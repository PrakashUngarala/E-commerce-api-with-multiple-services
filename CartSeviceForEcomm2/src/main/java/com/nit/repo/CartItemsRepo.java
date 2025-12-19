package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.Entity.CartItemsEntity;

public interface CartItemsRepo extends JpaRepository<CartItemsEntity, Integer> {

	
}
