package com.amazon.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.app.beans.Product;
import com.amazon.app.services.ProductService;

//@Component
public class ProductApp {

	@Autowired
	ProductService productService;

	/*public void setProductService(ProductService productService) {
		this.productService = productService;
	}*/
	
	public void diplayAllProductDetails() {
		List<Product> products = productService.getAllProducts();
		
		products.forEach(System.out::println);
	}
}
