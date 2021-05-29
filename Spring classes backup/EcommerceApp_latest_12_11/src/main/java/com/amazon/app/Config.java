package com.amazon.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.amazon.app.dao.ProductDAO;
import com.amazon.app.services.ProductService;

@Configuration
//@ComponentScan
//@PropertySource("classpath:/application.properties")
public class Config {

	@Bean("p1")
	@Profile("testing")
	public ProductDAO getProductDAO1() {
		return new ProductDAO("Testing");
	}
	
	@Bean("p2")
	@Profile("production")
	public ProductDAO getProductDAO2() {
		return new ProductDAO("Production");
	}
	
	/*
	@Bean
	public ProductService getProductService() {
		//ProductService productService = new ProductService(getProductDAO());
		ProductService productService = new ProductService();
		return productService;
		
	}*/
	
	/*@Bean
	public ProductApp getProductApp() {
		ProductApp productAPP = new ProductApp();
		//productAPP.setProductService(getProductService());
		return productAPP;
	}*/
}
