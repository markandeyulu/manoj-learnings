package com.amazon.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazon.app.beans.Product;
//@Component
public class ProductDAO {

	public String name;
	public ProductDAO(@Value("sabari") String name) {
		this.name=name;
		System.out.println(name);
	}
	
	static List<Product> products;
	
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
	}
	
	public List<Product> getProducts(){
		return products;
	}
/*	
	public List<Product> getProduct(String productName) {

		return products.stream()
				.filter((p) -> p.getProdName().equals(productName)) //java 8 - lamda expression - learn - mandate - learn
				.collect(Collectors.toList()); //((p) -> p1=p
	
	}
*/
	public List<Product> getProduct(String productName) throws Exception {

		List <Product> productInfo = products.stream()
				.filter((p) -> p.getProdName().equals(productName))
				.collect(Collectors.toList());
	
		if(productInfo.isEmpty()) {
			throw new Exception("Sorry the given Product Name not Available"); // we should have give ProductNotFoundException which we need to create our own exception
		}
		else {
			return productInfo;
		}
	}
	

	
	public boolean addProduct(Product product) {
		products.add(product);
		return true;
	}
	
}
