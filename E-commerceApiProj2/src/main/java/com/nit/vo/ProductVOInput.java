package com.nit.vo;

import org.springframework.web.multipart.MultipartFile;

import com.nit.Entity.InventryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVOInput {

	private Integer pId;
	
	private String pname;
	
	private String companyName;
	
	
	private String catagory;
	
	
	//@JsonIgnore
	//private MultipartFile productImageMultipartFile;    //for uploading the image
	
	
	private String discription;
	
	private InventryVO ieVO;
	//private String displayImage;                   //for displaying the image
	
}
