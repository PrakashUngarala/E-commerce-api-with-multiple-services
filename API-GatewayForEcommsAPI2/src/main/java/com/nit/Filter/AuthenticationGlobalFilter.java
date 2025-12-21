package com.nit.Filter;

import java.net.URI;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.nit.JwtUtil.JwtUtil;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

//it is globalfilter it is executed for every request
//gatewatfilter means it is executed for selected requests
@Component
public class AuthenticationGlobalFilter implements GlobalFilter, Ordered {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getURI().getPath(); //user requested path storing ex /order/9
		
		
		if(path.contains("/oauth2") || path.contains("/login")) {
			return chain.filter(exchange);
		}
		
		
		HttpCookie jwtCookie = request.getCookies().getFirst("JWT_Token_ecom");
		
		if(jwtCookie == null) {
			return redirectToLogin(exchange);
		}
		
		try {
			Claims claims = jwtUtil.validateToken(jwtCookie.getValue());
			
			String role = claims.get("role",String.class);
			String username = claims.getSubject();
			
			
			ServerHttpRequest mutatedRequest = request.mutate()
			.header("X-Auth-User", username)
			.header("X-Auth-Role",role)
			.build();
			
			return chain.filter(exchange.mutate().request(mutatedRequest).build());
			
		}catch(Exception e) {
			//token expired
			return redirectToLogin(exchange);
		}
		
	}
	
	private Mono<Void> redirectToLogin(ServerWebExchange exchange){
		
		String orginalPath = exchange.getRequest().getURI().toString();
		String loginUrl = "http://localhost:8080/login?continue=" + orginalPath;
	
		exchange.getResponse().setStatusCode(HttpStatus.FOUND);
		exchange.getResponse().getHeaders().setLocation(URI.create(loginUrl));
		
		return exchange.getResponse().setComplete();
		
		
	}
	
	
}
