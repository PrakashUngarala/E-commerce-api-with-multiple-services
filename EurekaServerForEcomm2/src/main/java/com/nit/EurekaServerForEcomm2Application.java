package com.nit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerForEcomm2Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerForEcomm2Application.class, args);
	}

}
