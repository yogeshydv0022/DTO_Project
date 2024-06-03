package com.dtolayer;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private long productId;
	
	private String productName;
	
	private String productBrand;
	
	private String productModel;
	
	private double productPrice;
	
	private int productDiscount;
	
	private String productDescription;
	
	private String productImage;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private LocalDateTime createAt= LocalDateTime.now();

}
