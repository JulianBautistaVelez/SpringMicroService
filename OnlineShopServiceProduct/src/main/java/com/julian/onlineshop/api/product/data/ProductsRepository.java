package com.julian.onlineshop.api.product.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<ProductEntity, Long>{

	public ProductEntity findByName(String ProductName);
	public List<ProductEntity> findByShopID(String shopId);
}
