package com.julian.onlineshop.api.product.controller;
/*
 * @author Julián Bautista Vélez
 * 
 */
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julian.onlineshop.api.product.model.CreateProductRequestModel;
import com.julian.onlineshop.api.product.model.CreateProductResponseModel;
import com.julian.onlineshop.api.product.service.ProductsService;
import com.julian.onlineshop.api.product.shared.ProductDto;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ProductsService productsService;
	
	@GetMapping("/status")
	public String status() {
		return "The service Products is working on port " + env.getProperty("local.server.port");
	}
	
	@GetMapping
	public ResponseEntity<List<CreateProductResponseModel>> listProducts(){
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		List<ProductDto> allProducts = productsService.getAllProducts();
		List<CreateProductResponseModel> responseBody = new ArrayList<>();
		
		if(allProducts == null || allProducts.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
		}
		
		Type listType = new TypeToken<List<CreateProductResponseModel>>(){}.getType();
		responseBody = modelMapper.map(allProducts, listType);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}
	
	@PostMapping("/product")
	public ResponseEntity<CreateProductResponseModel>createProduct(@Valid @RequestBody CreateProductRequestModel productCreationForm) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ProductDto productDto = modelMapper.map(productCreationForm, ProductDto.class);
		
		ProductDto productCreated = productsService.createProduct(productDto);
		
		CreateProductResponseModel returnBody = modelMapper.map(productCreated, CreateProductResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnBody);
	}
	
	@GetMapping("/shop/{id}")
	public ResponseEntity<List<CreateProductResponseModel>> listProductsOfShop(@PathVariable("id") String shopID) {
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<ProductDto> productsOfShop = productsService.getProductByShop(shopID);
		List<CreateProductResponseModel> responseBody = new ArrayList<>();
		
		if(productsOfShop == null || productsOfShop.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
		}
		
		Type listType = new TypeToken<List<CreateProductResponseModel>>(){}.getType();
		responseBody = modelMapper.map(productsOfShop, listType);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}

}
