package com.nit.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEntity {

	@Id
	@SequenceGenerator(name = "gen3",sequenceName = "cartseq1",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen3",strategy = GenerationType.SEQUENCE)
	private Integer cid;
	
	@Column(length = 30)
	private String username;
	
}
