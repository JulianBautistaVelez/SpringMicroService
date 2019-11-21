package com.julian.onlineshop.api.shop.data;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.julian.onlineshop.api.shop.model.ProductResponseModel;

@FeignClient(name="product-ms")
public interface ServiceProductClient {

	@GetMapping("products/shop/{shopID}")
	public List<ProductResponseModel> getProducts(@PathVariable("shopID") String shopID);
	
}
