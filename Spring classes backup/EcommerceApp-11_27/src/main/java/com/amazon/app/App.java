package com.amazon.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    	//Profiling - which set of objects I want to load in memory in container 
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.getEnvironment().setActiveProfiles("production"); // to set the profile by coding. check AnnotationConfigApplicationContext is used instead interface.
    	// you can choose any number of profiles by comma for different instances. testing,production, etc. If you give multiple profile, you will have multiple objects for that profiled objects created in memory. 
		//that time if you want to use autowire you have to use qualifier with bean name to get the object you need.
    	context.register(Config.class); // order is very important. first plain object. set profile and register them to class. then refresh.
    	context.refresh(); // Between code and property file, compiler will give you priority to code.
        
        ProductApp productApp = context.getBean(ProductApp.class);//you cant use @autowire in main since object should have been created before
        
        productApp.displayAllProductDetails();
    }
}
