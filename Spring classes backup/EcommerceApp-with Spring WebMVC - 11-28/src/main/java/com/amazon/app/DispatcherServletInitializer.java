package com.amazon.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//same as web.xml
public class DispatcherServletInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException { //onstartup - front controller. This is teh interface from WebApplicationInitializer 
		
		//step 1: Create a Web IoC context
		// This is exclusively dedicated for web mvc
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		
		//step 2: register the config class
		context.register(WebConfigFile.class);
		
		//step 3: register the dispatcher servlet ( Enabling the DS )
		// who is gonna act as front controller
		
		ServletRegistration.Dynamic registerServlet = servletContext.addServlet("dispatcherServlet",
				new DispatcherServlet(context));
		//tghis is same like registering web.xml to mvc
		
		
		//step 4: Loading the Servlet (order)
		registerServlet.setLoadOnStartup(1);
		
		//step 5: URL mapping. any request should come to the dispatchservlet first
		registerServlet.addMapping("/"); // front controller
		
	}
}
