package com.nit.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.service.IProductService;

import com.nit.vo.ProductVOInput;
import com.nit.vo.ProductVOoutputjson;

@RestController
@RequestMapping("/products-api")
public class ProductRestController {

	@Autowired
	private IProductService service;
	
	
	
	public String getLoggedInUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	

	@GetMapping("/")
	public ResponseEntity<String> homePage(){
		//Authentication auth
		//String username = auth.getName();
		//System.out.println("user name is : "+username);
		return new ResponseEntity<>("Welcome to E- commerce website",HttpStatus.OK);
	}
	
	
	@PostMapping("/addProduct")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity<String> addingProduct(@RequestBody ProductVOInput productVOInput) {
		
		
		return new ResponseEntity<String>(service.addProduct(productVOInput),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/showAllProducts")
	@PreAuthorize("hasAnyAuthority('EMPLOYEE','CUSTOMER')")
	public ResponseEntity<List<ProductVOoutputjson>> showAllProducts(){
		
		System.out.println("show all products method is executed");
		
		return new ResponseEntity<>(service.showAllProducts(),HttpStatus.OK);
	}
	
	
	@GetMapping("/findProductByID/{id}")
	@PreAuthorize("hasAnyAuthority('EMPLOYEE','CUSTOMER')")
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
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity<String> updatingThePrice(@PathVariable("id") Integer id,@PathVariable("price") Double price){
		String msg = service.updatingThePrice(id, price);
		
		return new ResponseEntity<>( msg ,HttpStatus.OK);
	}
	
	
	@PutMapping("/updatingFullProduct")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity<String> updatingFullPro(@ModelAttribute ProductVOInput productVOInput){
		
		String msg = service.updatingTheProduct(productVOInput);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deletingWithId/{id}")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity<String> deletingWithId(@PathVariable Integer id){
		String msg = service.deletingById(id);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	//username or password wrong
	@GetMapping("/accessdenied")
	public ResponseEntity<String> accessDenied(){
		
		return new ResponseEntity<String>("accessdenied method is excuted username or password wrong",HttpStatus.UNAUTHORIZED);
	}
	
	
	
	@GetMapping("/noPermisson")
	public ResponseEntity<String> noPermisson(){
		
		return new ResponseEntity<String>("noPermisson method is excuted no permission for this endpoint",HttpStatus.FORBIDDEN);
	}
	
	
	@GetMapping("/userCartProducts")
	public ResponseEntity<List<ProductVOoutputjson>> userCartProducts(){
		
		String username = "prakash";
		//need to user name dynamically
		
		return new ResponseEntity<List<ProductVOoutputjson>>(service.retrivingUserCart(username),HttpStatus.ACCEPTED); 
		
	}
	
	
}
