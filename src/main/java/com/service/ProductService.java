package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customException.ResourceNotFoundException;
import com.dtolayer.ProductDto;
import com.entity.Product;
import com.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	public ProductDto convertToDTO(Product product) {
		return modelMapper.map(product, ProductDto.class);
	}

	public Product convertToEntity(ProductDto productDto) {
		return modelMapper.map(productDto, Product.class);
	}

	// Add Product With ProductDto
	public ProductDto createProduct(ProductDto productDto) {
		Product product = this.convertToEntity(productDto);
		Product saveproduct = this.productRepository.save(product);
		return this.convertToDTO(saveproduct);
	}

	// update By Id With ProductDto
	public ProductDto updateProduct(ProductDto productDto, long id) {

		// get Id By repository
		Product product = this.productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product id not found " + id));

		// convert the data product to ProductDto
		product.setProductName(productDto.getProductName());
		product.setProductBrand(productDto.getProductBrand());
		product.setProductModel(productDto.getProductModel());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductDiscount(productDto.getProductDiscount());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductImage(productDto.getProductImage());
		// save Product in database using repository
		Product update = this.productRepository.save(product);
		
		// convetProduct to ProductDto than Return productDto
		ProductDto productdto = this.convertToDTO(update);
		return productdto;

	}

	// Find Product By ProductBrand And Return ByProductDto
	public List<ProductDto> getProductByBrand(String productBrand) {
		// get ProductBrand By repository
		List<Product> product = this.productRepository.findByProductBrand(productBrand);
		if(product.isEmpty())
			{
		     throw	new ResourceNotFoundException("product name not found  "+productBrand);
		     }
		//.orElseThrow(() -> new ResourceNotFoundException("product name not found  "+productBrand));

		// convetProduct List to ProductDto than Return productDto
		return product.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Find All Products And Return By ProductDto
	public List<ProductDto> getAllProducts() {

		List<Product> plist = this.productRepository.findAll();

		return plist.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Find By ProductByName Than Return By ProductDto
	public ProductDto getByProductName(String productName) {
		// get Name By repository
		Product product = this.productRepository.findByProductName(productName);
//				.orElseThrow(() -> new ResourceNotFoundException("product name not found  " + productName));

		// convetProduct to ProductDto than Return productDto
		ProductDto productdto = this.convertToDTO(product);
		return productdto;
	}

}
