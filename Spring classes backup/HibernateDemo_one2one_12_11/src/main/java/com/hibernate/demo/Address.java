package com.hibernate.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)// who will generate this ? Hibernate or Oracle ?
	private int addressId;
	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String city) {
		this.cityName = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	private String cityName;
	private String state;
	
/*
 * 
 * 	@OneToOne(mappedBy="address")
	Person person;
*/	

	
	
	public Address(int addressId, String city, String state) {
		super();
		this.addressId = addressId;
		this.cityName = city;
		this.state = state;
	}


	public Address() {
		// TODO Auto-generated constructor stub
	}
	
}
