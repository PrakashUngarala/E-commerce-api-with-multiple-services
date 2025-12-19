package com.nit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.service.ICartService;

@RestController
@RequestMapping("/cart-api")
public class CartApi {

	@Autowired
	private ICartService service;
	
	@GetMapping("/")
	public ResponseEntity<String> welcomeMsg(){
		return new ResponseEntity<String>("Welcome to cart",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addtocart/username/productid")
	public ResponseEntity<Boolean> addToCart(@PathVariable String username,@PathVariable Integer productid ){
		
		return new ResponseEntity<Boolean>(service.addtocart(username, productid),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/retriveProductsIdsUsingUsername")
	public ResponseEntity<List<Integer>> productsOfUser(@RequestParam String username){
		
		
		return new ResponseEntity<List<Integer>>(service.retrivingProductsOfUser(username),HttpStatus.ACCEPTED);
	}
	
}
