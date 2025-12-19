package com.nit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.Entity.CartEntity;
import com.nit.Entity.CartItemsEntity;
import com.nit.globalExp.UserNameIsNull;
import com.nit.repo.CartItemsRepo;
import com.nit.repo.CartRepo;

@Service
public class CartServiceImpl  implements ICartService{

	@Autowired
	private CartRepo cartrepo;
	
	@Autowired
	private CartItemsRepo itemsrepo;
	
	@Override
	public boolean addtocart(String username, Integer productid) {
		if(username == null) {
			 throw new UserNameIsNull(null);
		}
		List<CartEntity> all = cartrepo.findAll();
		int flag =0;
		
		for(CartEntity cart : all) {
			if(cart.getUsername().equals(username)) {
				CartItemsEntity items = new CartItemsEntity();
				items.setCart(cart);
				items.setPid(productid);
				items.setQuantity(items.getQuantity()+1);
				itemsrepo.save(items);
			   flag=1;
				break;
			}
		}
		if(flag ==0) {
			CartEntity cart = new CartEntity();
			cart.setUsername(username);
			cartrepo.save(cart);
			CartItemsEntity items = new CartItemsEntity();
			items.setCart(cart);
			items.setPid(productid);
			items.setQuantity(items.getQuantity()+1);
			itemsrepo.save(items);
			
		}
		
		
		
		return true;
	}
	@Override
	public List<Integer> retrivingProductsOfUser(String username) {
		if(username == null) {
			 throw new UserNameIsNull(null);
		}
		List<CartEntity> carts = cartrepo.findAll();
		
		CartEntity ce = null;
		int flag = 0;
		for(CartEntity cart : carts) {
			if(username.equals(cart.getUsername())) {
				ce = cart;
				flag =1;
				break;
			}
		}
		if(flag == 0) {
			ce = new CartEntity();
			ce.setUsername(username);
		}
		cartrepo.save(ce);
		
		return null;
	}

}
