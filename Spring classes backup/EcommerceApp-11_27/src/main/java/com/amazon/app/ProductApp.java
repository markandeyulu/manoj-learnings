package com.amazon.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.app.beans.Product;
import com.amazon.app.services.ProductService;
@Component
public class ProductApp {

	@Autowired //without @component also this will work if the object is avaibalbe in container
	ProductService productService;


/*	public void setProductService(ProductService productService) {
		this.productService = productService;
	}*/

	public void displayAllProductDetails() {
		List<Product> products = productService.getAllProducts();
	
		products.forEach(System.out::println);//only available in java 8 - called lamda syntax
		
	}
	
	
}
