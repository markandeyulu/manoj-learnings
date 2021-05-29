package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BootEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEcommerceApplication.class, args);
	}
	
	/*
	 * We have automated all the below lines
	 * 
	 * 		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		
		//step 2: register the config class
		context.register(WebConfigFile.class);
		
		//step 3: register the dispatcher servlet ( Enabling the DS )
		// who is gonna act as front controller
		
		ServletRegistration.Dynamic registerServlet = servletContext.addServlet("dispatcherServlet",
				new DispatcherServlet(context));
		//tghis is same like registering dispatchservlet.xml to web.xml in Spring MVC XML way
		
		
		//step 4: Loading the Servlet (order)
		registerServlet.setLoadOnStartup(1);
		
		//step 5: URL mapping. any request should come to the dispatchservlet first
		registerServlet.addMapping("/"); // front controller
		
	 */
	@GetMapping("/welcome")
	public String getMessage() {//Spring MVC operation can be done easily here // outside of main.
		return "Welcome to Boot";
	}
}
