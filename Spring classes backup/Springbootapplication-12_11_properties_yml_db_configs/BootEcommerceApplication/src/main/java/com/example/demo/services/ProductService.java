package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Product;
import com.example.demo.repository.ProductRepository;

//@Component we can use @service. Nothing harm. Just to precise in what this is doing
@Service
public class ProductService {

	/*Spring JDBC Template code
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 * 
	 * 
	 * public List<Product> getAllProductsFromDB() {
	 * 
	 * return jdbcTemplate.query("SELECT * FROM products",(rs, rowNum) -> new
	 * Product( rs.getInt("prodId"), rs.getString("prodName"),
	 * rs.getFloat("prodPrice"))); }
	 */
	
	@Autowired 
	ProductRepository productRepository;
	
	public List<Product> getAllProductsFromDB() {
		return productRepository.findAll();
	}
	
	public List<Product> getAllProducts() {
		
		List<Product> products = new ArrayList<>();
		
		products.add(new Product(101,"Watch", 435.35f));
		products.add(new Product(102,"Wallet", 123.35f));
		products.add(new Product(103,"Mobile", 45.35f));
		products.add(new Product(104,"IPad", 3453.35f));
		
		
		return products;
	}
	
	
}
