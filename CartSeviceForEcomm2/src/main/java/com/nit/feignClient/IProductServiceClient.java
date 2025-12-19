package com.nit.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("Product-service-Api")
public interface IProductServiceClient {

	
}
