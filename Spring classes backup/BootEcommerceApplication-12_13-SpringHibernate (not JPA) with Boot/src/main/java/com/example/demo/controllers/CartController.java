package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Cart;
import com.example.demo.services.CartService;


@RestController
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/saveCart")
	public boolean saveCart(@RequestBody Cart cart) {
		return cartService.save(cart);
		
	}
	
	@GetMapping("/allCarts")
	public List<Cart> allCarts() {
		return cartService.findAll();
	}
	
}
