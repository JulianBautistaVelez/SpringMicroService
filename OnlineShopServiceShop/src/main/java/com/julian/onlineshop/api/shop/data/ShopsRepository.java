package com.julian.onlineshop.api.shop.data;


import org.springframework.data.repository.CrudRepository;

public interface ShopsRepository extends CrudRepository<ShopEntity, Long> {

	public ShopEntity findByName(String shopName);
	public ShopEntity findByShopID(String shopID);
}
