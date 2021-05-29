package com.amazon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amazon.app.beans.Product;
import com.amazon.app.dao.ProductDAO;

@Component
public class ProductService {

	@Autowired
	//@Qualifier("p2")//commenting this to try profiling // use this when you have 2 instances (
	// as given beklow @Bean
/*	public ProductDAO getProductDAO1() {
		return new ProductDAO();
	}

	@Bean
	public ProductDAO getProductDAO2() {
		return new ProductDAO();
	})*/
	ProductDAO productDAO;
	
/*	public ProductService(ProductDAO productDAO) {//construction based injection - one type of manual wiring ( setter based also same case)
		this.productDAO = productDAO;
	}
	*/
	public List<Product> getAllProducts() {
		System.out.println(productDAO.name);
		return productDAO.getProducts();
	}
}
