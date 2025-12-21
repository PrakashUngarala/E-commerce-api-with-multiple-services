package com.nit.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("CartSeviceForEcomm2")
public interface ICartServiceClient {

	@GetMapping("/retriveProductsIdsUsingUsername")
	public ResponseEntity<List<Integer>> productsOfUser(String username);
	
	
}
