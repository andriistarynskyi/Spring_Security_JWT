package com.example.spring_boot_jwt.config;

import com.auth0.jwt.JWT;
import com.example.spring_boot_jwt.model.User;
import com.example.spring_boot_jwt.model.security.UserPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final String secretSalt;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) {
        User userFromCredentials = new User();
        userFromCredentials.setLogin(req.getParameter("username"));
        userFromCredentials.setPassword(req.getParameter("password"));
        UserPrincipal user = new UserPrincipal(userFromCredentials);

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.emptyList())
        );
    }

    @Override
    public void successfulAuthentication(HttpServletRequest req,
                                         HttpServletResponse res,
                                         FilterChain chain,
                                         Authentication auth) throws JsonProcessingException {
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        String token = JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(principal))
                .withExpiresAt(Date.from(LocalDateTime.now().plus(30, ChronoUnit.MINUTES)
                        .atZone(ZoneId.systemDefault()).toInstant())
                ).sign(HMAC512(secretSalt.getBytes()));
        res.addHeader("Authorization", "Bearer " + token);
    }
}