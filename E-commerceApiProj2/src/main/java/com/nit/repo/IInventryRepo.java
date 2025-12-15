package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.Entity.InventryEntity;

public interface IInventryRepo  extends JpaRepository<InventryEntity, Integer>{

}
