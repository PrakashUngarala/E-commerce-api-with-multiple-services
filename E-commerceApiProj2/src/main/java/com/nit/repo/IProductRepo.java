package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.Entity.ProductEntity;

public interface IProductRepo extends JpaRepository<ProductEntity, Integer>{

}
