package com.julian.onlineshop.api.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julian.onlineshop.api.product.service.ProductsService;

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

}
