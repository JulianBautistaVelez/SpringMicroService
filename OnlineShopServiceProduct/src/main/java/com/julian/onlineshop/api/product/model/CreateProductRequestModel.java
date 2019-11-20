package com.julian.onlineshop.api.product.model;
 
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class CreateProductRequestModel {
	
	@NotNull(message="You must give a name to your product")
	private String name;
	
	private String description;
	
	@NotNull(message="The price of a product cannot be empty")
	@Digits(integer=20,fraction=2, message="Only 2 fractionary digits")
	private float price;
	
	private int stock;
	
	@NotNull(message="At least one tag indicating the category of the product is needed")
	private String tags;
	
	@NotNull(message="You must provide the public id of your shop to add your product")
	private String shopID;

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
