package com.hrms.security.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// Allow requests from any origin
		response.setHeader("Access-Control-Allow-Origin", "*");
		// Allow specific HTTP methods (e.g., GET, POST)
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		// Allow specific headers (e.g., Content-Type, Authorization)
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

		filterChain.doFilter(request, response);
	}
}
