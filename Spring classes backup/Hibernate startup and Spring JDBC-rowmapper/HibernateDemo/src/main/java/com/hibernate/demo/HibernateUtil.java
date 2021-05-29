package com.hibernate.demo;



import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.sun.xml.internal.ws.wsdl.writer.document.Service;

public class HibernateUtil {

	private static SessionFactory sessionFactory = createSessionFactory(); // why this need to be private one ? - To create only one session - singleton
	
	public static SessionFactory getSessionFactory( ) {
		return sessionFactory;
	}
	
	private static SessionFactory createSessionFactory() {
		
		SessionFactory sessionFactory = null;
		
		try {
			
			Configuration config = new Configuration();
			
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
			
			//ssrb.configure();//Read setting information from an XML file using the standard resource location.
			ssrb.configure("hibernate.cfg.xml");//same as above

			ServiceRegistry registry = ssrb.build();
			
			sessionFactory = config.buildSessionFactory(registry);
		
			//Here we get the config file and create session factory which will be used to query
			
			
		}
		
		catch(Exception ex) {
			ex.printStackTrace(); // if you have wrongly configured xml or file is missing
		}
		
		return sessionFactory;
	}
	
}
