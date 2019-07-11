package com.epam.security.securitydemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("Request received. Request URI={}, Request Method={}", request.getRequestURI(), request.getMethod());
        if (!request.getRequestURI().contains("/login") && request.getHeader("Authorization") == null) {
            log.error("Authorization header is missing!");
        }
        filterChain.doFilter(request, response);
    }
}
