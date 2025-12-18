package com.nit.SecConfig;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableMethodSecurity
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Bean
//	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)throws Exception{
//		http.authorizeHttpRequests((requests) -> {
//			requests.requestMatchers("/rest-api/").permitAll()
//			.requestMatchers("/rest-api/addProduct","/rest-api/showAllProducts","/rest-api/findProductByID/{ID}","/rest-api/updatePrice/{id}/{price}","/rest-api/updatingFullProduct","/rest-api/deletingWithId/{id}").authenticated()
//			.anyRequest().authenticated();
//			})
//		
//			.formLogin(form -> {form
//				//.login(Customizer.withDefaults())// automatically default form will come
//				.failureUrl("/accessdenied")
//				.permitAll();
//			})
//			.exceptionHandling(ex -> ex
//					.accessDeniedPage("/noPermisson")); //logged in but no permission
//		
//		return http.build();
//	}
//	
//	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		UserDetails emp = User.withDefaultPasswordEncoder()
//				.username("prakash")
//				.password("prakash")
//				.authorities("EMPLOYEE")
//				.build();
//		
//		UserDetails cus = User.withDefaultPasswordEncoder()
//				.username("raja")
//				.password("raja")
//				.authorities("CUSTOMER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(emp,cus);
//	}
//	
//	/*
//	@Bean
//	public UserDetailsManager createJdbcUDM(DataSource ds) {
//		return new JdbcUserDetailsManager(ds);
//	}
//	
//	
//	@Bean
//	public BCryptPasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}
//	*/
//	
//}
