package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartDAO;
import com.example.demo.entities.Cart;


@Service // why we are not using component here? - we can use that as well. But absolute is service.
public class CartService {

	@Autowired
	CartDAO cartDAO;
	
	//CRUD operations
	public boolean save(Cart cart) {
		try {
			return cartDAO.save(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Cart> findAll() {
		return cartDAO.findAll();
	}
}
