package com.julian.onlineshop.api.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OnlineShopServiceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopServiceProductApplication.class, args);
	}

}
