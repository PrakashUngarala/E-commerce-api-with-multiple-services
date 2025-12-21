package com.nit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nit.Advice.ProductNotFoundException;
import com.nit.Advice.UserNameIsNullExp;
import com.nit.Entity.InventryEntity;
import com.nit.Entity.ProductEntity;
import com.nit.feignClient.ICartServiceClient;
import com.nit.repo.IProductRepo;
import com.nit.vo.InventryVO;
import com.nit.vo.ProductVOInput;
import com.nit.vo.ProductVOoutputjson;
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepo repo;
	
	@Autowired
	private ICartServiceClient cartClient;

	@Override
	public String addProduct(ProductVOInput productVOInput)  {
		ProductEntity proEn = new ProductEntity();
		InventryEntity ie = new InventryEntity();
		BeanUtils.copyProperties(productVOInput, proEn);
		BeanUtils.copyProperties(productVOInput.getIeVO(), ie);
		
		proEn.setIe(ie);
		ie.setPe(proEn);
		//proEn.setProductPicBytes(productVOInput.getProductImageMultipartFile().getBytes());
		ProductEntity save = repo.save(proEn);
		return "Product is saved with id : "+save.getPId();
	}
	
	
	@Override
	public List<ProductVOoutputjson> showAllProducts() {
		List<ProductEntity> entities = repo.findAll();
		
		List<ProductVOoutputjson> vos = new ArrayList<>(); 
		
		for(ProductEntity ent : entities) {
			ProductVOoutputjson vo = new ProductVOoutputjson();
			BeanUtils.copyProperties(ent, vo);
			InventryVO ievo = new InventryVO();
			BeanUtils.copyProperties(ent.getIe(), ievo);
			vo.setIeVO(ievo);
			//String base64 = Base64.getEncoder().encodeToString(ent.getProductPicBytes());
			//vo.setDisplayImage(base64);
			vos.add(vo);
		}
		
		
		return vos;
	}
	
	@Override
	public ProductVOoutputjson findingProductById(Integer id) {
		Optional<ProductEntity> entity = repo.findById(id);
		ProductVOoutputjson vo = new ProductVOoutputjson();
		if(entity.isPresent()) {
			
			BeanUtils.copyProperties(entity.get(), vo);
			
			//String base64 = Base64.getEncoder().encodeToString(entity.get().getProductPicBytes());
			//vo.setDisplayImage(base64);
		}else {
			throw new ProductNotFoundException("product is not there with this id "+id);
		}
		
		
		
		return vo;
	}
	
	
	@Override
	public String updatingThePrice(Integer id, Double price) {
		Optional<ProductEntity> byId = repo.findById(id);
		if(byId.isPresent()) {
			ProductEntity entity = byId.get();
			entity.setPrice(price);
			repo.save(entity);
			
		}else {
			throw new ProductNotFoundException("product is not there in the db for update for price with this id "+id);
		}
		
		return "Product is updated successfully with this "+id;
	}
	
	
	@Override
	public String updatingTheProduct(ProductVOInput productVOInput) {
		Optional<ProductEntity> byId = repo.findById(productVOInput.getPId());
		ProductEntity entity = byId.get();
		if(byId.isPresent()) {
			BeanUtils.copyProperties(productVOInput, entity);
			/*
			try {
				entity.setProductPicBytes(productVOInput.getProductImageMultipartFile().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			ProductEntity save = repo.save(entity);
			
			return "the product is updated successfully "+save.getPId();
			
		}else {
			throw new ProductNotFoundException("product with this id  "+productVOInput.getPId()+" is not there in the database for full update");
		}
		
		
		//return null;
	}
	
	
	@Override
	public String deletingById(Integer id) {
		
		Optional<ProductEntity> entity = repo.findById(id);
		
		if(entity.isPresent()) {
			
			repo.deleteById(id);
		}else {
			throw new ProductNotFoundException("product is not avaiable with this "+id+" for delete operation");
		}
		
		
		
		return "the product is deleted whith is id "+id+" successfully ";
		
		
	}
	
	@Override
	public List<ProductVOoutputjson> retrivingUserCart(String username) {
		
		ResponseEntity<List<Integer>> productsOfUser = cartClient.productsOfUser(username);
		int statusCode = productsOfUser.getStatusCode().value();
		if(statusCode == 502) {
			throw new UserNameIsNullExp("Username is null");
		}
		
		List<ProductVOoutputjson> products = new ArrayList<ProductVOoutputjson>();
		List<Integer> body = productsOfUser.getBody();
		
		body.forEach( pid -> {
			Optional<ProductEntity> byId = repo.findById(pid);
			if(byId.isPresent()) {
				ProductVOoutputjson vo = new ProductVOoutputjson();
				BeanUtils.copyProperties(byId.get(),vo);
				products.add(vo);
			}
		});
		
		
		
		
		return products;
	}
	
}
