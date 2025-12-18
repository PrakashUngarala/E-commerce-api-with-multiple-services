package com.nit.securityConfig;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

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
				.failureUrl("/accessdenied")
				.permitAll();
			})
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
	
}
