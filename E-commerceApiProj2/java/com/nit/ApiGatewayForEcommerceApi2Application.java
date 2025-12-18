package com.nit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayForEcommerceApi2Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayForEcommerceApi2Application.class, args);
	}

}
