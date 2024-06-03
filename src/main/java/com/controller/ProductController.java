package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtolayer.ProductDto;
import com.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// createProduct
	@PostMapping("/")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDTO) {
		ResponseEntity<?> resp = null;
		try {
			ProductDto products = productService.createProduct(productDTO);
			resp = new ResponseEntity<ProductDto>(products, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("unable to create  products ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	//update Products By Id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProducts(@RequestBody ProductDto productDto, @PathVariable("id") long id) {
		ResponseEntity<?> resp = null;
		try {
			ProductDto products = productService.updateProduct(productDto,id);
			resp = new ResponseEntity<ProductDto>(products, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("unable to update  products ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	// get Products By Brand 
	@GetMapping("/{productBrand}")
	public ResponseEntity<?> findByProductBrand(@PathVariable("productBrand")String productBrand) {
		
		
			List<ProductDto> products = productService.getProductByBrand(productBrand);
			return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
	}
	
	// get all Products
	@GetMapping("/")
	public ResponseEntity<?> getAllProduct() {
		ResponseEntity<?> resp = null;
		try {
			List<ProductDto> products = productService.getAllProducts();
			resp = new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("unable to find all products", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

}
