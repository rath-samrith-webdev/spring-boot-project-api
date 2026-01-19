package com.springdev.rentalApp.config.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * A Servlet Filter that logs the incoming request URI and the outgoing response status.
 * This operates at the lowest level of the request lifecycle.
 */
@Component
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        logger.info("[Filter] Incoming Request: {} {}", httpRequest.getMethod(), httpRequest.getRequestURI());

        chain.doFilter(request, response);

        logger.info("[Filter] Outgoing Response Status: {}", httpResponse.getStatus());
    }
}
