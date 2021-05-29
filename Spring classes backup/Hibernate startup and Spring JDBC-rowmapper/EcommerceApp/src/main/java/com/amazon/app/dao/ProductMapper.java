package com.amazon.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.amazon.app.beans.Product;

public class ProductMapper implements RowMapper<Product>{//for each table(domains) mapper will be diff -- Spring data JPA ?

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(rowNum);
		//Object Relational Mapping ORM is diff. This is rowMapper not ORM.
		// Can we outsource this job ? Yes ORM is there. Hibernate is one of them.
		// you dont need to do this row mappings in every place where we query the data.
		Product product = new Product();
		product.setProdId(rs.getInt(1));
		product.setProdName(rs.getString(2));
		product.setProdPrice(rs.getFloat(3));
		// you can write this only once not many times as in normal JDBC
		
		
		return product;
	}
	
	

}
