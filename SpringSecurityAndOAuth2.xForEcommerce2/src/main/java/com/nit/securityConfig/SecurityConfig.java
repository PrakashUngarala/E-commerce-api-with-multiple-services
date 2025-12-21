package com.nit.securityConfig;


import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.nimbusds.openid.connect.sdk.federation.utils.JWTUtils;
import com.nit.jwtTokenGen.JwtToekenGen;

import jakarta.servlet.http.Cookie;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtToekenGen jwtToken;

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)throws Exception{
		http.authorizeHttpRequests((requests) -> {
			requests.requestMatchers("/login","/oauth2/**").permitAll()
			
			.anyRequest().authenticated();
			})
		
			.formLogin(Customizer.withDefaults())
			.oauth2Login(Customizer.withDefaults())
			.formLogin(form -> {form
				//.login(Customizer.withDefaults())// automatically default form will come
				.successHandler(customSuccessHandler())
				.failureUrl("/accessdenied")
				.permitAll();
			})
			.oauth2Login(oauth2 -> oauth2
		            .successHandler(customSuccessHandler()) // Handle Google/Facebook Login
		        )
			.exceptionHandling(ex -> ex
					.accessDeniedPage("/noPermisson")); //logged in but no permission
		
		return http.build();
	}
	
	
	@Bean
	public UserDetailsManager createJdbcUDM(DataSource ds) {
		return new JdbcUserDetailsManager(ds);
	}
	
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public AuthenticationSuccessHandler customSuccessHandler() {
		return (request,response,authentication)->{
			String username = "";
			
			if (authentication.getPrincipal() instanceof OAuth2User) {
	            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
	            Map<String, Object> attributes = oAuth2User.getAttributes();

	            // 1. Try to get Email (best for "username")
	            if (attributes.containsKey("email")) {
	            	username = (String) attributes.get("email");
	            } 
	            // 2. Fallback to 'name' if email isn't provided (depends on FB permissions)
	            else if (attributes.containsKey("name")) {
	            	username = (String) attributes.get("name");
	            } 
	            // 3. Final Fallback to the provider's unique ID (sub for Google, id for FB)
	            else {
	            	username = oAuth2User.getName(); 
	            }
			}else {
				username = authentication.getName();
			}
			
			String token = jwtToken.generateToken(username);
			
			Cookie jwtCookie = new Cookie("JWT_Token_ecom",token);
			jwtCookie.setHttpOnly(true);
			jwtCookie.setPath("/");
			jwtCookie.setMaxAge(100);
			response.addCookie(jwtCookie);
			
			
			String continueUrl = request.getParameter("continue");
			if(continueUrl == null || continueUrl.isEmpty()) {
				continueUrl = "http://localhost:8080/";
			}
			
			response.sendRedirect(continueUrl);
			
		};
		
		
	}
	
}
