package com.FaceBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.FaceBook.Service.RentalService;
import com.FaceBook.config.faceBookConfiguration;


public class App {

/*	@Autowired // will be executed during startup - like static - and any class 
     @Autowired should be at the static level before creating @autowire of the class level variables. 
     Will be seen in Spring boot
	static RentalService rentalService1;
	@Autowired
	static RentalService rentalService2;
	
	you can achieve this here - 		RentalService rentalService1 = (RentalService) context.getBean(RentalService.class);
*/
	
	
	
	public static void main(String[] args)
	{
		
		ApplicationContext context = new AnnotationConfigApplicationContext(faceBookConfiguration.class);
		
		//RentalService rentalService = context.getBean(RentalService.class);
		//If we have multiple object to be returned, go for the bean name.(if you go by class you will get error)
		//RentalService rentalService = (RentalService) context.getBean("rs");//object has to be typecasted if we call object with bean name
/*		RentalService rentalService1 = (RentalService) context.getBean(RentalService.class);
		RentalService rentalService2 = (RentalService) context.getBean(RentalService.class);
		*/ //instead this - try autowire - which has to be static or lets say @Component should be created during the @autowire time.
		
		
		RentalService rentalService1 = (RentalService) context.getBean(RentalService.class);
		RentalService rentalService2 = (RentalService) context.getBean(RentalService.class);
		
		String msg1 = rentalService1.showRefinedMessage();
		String msg2 = rentalService2.showRefinedMessage();
		
		System.out.println(msg1);
		
		//((ConfigurableApplicationContext)context).close();
	}
}
