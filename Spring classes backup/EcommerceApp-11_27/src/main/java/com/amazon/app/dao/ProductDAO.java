package com.amazon.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amazon.app.beans.Product;
//@Component , when you have both @component and @bean, @component will take priority. and it will look for default constructor. 
public class ProductDAO {

	public String name;
	public ProductDAO(String name) {
		this.name = name;
		//System.out.println(name);
	}
	
	/* 
	 * 
	 * 	public ProductDAO(@Value=("Manoj") String name) { // @value is for component when you use this parameterised constructor
		this.name = name;
		//System.out.println(name);
	}
	 * */
	static List<Product> products;
	
	static {
		products = new ArrayList();
		Product p1 = new Product(101,"IPad", 344.45f);
		Product p2 = new Product(102,"RaspberryPI", 654.25f);
		Product p3 = new Product(103,"Wallet", 12.32f);
		Product p4 = new Product(104,"Laptop", 4564.45f);
		Product p5 = new Product(105,"smartWatch", 234.56f);
		
		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
		
	}
	public List<Product> getProducts() {
		
		return products;
		
	}
}
