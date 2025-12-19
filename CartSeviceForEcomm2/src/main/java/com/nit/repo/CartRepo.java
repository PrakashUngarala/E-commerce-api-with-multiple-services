package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.Entity.CartEntity;

public interface CartRepo extends JpaRepository<CartEntity, Integer>{

}
