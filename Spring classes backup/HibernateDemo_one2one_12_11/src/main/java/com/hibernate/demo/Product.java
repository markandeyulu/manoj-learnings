package com.hibernate.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;//both are from JPA. Its there in hibernate as well but striked out.
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity // very imp Hibernate engine will understand// class level annotation // This table will be created in DB by taking the config.xml details if we give settings as create hibernate.hbm2ddl.auto=create. 
// In cfg.xml we mention class as Product, by which the SessionFactory knows which class it has to look for entity.
// practically table would be created in DB directly // every time we run it will drop and create a table // never leave DDL to be done by hibernate - good practice


//@NamedQuery(name="p1", query="from Product")//alias prodId,prodName are the class variable name - case sensitive //Named query. We can create N number of named query we wanted. we can call this with the reference string p1, p2 etc.
//@NamedQuery(name="p1", query="select p.prodName from Product p where p.prodId=:pid")
//@NamedQuery(name="p1", query="select p.prodName, p.prodPrice from Product p where p.prodId=:pid")
@NamedQuery(name="p1", query="select p.prodName, p.prodPrice from Product p")
//@NamedNativeQuery(name="p2", query="select * from techm_product where prodid=103")
//@NamedNativeQuery(name="p2", query="select * from techm_product where prodid=?", resultClass=Product.class)
@NamedNativeQuery(name="p2", query="select * from techm_product where prodid=:pid", resultClass=Product.class)


@Table(name="Techm_Product")// every time it will try to drop the table and create
public class Product implements Serializable{

	@Id // field level annotation // Id and entity is mandatory
	int prodId;

	//@Column(name="product_Name"/*, nullable=false, unique=true*/)// if you want diff column name. If its not there it will create column given that you dont drop table.
	String prodName;
	float prodPrice;
	
	
	
	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", prodPrice=" + prodPrice + "]";
	}


	public Product() {
		
	}
	
	
	public Product(int prodId, String prodName, float prodPrice) {// we can avoid setter with this
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	
	@Transient//  marked as Non-Persistent. wont be mapped as a column. This will be a normal java variable.
	int calculateInterest;
	
	public int getCalculateInterest() {
		return calculateInterest;
	}
	public void setCalculateInterest(int calculateInterest) {
		this.calculateInterest = calculateInterest;
	}
	
	
	
	
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public float getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(float prodPrice) {
		this.prodPrice = prodPrice;
	}

	
	
	
}
