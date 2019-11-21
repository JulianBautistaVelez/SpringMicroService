package com.julian.onlineshop.api.shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.julian.onlineshop.api.shop.shared.ShopDto;

public interface ShopsService extends UserDetailsService{
	public ShopDto createShop(ShopDto shopDetails);
	public ShopDto getShopDetailsByName(String shopName);
	public ShopDto getShopById(String shopID);
	public ShopDto getFullShopById(String shopID);
}
