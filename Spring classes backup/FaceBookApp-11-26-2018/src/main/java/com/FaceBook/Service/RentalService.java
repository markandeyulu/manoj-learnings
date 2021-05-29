package com.FaceBook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.FaceBook.Locator.StoreLocater;

@Component // no need of any code in config class , @Service also will do the same. Whats the diff ? In overall class when all layers seperated. @Service root is @Component.
@Scope("prototype")
public class RentalService {

/*	StoreLocater storeLocator;//setter based injection
	

	public void setStoreLocator(StoreLocater storeLocator) {
		System.out.println("Setter called");
		this.storeLocator = storeLocator;
	}*/ // instead see autowired below to do the same


	@Autowired//instead setter based injection.
	//autowire is same as you get the object and set in setter.
	// it will be have same as constructor based injection
	StoreLocater storeLocator;//it will get the instance( the object should have been created either by @Component or @bean for that class) and set to hiddensetter.

	/*@Value("${user.names}") // Initially system variables will be considered. ex - user.name will give you machine name. // value annotation // For this to be executed, Config file should be having @PropertySource mentioned.
	//Can we achieve this without SPeL($ - Spring Expression Language) which is system duty to get the value ? 
	
	String name;
	
	
	public String showRefinedMessage() {
		return storeLocator.getMessage() + name;
	}
	*///Can we achieve this without SPeL name ?

	@Autowired
	Environment env; // can be given in Run config env variables too. That will be priority(System variable) and only property file priority
	// Priority Order is  propertySources=[systemProperties,systemEnvironment,class path resource [resources/application.properties]]}
	public String showRefinedMessage() {
		String name = env.getProperty("user.names");//without spell name
		System.out.println(env);
		return storeLocator.getMessage() + name;
	}
}
