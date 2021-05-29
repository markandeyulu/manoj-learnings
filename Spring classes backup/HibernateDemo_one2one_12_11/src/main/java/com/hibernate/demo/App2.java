package com.hibernate.demo;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App2 {

	public static void main(String args[]) {
	
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		
	/*	session.beginTransaction();
		
		Address address = new Address();
		address.setCityName("Bengaluru");
		address.setState("Karnataka");
		
		Person person = new Person();
		person.setPersonName("Manoj30");
		person.setPersonAge(30);
		person.setAddress(address);
		
		
		//session.save(address);// this order is imp. 1st address need to be created. If you dont have this you should have cascade option in one to many annotation. like @OneToOne(cascade=CascadeType.ALL). Integrity will be maintained. For removal integrity do @OneToOne(cascade=CascadeType.REMOVE)
		//if cascading not happening developer has to take care
		session.save(person);
		*/
		
/*
 *  //Getting table values
		Person person = session.get(Person.class, 1);
		
		System.out.println(person.getPersonName());
		System.out.println(" : StaysIn : ");
		
		Address address = person.getAddress();
		System.out.println(address.getCityName());
		
		
		Hibernate: select person0_.personId as personId1_1_0_, person0_.personAge as personAge2_1_0_, person0_.personName as personName3_1_0_, address1_.addressId as addressId1_0_1_, address1_.cityName as cityName2_0_1_, address1_.state as state3_0_1_ from Person person0_, Address address1_ where person0_.personId=address1_.addressId(+) and person0_.personId=?
				Manoj30
				 : StaysIn : 
				Bengaluru
	
		If you have getter setter for person in java you can call person in java as well. thats bi directional
		
		@OneToOne(mappedBy="address")
		Person person;
		*/
	
		
		
		session.beginTransaction();
/*		Address address = new Address();
		address.setCityName("Bengaluru1");
		address.setState("Karnataka1");
		
		Person person = new Person();
		person.setPersonName("Manoj31");
		person.setPersonAge(31);
		person.setAddress(address);
		*/
		
		Person person = session.get(Person.class, 1);
		
		System.out.println(person.getPersonName());
		System.out.println(" : StaysIn : ");
		
		Address address = person.getAddress();
		System.out.println(address.getCityName());
		
		//session.save(person);
		session.getTransaction().commit();
		
	}
	
	
}
