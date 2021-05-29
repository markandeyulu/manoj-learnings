package com.FaceBook.Locator;

import org.springframework.stereotype.Component;

@Component //part of streotype annotations
//It will reduce the manual coding in Config class to create instance and return (@Bean - IMP)
//with the help of this life become easier
// you can comment this in FaceBook configuration. We have to do the some work in config class there too. Either @autowired or @Component.

/*
@Bean
	public StoreLocater getStoreLocator() {
		System.out.println("Inside Store Locator");
		return new StoreLocater();
	}

 */
public class StoreLocater {

	
	public String getMessage() {
		return "Welcome to Store Locator";
	}
}
