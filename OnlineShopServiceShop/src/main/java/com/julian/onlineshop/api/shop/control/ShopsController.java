package com.julian.onlineshop.api.shop.control;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julian.onlineshop.api.shop.model.CreateShopRequestModel;
import com.julian.onlineshop.api.shop.service.ShopsService;
import com.julian.onlineshop.api.shop.shared.ShopDto;

@RestController
@RequestMapping("/shops")
public class ShopsController {

	@Autowired
	private Environment env;

	@Autowired
	private ShopsService shopsService;

	@PostMapping
	public String createShop(@Valid @RequestBody CreateShopRequestModel shopCreationForm) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ShopDto shopDto = modelMapper.map(shopCreationForm, ShopDto.class);

		shopsService.createShop(shopDto);
		return "Shop created";
	}

	

	@GetMapping("/status")
	public String status() {
		return "the service Shops is working on port " + env.getProperty("local.server.port");
	}

}
