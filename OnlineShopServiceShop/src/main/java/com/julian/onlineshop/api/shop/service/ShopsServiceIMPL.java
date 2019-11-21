package com.julian.onlineshop.api.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julian.onlineshop.api.shop.data.ServiceProductClient;
import com.julian.onlineshop.api.shop.data.ShopEntity;
import com.julian.onlineshop.api.shop.data.ShopsRepository;
import com.julian.onlineshop.api.shop.model.ProductResponseModel;
import com.julian.onlineshop.api.shop.shared.ShopDto;

@Service
public class ShopsServiceIMPL implements ShopsService {
	
	ShopsRepository shopsRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	ServiceProductClient serviceProductClient;
	
	@Autowired
	public ShopsServiceIMPL(
			ShopsRepository shopsRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder,
			ServiceProductClient serviceProductClient) {
		super();
		this.shopsRepository = shopsRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.serviceProductClient = serviceProductClient;
	}

	@Override
	public ShopDto createShop(ShopDto shopDetails) {
		
		shopDetails.setShopID(UUID.randomUUID().toString());
		shopDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(shopDetails.getPassword()));
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ShopEntity shopEntity = modelMapper.map(shopDetails, ShopEntity.class);
		
		
		shopsRepository.save(shopEntity);
		
		ShopDto returnValue = modelMapper.map(shopEntity, ShopDto.class);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String shopName) throws UsernameNotFoundException {
		ShopEntity shopEntity = shopsRepository.findByName(shopName);
		
		if(shopEntity == null) throw new UsernameNotFoundException(shopName);
		else return new User(shopEntity.getName(),shopEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public ShopDto getShopDetailsByName(String shopName) {
		ShopEntity shopEntity = shopsRepository.findByName(shopName);
		
		if(shopEntity == null) throw new UsernameNotFoundException(shopName);
		return new ModelMapper().map(shopEntity, ShopDto.class);
	}

	@Override
	public ShopDto getShopById(String shopID) {
		ShopEntity shopEntity = shopsRepository.findByShopID(shopID);
		
		if(shopEntity == null) throw new UsernameNotFoundException(shopID);
		return new ModelMapper().map(shopEntity, ShopDto.class);
	}
	
	@Override
	public ShopDto getFullShopById(String shopID) {
		ShopEntity shopEntity = shopsRepository.findByShopID(shopID);
		
		if(shopEntity == null) throw new UsernameNotFoundException(shopID);
		 ShopDto shopDto = new ModelMapper().map(shopEntity, ShopDto.class);
		 
		 List<ProductResponseModel> products = serviceProductClient.getProducts(shopID);
		 
		 shopDto.setProducts(products);
		 
		 return shopDto;
	}


}
