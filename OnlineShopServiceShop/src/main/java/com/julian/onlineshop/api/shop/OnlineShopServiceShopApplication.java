package com.julian.onlineshop.api.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OnlineShopServiceShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopServiceShopApplication.class, args);
	}

}
