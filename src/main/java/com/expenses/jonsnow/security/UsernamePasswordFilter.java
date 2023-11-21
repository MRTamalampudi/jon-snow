package com.expenses.jonsnow.security;

import com.expenses.jonsnow.config.URLConstants;
import com.expenses.jonsnow.model.UsernamePassword;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;

public class UsernamePasswordFilter extends AbstractAuthenticationProcessingFilter {

    public UsernamePasswordFilter(AuthenticationManager authenticationManager) {
        super("/login");
        super.setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        UsernamePassword usernamePassword = parseBody(request);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                usernamePassword.getUsername(),
                usernamePassword.getPassword()
        );
        Authentication authentication = this.getAuthenticationManager().authenticate(authenticationToken);
        return authentication;
    }


    public UsernamePassword parseBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String jsonString = bufferedReader.readLine();
        while (jsonString != null){
            stringBuilder.append(jsonString);
            jsonString = bufferedReader.readLine();
        }
        jsonString = stringBuilder.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, UsernamePassword.class);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setHeader("username", authResult.getName());
        Cookie cookie = new Cookie("username",authResult.getName());
        cookie.setDomain("dev.expenses.io");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
       response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
