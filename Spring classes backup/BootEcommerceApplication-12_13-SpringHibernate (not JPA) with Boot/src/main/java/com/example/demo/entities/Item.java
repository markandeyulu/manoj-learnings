package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="item")
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int itemId;
	
	private String itemName;
	
	private float itemPrice;

	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name="cartId")
    private Cart cart;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int item_id) {
		this.itemId = item_id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String item_name) {
		this.itemName = item_name;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
