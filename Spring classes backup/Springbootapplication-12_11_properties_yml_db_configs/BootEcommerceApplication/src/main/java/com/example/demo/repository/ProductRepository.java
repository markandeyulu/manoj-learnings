package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.beans.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

	@Query(value = "SELECT * FROM Product p WHERE p.id in (1,2,3) order by p.name ", nativeQuery = true)
	public abstract Collection<Product> findAllActiveProductsNative();
}
