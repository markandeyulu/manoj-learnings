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
	//@Qualifier("p2")
	ProductDAO productDAO ;

	/*public ProductService(ProductDAO productDAO) {		
		this.productDAO = productDAO;
	}*/
	
	public List<Product> getAllProducts(){
		//System.out.println(productDAO.name);
		return productDAO.getProducts();
	}
	
	
	
}
