package com.nit.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.service.IProductService;

import com.nit.vo.ProductVOInput;
import com.nit.vo.ProductVOoutputjson;

@RestController
@RequestMapping("rest-api")
public class ProductRestController {

	@Autowired
	private IProductService service;
	
	

	@GetMapping("/")
	public ResponseEntity<String> homePage(){
		return new ResponseEntity<>("Welcome to E- commerce website",HttpStatus.OK);
	}
	
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addingProduct(@ModelAttribute ProductVOInput productVOInput) throws IOException{
		
		
		return new ResponseEntity<String>(service.addProduct(productVOInput),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/showAllProducts")
	public ResponseEntity<List<ProductVOoutputjson>> showAllProducts(){
		
		System.out.println("show all products method is executed");
		
		return new ResponseEntity<>(service.showAllProducts(),HttpStatus.OK);
	}
	
	
	@GetMapping("/findProductByID/{id}")
	public ResponseEntity<ProductVOoutputjson> findingProductByID(@PathVariable("id") Integer id){
		try {
			Thread.sleep(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ProductVOoutputjson product = service.findingProductById(id);
		
		return new ResponseEntity<>( product ,HttpStatus.OK);
	}
	
	
	@PatchMapping("/updatePrice/{id}/{price}")
	public ResponseEntity<String> updatingThePrice(@PathVariable("id") Integer id,@PathVariable("price") Double price){
		String msg = service.updatingThePrice(id, price);
		
		return new ResponseEntity<>( msg ,HttpStatus.OK);
	}
	
	
	@PutMapping("/updatingFullProduct")
	public ResponseEntity<String> updatingFullPro(@ModelAttribute ProductVOInput productVOInput){
		
		String msg = service.updatingTheProduct(productVOInput);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deletingWithId/{id}")
	public ResponseEntity<String> deletingWithId(@PathVariable Integer id){
		String msg = service.deletingById(id);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	
}
