package com.julian.onlineshop.api.shop.model;

import java.util.List;

public class ShopProductsResponseModel {

	private String alias;
	private String name;
	private String email;
	private String description;
	private String location;
	private List<ProductResponseModel> products;
	
	
	public List<ProductResponseModel> getProducts() {
		return products;
	}
	public void setProducts(List<ProductResponseModel> products) {
		this.products = products;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
