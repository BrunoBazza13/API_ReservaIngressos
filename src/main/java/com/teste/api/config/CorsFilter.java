package com.teste.api.config;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletResponse res = (HttpServletResponse) response;
	        HttpServletRequest req = (HttpServletRequest) request;

	        // Permitir todas as origens
	        res.setHeader("Access-Control-Allow-Origin", "*");
	        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	        res.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization, X-Requested-With, remember-me");

	        chain.doFilter(request, response);
	    }

	
}
