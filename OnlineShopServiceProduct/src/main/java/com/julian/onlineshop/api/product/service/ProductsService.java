package com.julian.onlineshop.api.product.service;

import java.util.List;

import com.julian.onlineshop.api.product.shared.ProductDto;

public interface ProductsService {

	public ProductDto createProduct(ProductDto productDetails);
	public ProductDto getProductDetailsByName(String productName);
	public List<ProductDto> getProductByShop(String shopID);
	List<ProductDto> getAllProducts();
	
}
