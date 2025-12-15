package com.nit.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nit.Entity.ProductEntity;

import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class InventryVO {

	private Integer ieId;
	private Integer quantity;
	
	@JsonIgnore
	private ProductEntity pe;
}
