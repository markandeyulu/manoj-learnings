package com.hibernate.demo;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App1 {

	public static void main(String[] args) {
		
		//Creteria query - to remove SQL dependency
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		//Step 1: get the Builder object to Build crieria
		CriteriaBuilder builder = session.getCriteriaBuilder(); // hibernate 5
		

		//Step 2 : get the Criteria query object.. 
		
		/*3 types of query
		Query
		NativeQuery
		CriteriaQuery
		*/
/*		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		
		// Step 3: Get the root entity
		Root<Product> root = criteria.from(Product.class);//from Product
		// where productid = xxx, for this root is needed, for equal/like operator you need to use root. root offers gt, gte, lt, lte etc. 
		// or/and everything is methods
		
		
		//criteria.select(Product.class) - to refer statement as select * // syntax not correct here
		// If you have * you dont need select. If you need individual column you need select
		
		//criteria.where(builder.equal(root.get(Product_.prodId), new Integer(104)));// where prodid=103. @StaticMetamodel need to be created
		//converts as HQL and not as Native query - select product0_.prodName as col_0_0_, product0_.prodPrice as col_1_0_ from Techm_Product product0_

		Predicate pred1 = builder.equal(root.get(Product_.prodId), new Integer(108));
		Predicate pred2 = builder.equal(root.get(Product_.prodName), new String("Wallet1"));
		Predicate finalPredicate = builder.and(pred1, pred2);// incase of 2 conditions for and
		//Predicate finalPredicate = builder.or(pred1, pred2);// incase of or
		criteria.where(finalPredicate);
		
		Query query = session.createQuery(criteria);// forming CriteriaQuery object and giving to Query object
		
		List<Product> products = query.getResultList();
		
		products.forEach(System.out::println);*/ //commenting this to test the multiple select returns
		
		
		
		
		
		
		
		//CriteriaQuery<String> criteria = builder.createQuery(String.class);//If you want only prod name in select
		CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);// for multiple returns
		// Step 3: Get the root entity
		Root<Product> root = criteria.from(Product.class);
		//criteria.select(root.get(Product_.prodName));//If you want only prod name in select
		criteria.multiselect(root.get(Product_.prodName), root.get(Product_.prodId));
		Predicate pred1 = builder.equal(root.get(Product_.prodId), new Integer(108));
		Predicate/* it should not be java 8 predicate */ pred2 = builder.equal(root.get(Product_.prodName), new String("Wallet1"));
		Predicate finalPredicate = builder.and(pred1, pred2);// incase of 2 conditions for and
		//Predicate finalPredicate = builder.or(pred1, pred2);// incase of or
		criteria.where(finalPredicate);
		
		Query query = session.createQuery(criteria);// forming CriteriaQuery object and giving to Query object
		
		//List<String> products = query.getResultList();//If you want only prod name in select //query is  select product0_.prodName as col_0_0_ from Techm_Product product0_ where product0_.prodId=108 and product0_.prodName=?
		List<Object[]> products = query.getResultList();//query -  select product0_.prodName as col_0_0_, product0_.prodId as col_1_0_ from Techm_Product product0_ where product0_.prodId=108 and product0_.prodName=?
		products.forEach((obj) -> System.out.println(obj[0] + " : " + obj[1]));
		//products.forEach(System.out::println);
		// lower version of hibernate not having these many functionalities. but to satisfy 100% of needs and support java 8 functionalities they revamped many.
		
		
	}
	
	
}
