package com.nit.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVOoutputjson{

	private Integer pId;
	
	private String pName;
	
	private String companyName;
	
	private String mainCatagory;
	
	private String subCatagory;
	
	private Integer noOfProducts;
	
	//@JsonIgnore
	//private MultipartFile productImageMultipartFile;    //for uploading the image
	
	private Double price;
	
	private String discription;
	
	private String displayImage;                   //for displaying the image
	
}
