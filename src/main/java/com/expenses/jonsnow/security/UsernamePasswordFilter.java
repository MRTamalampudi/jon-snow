package com.expenses.jonsnow.security;

import com.expenses.jonsnow.model.SecurityUser;
import com.expenses.jonsnow.model.User;
import com.expenses.jonsnow.model.UsernamePassword;
import com.expenses.jonsnow.service.JWTService;
import com.expenses.jonsnow.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.BufferedReader;
import java.io.IOException;

public class UsernamePasswordFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

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
        User user = ((SecurityUser) authResult.getPrincipal()).getUser();
        Cookie cookie = new Cookie(jwtService.getCookieName(), jwtService.generateToken(user));
        cookie.setDomain("expenses.io");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
       response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
