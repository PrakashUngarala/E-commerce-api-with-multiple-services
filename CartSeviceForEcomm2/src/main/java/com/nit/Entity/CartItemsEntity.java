package com.nit.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemsEntity {

	@Id
	@SequenceGenerator(name = "gen4",sequenceName = "seq_nm4",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "gen4",strategy = GenerationType.SEQUENCE)
	private Integer citid;
	
	@ManyToOne
	@JoinColumn(name = "user_cartid",referencedColumnName = "cid")
	private CartEntity cart;
	
	private Integer pid;
	
	private Integer quantity;
	
}
