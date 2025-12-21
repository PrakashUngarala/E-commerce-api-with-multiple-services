package com.nit.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class FilterForAuthorization extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String username = request.getHeader("X-Auth-User");
        String roleHeader = request.getHeader("X-Auth-Role");
        
        if (username != null && roleHeader != null) {
            
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleHeader);
            
            UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(authority));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
	}

}
