package com.julian.onlineshop.api.shop.shared;
/**
 * @author Julián Bautista Vélez
 * 
 */

import java.io.Serializable;
import java.util.List;

import com.julian.onlineshop.api.shop.model.ProductResponseModel;

public class ShopDto implements Serializable {
	
	private static final long serialVersionUID = -5215636719716319379L;
	private String alias;
	private String name;
	private String password;
	private String description;
	private String location;
	private String email;
	private String shopID;
	private String encryptedPassword;
	private List<ProductResponseModel> products;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public List<ProductResponseModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponseModel> products) {
		this.products = products;
	}

}
