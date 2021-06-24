package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)//we have given exclude since HibernateJPA and other colloided
@PropertySource(value = {"application-dev.properties", "application-test.properties"})// if you load multiple which is last in the order will picked up if you have same entry unless you mention spring.profiles.active=dev in application.properties or application.yml. If you set that it will take dev. // .yml will not work for this annotation. If you want YML based go for profile based switch

//@EnableTransactionManagement //(Spring data JPA's) -  no need to  configure txn manager(Spring integrated with hibernate), boot will tell container to get txn manager here. Advantage of Spring data JPA. 
//@RestController
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
/*	@GetMapping("/welcome")
	public String getMessage() {//Spring MVC operation can be done easily here // outside of main.
		return "Welcome to Boot";
	}*/
	
	
	// better dont use this class for any config like above /welcome
}
