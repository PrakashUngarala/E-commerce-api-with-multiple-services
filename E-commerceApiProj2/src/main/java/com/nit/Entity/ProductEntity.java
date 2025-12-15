package com.nit.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "E-comm_Products_service")
public class ProductEntity  {

	@Id
	@SequenceGenerator(name = "gen2",sequenceName = "e-commerce-seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen2",strategy = GenerationType.SEQUENCE)
	private Integer pId;
	@NonNull
	@Column(length = 50)
	private String pName;
	@NonNull
	@Column(length = 50)
	private String companyName;
	@NonNull
	@Column(length = 50)
	private String mainCatagory;
	@NonNull
	@Column(length = 50)
	private String subCatagory;
	//@Lob
	//@NonNull
	//private byte[] productPicBytes;
	@NonNull
	@Column(length = 50)
	private Double price;
	@NonNull
	@Column(length = 150)
	private String discription;
	
	@NonNull
	@Column
	private Integer noOfProducts;
	
	@Version
	@Column(name="update_count")
	private Integer updateCount; 
	
	@CreationTimestamp
	@Column(name="created_on",updatable = false,insertable = true)
	private LocalDateTime createdOn;
	@UpdateTimestamp
	@Column(name="lastly_updated_on",updatable = true,insertable = false)
	private LocalDateTime lastlyUpdatedOn;
}
