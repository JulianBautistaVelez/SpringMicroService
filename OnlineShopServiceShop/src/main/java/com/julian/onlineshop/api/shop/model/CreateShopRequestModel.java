package com.julian.onlineshop.api.shop.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateShopRequestModel {
	
	@NotNull(message="You should give a public name to your shop")
	private String alias;
	
	@NotNull(message="You should give a private name to your shop, it is the name you will log in with")
	@Size(min=5,message="Your shop private name must have at least 5 characters.")
	private String name;
	
	
	//DOES NOT WORK; IS NOT RECOGNIZED FROM THE JSON OBJECT RECEIVED, TO SOLVE**********************
	@NotNull(message="You must provide a password")
	@Size(min=8,message="Your password name must have at least 8 characters.")
	private String pass;
	
	@NotNull(message="You must give an email to your shop, your customers need a way to contact you")
	@Email
	private String email;
	
	private String description;
	
	private String location;
	
	
	
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
	
	

}
