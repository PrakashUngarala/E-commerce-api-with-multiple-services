package com.nit.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventryEntity {
	@Id
	@SequenceGenerator(name = "gen2",sequenceName = "InventrySeq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen2",strategy = GenerationType.SEQUENCE)
	private Integer ieId;
	private Integer quantity;

	@OneToOne(mappedBy = "ie")
	private ProductEntity pe;

}
