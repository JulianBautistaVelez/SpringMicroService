package com.julian.onlineshop.zuul.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	Environment environment;
	
	public WebSecurity(Environment environment) {
		super();
		this.environment = environment;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		String[] shopsSwaggerUrls = environment.getProperty("shops.swagger.urls.paths").split(",");
		String[] productsSwaggerUrls = environment.getProperty("products.swagger.urls.paths").split(",");
		
		http.authorizeRequests()
		.antMatchers(environment.getProperty("shops.h2console.url.path")).permitAll()
		.antMatchers(environment.getProperty("products.h2console.url.path")).permitAll()
		.antMatchers(shopsSwaggerUrls).permitAll()
		.antMatchers(productsSwaggerUrls).permitAll()
		.antMatchers(HttpMethod.POST,environment.getProperty("shops.registration.url.path")).permitAll()
		.antMatchers(HttpMethod.POST,environment.getProperty("shops.login.url.path")).permitAll()
		.antMatchers(HttpMethod.GET,environment.getProperty("products.listAll.url.path")).permitAll()
		.antMatchers(HttpMethod.GET,environment.getProperty("products.listByShop.url.path")).permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new AuthorizationFilter(authenticationManager(),environment));
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
