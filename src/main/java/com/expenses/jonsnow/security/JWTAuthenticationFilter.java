package com.expenses.jonsnow.security;

import com.expenses.jonsnow.service.JPAUserDetailsService;
import com.expenses.jonsnow.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JPAUserDetailsService userDetailsService;
    @Autowired
    private JWTService jwtService;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super("/**/*");
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookie = Arrays.stream(cookies)
                .filter(cookie1 -> jwtService.getCookieName().equals(cookie1.getName()))
                .findFirst();
        Jws<Claims> claimsJws = jwtService.parseToken(cookie.isPresent() ? cookie.get().getValue() : "");
        UserDetails userDetails = userDetailsService.loadUserByUsername(claimsJws.getPayload().get("email").toString());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword());
        return this.getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        if (authResult != null) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authResult);
            SecurityContextHolder.setContext(context);
        }
        chain.doFilter(request, response);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return super.requiresAuthentication(request, response)
                && !(new AntPathRequestMatcher("/login").matches(request))
                && !(new AntPathRequestMatcher("/user/signup").matches(request));
    }
}
