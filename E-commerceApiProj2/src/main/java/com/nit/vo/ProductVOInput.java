package com.nit.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVOInput {

	private Integer pId;
	
	private String pName;
	
	private String companyName;
	
	
	private String catagory;
	
	
	//@JsonIgnore
	//private MultipartFile productImageMultipartFile;    //for uploading the image
	
	private Double price;
	
	private String discription;
	
	//private String displayImage;                   //for displaying the image
	
}
