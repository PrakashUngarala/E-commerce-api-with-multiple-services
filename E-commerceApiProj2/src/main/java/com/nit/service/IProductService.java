package com.nit.service;

import java.io.IOException;
import java.util.List;


import com.nit.vo.ProductVOInput;
import com.nit.vo.ProductVOoutputjson;

public interface IProductService {

	
	public String addProduct(ProductVOInput productVOInput);
	
	public List<ProductVOoutputjson> showAllProducts();
	
	public ProductVOoutputjson findingProductById(Integer id);
	
	public String updatingThePrice(Integer id,Double price);
	
	public String updatingTheProduct(ProductVOInput productVOInput);
	
	public String deletingById(Integer id);
	
	public List<ProductVOoutputjson> retrivingUserCart(String username);
	
}
