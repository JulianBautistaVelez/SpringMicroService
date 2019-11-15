package com.julian.onlineshop.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class OnlineShopConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopConfigServiceApplication.class, args);
	}

}
