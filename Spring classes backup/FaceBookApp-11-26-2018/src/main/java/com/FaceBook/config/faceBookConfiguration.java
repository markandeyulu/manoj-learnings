package com.FaceBook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.FaceBook.Locator.StoreLocater;
import com.FaceBook.Service.RentalService;
@PropertySource(value= {"classpath:/resources/application.properties"}) //To initialize the property value to be used in classes // Can be given multiple file seperated with comma // read more about it
//Step1 : First we need to declare this class as a configuraion
//@Configuration

@Configuration 
//If you remove @Configuration and having @bean it will be treated as notmal java file called LITE mode.
//Container itself will create instance for any of stereo type annotaions.
//@Component, @Service, @Controller, @Repository & @Configuration & @RestController- for all the auto instance will be created


@ComponentScan(basePackages = { "com.FaceBook.Locator" , "com.FaceBook.Service"})// If all classes under (parent) this, this is not needed
//To scan the Component classes - This is generic for @Service @Repository @Controller n all streotype annotations
// This will scan only the Child classes and not the siblings
public class faceBookConfiguration {

//Step 2:
	//@Bean //- If you comment its not relevent to container at all. It wont create bean in Spring container by default.
	//If not commented singleton object will be created and share with RentalService everytime
	//@Bean is necessery to instruct container. if not that is a normal developer code.  called @Bean LITE mode
	//@Scope("prototype")//If you want the context instance(object) to be created for this class everytime. if not singleton.
	//If you remove @Configuration and having @bean it will be treated as normal java file called LITE mode.
	//default scope - singleton
	
	
	//@Component //to create auto instance - Will it work ? - try //We can try with @AutoWired too. 
/*	@Bean
	public StoreLocater getStoreLocator() {
		System.out.println("Inside Store Locator");
		return new StoreLocater();
	}*/
//Step 3: 
	
/*	@Autowired//to create auto instance - same as @Component
	StoreLocater storeLocator;
	
	*/

	@Autowired//to create auto instance - same as @Component
	StoreLocater storeLocator;
	
	
	
/*	//@Bean(name = "rs")
	@Bean
	@Scope("prototype")//If you want the context instance(object) to be created for this class everytime. if not singleton.
	public RentalService getRentalService() {
		System.out.println("Inside Rental Service");
		RentalService rs = new RentalService();
		//rs.setStoreLocator(getStoreLocator());
		rs.setStoreLocator(storeLocator);// to test Component Scan
		return rs;
	}
	*/
	
}
