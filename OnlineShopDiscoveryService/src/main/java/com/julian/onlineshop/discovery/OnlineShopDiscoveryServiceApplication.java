package com.julian.onlineshop.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OnlineShopDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopDiscoveryServiceApplication.class, args);
	}

}
