package com.julian.onlineshop.api.shop.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julian.onlineshop.api.shop.model.LoginRequestModel;
import com.julian.onlineshop.api.shop.service.ShopsService;
import com.julian.onlineshop.api.shop.shared.ShopDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	ShopsService shopsService;
	Environment environment;
	
	@Autowired
	public AuthenticationFilter(
			ShopsService shopsService, 
			Environment environment, 
			AuthenticationManager authenticationManager) {
		super();
		this.shopsService = shopsService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication( 
			HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException {
		
		try {
			LoginRequestModel credentials = new ObjectMapper()
					.readValue(req.getInputStream(), LoginRequestModel.class);
			
			
			
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							credentials.getName(), 
							credentials.getPassword(),
							new ArrayList<>())
					
					);
					
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override 
	protected void successfulAuthentication(
			HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException{
		
		String shopName =  ((User) auth.getPrincipal()).getUsername();
		ShopDto shopDetails = shopsService.getShopDetailsByName(shopName);
		
		Date expirationDate = new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time")));
		
		String token = Jwts.builder()
				.setSubject(shopDetails.getShopID())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.sign"))
				.compact();
		
		res.addHeader("token", token);
		res.addHeader("shopId", shopDetails.getShopID());

	}
	

}
