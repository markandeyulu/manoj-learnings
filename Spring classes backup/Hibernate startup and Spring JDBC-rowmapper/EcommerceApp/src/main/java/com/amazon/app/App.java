package com.amazon.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	
    	context.getEnvironment().setActiveProfiles("testing");
    	context.register(Config.class);
    	context.refresh();
    	
    	ProductApp productApp = context.getBean(ProductApp.class);
    	
    	productApp.diplayAllProductDetails();
    }
}
