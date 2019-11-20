package com.julian.onlineshop.api.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import org.modelmapper.TypeToken;

import com.julian.onlineshop.api.product.data.ProductEntity;
import com.julian.onlineshop.api.product.data.ProductsRepository;
import com.julian.onlineshop.api.product.shared.ProductDto;

@Service
public class ProductsServiceIMPL implements ProductsService {
	
	ProductsRepository productsRepository;
	
	public ProductsServiceIMPL(ProductsRepository productsRepository) {
		super();
		this.productsRepository=productsRepository;
	}

	@Override
	public ProductDto createProduct(ProductDto productDetails) {
		productDetails.setProductID(UUID.randomUUID().toString());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ProductEntity productEntity = modelMapper.map(productDetails, ProductEntity.class);
		
		productsRepository.save(productEntity);
		
		ProductDto returnValue = modelMapper.map(productEntity, ProductDto.class);
		
		return returnValue;
	}

	@Override
	public ProductDto getProductDetailsByName(String productName) {
		ProductEntity productEntity = productsRepository.findByName(productName);
		return new ModelMapper().map(productEntity, ProductDto.class);
	}

	@Override
	public List<ProductDto> getProductByShop(String shopID) {
		List<ProductEntity> productEntities = productsRepository.findByShopID(shopID);
		List<ProductDto> returnValue = new ArrayList<>();
		
		if(productEntities == null || productEntities.isEmpty()) {
			return returnValue;
		}
		
		Type listType = new TypeToken<List<ProductDto>>(){}.getType();
		
		returnValue = new ModelMapper().map(productEntities, listType);
		
		return returnValue;
	}
	
	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductEntity> productEntities = (List<ProductEntity>) productsRepository.findAll();
		List<ProductDto> returnValue = new ArrayList<>();
		
		if(productEntities == null || productEntities.isEmpty()) {
			return returnValue;
		}
		
		Type listType = new TypeToken<List<ProductDto>>(){}.getType();
		
		returnValue = new ModelMapper().map(productEntities, listType);
		
		return returnValue;
	}
	
	

}
