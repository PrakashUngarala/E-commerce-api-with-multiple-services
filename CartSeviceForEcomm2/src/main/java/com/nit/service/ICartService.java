package com.nit.service;

import java.util.List;

import org.springframework.stereotype.Service;


public interface ICartService {

	public boolean addtocart(String username,Integer productid);
	public List<Integer> retrivingProductsOfUser(String username);
}
