package com.julian.onlineshop.api.shop.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julian.onlineshop.api.shop.data.ShopEntity;
import com.julian.onlineshop.api.shop.data.ShopsRepository;
import com.julian.onlineshop.api.shop.shared.ShopDto;

@Service
public class ShopsServiceIMPL implements ShopsService {
	
	ShopsRepository shopsRepository;
	
	@Autowired
	public ShopsServiceIMPL(ShopsRepository shopsRepository) {
		super();
		this.shopsRepository = shopsRepository;
	}

	@Override
	public ShopDto createShop(ShopDto shopDetails) {
		
		shopDetails.setShopID(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ShopEntity shopEntity = modelMapper.map(shopDetails, ShopEntity.class);
		shopEntity.setEncryptedPassword("TEST");
		
		shopsRepository.save(shopEntity);
		
		return null;
	}


}
