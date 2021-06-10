package com.example.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

@Service
//prefix for the property and getter setter will take care rest
@ConfigurationProperties(prefix="product")// It is from BOOT exclusively and not in Spring traditional framework
public class OfferService {

/*	//Spring approach, Spring boot approach is getter setter and @ConfigurationProperties. If you have 10 to 20 property entries life will be easier
	//@Value("${product.offer}")//SPEL //profile value from application-dev.properties. you have to add this in bootclass(BootEcommerceApplication) to let Boot know we have more than one prop file. thats y its an array - @PropertySource(value = {"application-dev.properties"})
	private float offerPrice;
	
	//IOC container cant recognise this value - java.lang.IllegalArgumentException: Could not resolve placeholder 'product.offer' in value "${product.offer}"
	//it will pick only application.properties or application.yml. So we need to @resource
	
	/*	//URL context will be in controller
	public float getOfferPrice() {
		
		return offerPrice;
	}*/// you dont need seperate getter to return the values if you go with Boot approach to read property file @@ConfigurationProperties and getter setters
	

	
	//Boot approach //@ConfigurationProperties and getter setters. you dont need seperate getter to return the data
	@Value("#{${custom.offer} * 5}")
	private float offer;//same as in property file
	@Value("#{${custom.expiry} + 10}")
	private int expiry;//same as in property file

	@Value("${custom.name}")
	private String name;
	
	@Value("#{'${custom.name}'.concat(' asdf Manoj')}")
	private String desc;
	
	ExpressionParser expressionParser = new SpelExpressionParser();
	private String remarks = expressionParser.parseExpression("'Hello World'.concat('!')").getValue(String.class);
	
	public String getRemarks() {
		return remarks;
	}
	
	public int getExpiry() {
		return expiry;
	}

	public void setExpiry(int expiry) {
		this.expiry = expiry;
	}

	public float getOffer() {
		return offer;
	}

	public void setOffer(float offer) {
		this.offer = offer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
