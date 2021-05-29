package com.amazon.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.amazon.app.dao.ProductDAO;
import com.amazon.app.services.ProductService;


@Configuration
@ComponentScan
//@PropertySource("classpath:/application.properties")//used for profiling
public class Config {
	
	
/*	@Bean
	public ProductDAO getProductDAO1() {
		return new ProductDAO();
	}

	@Bean
	public ProductDAO getProductDAO2() {
		return new ProductDAO();
	}*/ // you will get error when you use autowired for this combo

	
	@Bean("p1")
	@Profile("testing") //need to set run config as -Dspring.profiles.active=testing
	//Profiling - which set of objects I want to load in memory in container
	public ProductDAO getProductDAO1() {//method name is the bean name
		return new ProductDAO("testing");
	}

	@Bean("p2") // explicitely creating name for the bean. if not method name will be taken 
	@Profile("production") // how to do this in component need to be checked
	public ProductDAO getProductDAO2() {
		return new ProductDAO("production");
	} // though we create 2 instances its still singleton pattern because p1 n p2 are singleton objects
	
/*	@Bean
	public ProductDAO getProductDAO() {
		return new ProductDAO();
	}
	
	@Bean
	public ProductService getProductService() {
		ProductService productService = new ProductService();//will not work since we created constructor based injector. In last case we have seen the Setter based injection. This is Constructor based injection.
		//ProductService productService = new ProductService(getProductDAO());//doing the wiring - manual wiring .. //If constructor is not thetre in ProductService you will get null pointer.. Comment this and use autowire there. it will work the same.
		return productService;
	}

	@Bean// this should be there since we dont use @component.
	public ProductApp getProductApp() {
		ProductApp productApp = new ProductApp();
		//productApp.setProductService(getProductService());//setter based injection.. comment this and use autowiring. It will work the same
		return productApp;
	}
	*///commenting to use @component
}
