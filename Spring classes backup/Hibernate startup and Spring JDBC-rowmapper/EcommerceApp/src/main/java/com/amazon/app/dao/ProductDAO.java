package com.amazon.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazon.app.beans.Product;
import com.amazon.app.exceptions.ProductNotFoundException;
//@Component
public class ProductDAO {

	
	
	@Autowired
	JdbcTemplate jdbcTemplate;//Spring JDBC 
	//public JdbcTemplate getTemplate() { // will be defaultly taken by JdbcTemplate
	
	
	
	
	
	
	
	
	
	
	public String name;
	public ProductDAO(@Value("sabari") String name) {
		this.name=name;
		System.out.println(name);
	}
	
	static List<Product> products;
/*	
	static {
		products = new ArrayList<>();
		Product p1 = new Product(101,"IPad",344.45f);
		Product p2 = new Product(102,"RaspberryPI",654.2f);
		Product p3 = new Product(103,"Wallet",12.32f);
		Product p4 = new Product(104,"IPad",4564.45f);
		Product p5 = new Product(105,"SmartWatch",234.56f);
		Product p6 = new Product(106,"SmartWatch",34.5f);
		
		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
		products.add(p6);
	}*/ //commented to get from DB
	
	
/*public List<Product> getProducts(){
		
		return products;
	}
*/
	public List<Product> getProducts() {

		return jdbcTemplate.query("select * from product", new ProductMapper());//query is same like executequery in jdbc
		// Normally we should have got result set and do loop for rowmapping and then show the data. Here we dont need to do the row mapping
	}

	/*	
	public List<Product> getProduct(String productName) {

		return products.stream()
				.filter((p) -> p.getProdName().equals(productName)) //java 8 - lamda expression - learn - mandate - learn
				.collect(Collectors.toList()); //((p) -> p1=p
	
	}
*/
/*	public List<Product> getProduct(String productName) throws Exception {

		List <Product> productInfo = products.stream()
				.filter((p) -> p.getProdName().equals(productName))
				.collect(Collectors.toList());
	
		if(productInfo.isEmpty()) {
			//throw new ExceptionException("Sorry the given Product Name not Available"); // we should have give ProductNotFoundException which we need to create our own exception
			throw new ProductNotFoundException("Sorry the given Product Name not Available"); // This can be handled controller advice or controller too
		}
		else {
			return productInfo;
		}
	}*/
	
	public List<Product> getProduct(String productName) throws Exception {

	
		List <Product> prod = jdbcTemplate.query("select * from product where prodname=?", 
				new Object[] {productName}, new ProductMapper());// you dont need to write result set coding again n again. use prodmapper
		//Object[] to have multiple placeholder with diff datatype. We can have multiple placeholders
		// Normally we should have got result set and do loop for rowmapping and then show the data. Here we dont need to do the row mapping
		
		List <Product> productInfo = prod.stream()
				.filter((p) -> p.getProdName().equals(productName))
				.collect(Collectors.toList());
		
		if(productInfo.isEmpty()) {
			throw new ProductNotFoundException("Sorry the given Product Name not Available"); // This can be handled controller advice or controller too
		}
		else {
			return productInfo;
		}
	}

/*	
	public boolean addProduct(Product product) {
		products.add(product);
		return true;
	}*/
	
	
/*	public boolean addProduct(Product product) {
		int rows = jdbcTemplate.update("insert into product values(?,?,?)",
		product.getProdId(),product.getProdName(),product.getProdPrice());
		//query can be avoided with ORM(hibernate is one of ORM)
		//Traditionally we go via raw JDBC tamplate. But here we abstract everything into the Jdbc template(in spring jdbc)
		if(rows > 0) {
			return true;
		}
		
		return false;
	}*/
	
	
	public boolean addProduct(Product product) {
		int rows = jdbcTemplate.update("insert into product values(?,?,?)",
		product.getProdId(),product.getProdName(),product.getProdPrice());
		//query can be avoided with ORM(hibernate is one of ORM)
		//Traditionally we go via raw JDBC tamplate. But here we abstract everything into the Jdbc template(in spring jdbc)
		if(rows > 0) {
			return true;
		}
		
		return false;
	}
	
	//Spring JDBC template - no need to write line by line JDBC . we can use (ORM) Hibernate too.
	
}
