package com.hibernate.demo;

import java.util.List;

import javax.persistence.NamedQuery;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

public class App 
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        
        Session session = sessionFactory.openSession();
        
        // In JDBC we did rowmapper. was mapping manually from object to resultset and vice versa. Here the mapping is automated. ORM.
        
        /*//Select * from product where prodid='101'; 
        Product p = (Product) session.get(Product.class, 101);// @id field
        System.out.println(p.getProdId() + " : " + p.getProdName() + " : " + p.getProdPrice());
        */
        
        // HQL // get will return only one object - This is HQL- Hibernate Query Language. 
        //u can write native queries too.. 
        //Then we have HCQ too - hibernate creteria query, you can write java code to to query. unless a Stored Procedure we can go with that.
/*        Query query = session.createQuery("from Product");//HQL //Product is not a table name here. Table name is Techm_product. This is a class name
        //any queries  complex queries need to be given as input to hibernate since hibernate cant simplify our complex queries.
*/
        
        
        /*
        // Native query
        NativeQuery<Product> query = session.createNativeQuery("select * from techm_product where prodname='Wallet1'");
        query.addEntity(Product.class);// we will get ClassCastException if not this. need for native query.
        
        
        
        List<Product> list = query.getResultList();
        list.forEach(System.out::println);
        // not coming under transaction as we did for updates. querying is not a transaction
        */
        
        
        
        /*
        get/HQL/nativequery/cretiriaQL - query - no need to in transaction
        save - insert
        update - update
        delete - delete*/
      /*  
        session.beginTransaction();
        
        Product p1 = new Product(108,"Wallet1",3432.32f);
        Product p2 = new Product(109,"Wallet1",3432.32f);
        Product p3 = new Product(110,"Wallet1",3432.32f);
        
//commenting setters to check constructor
                p.setProdId(104); 
        p.setProdName("Wallet1");
        p.setProdPrice(3432.32f);// make create to update in property file to make sure table dont get dropped
        //hibernate.hbm2ddl.auto=update
        
        session.save(p1);
        session.save(p2);
        session.save(p3);
        session.getTransaction().commit();
        */
        
        
        
 /*       session.beginTransaction();
        Product p = (Product) session.get(Product.class, 102);
        session.delete(p);//delete from Techm_Product where prodId=?
                
        session.getTransaction().commit();
        session.close();
        */
        
        
        /*
        session.beginTransaction();
        Product p2 = (Product) session.get(Product.class, 104);
        p2.setProdName("TestName");//select product0_.prodId as prodId1_0_0_, product0_.prodName as prodName2_0_0_, product0_.prodPrice as prodPrice3_0_0_ from Techm_Product product0_ where product0_.prodId=?
        session.update(p2);
        
        
        session.getTransaction().commit();
        session.close();
        
        // above line is to create a row
         * Hibernate: insert into Techm_Product (prodName, prodPrice, prodId) values (?, ?, ?)
			SessionImpl(<closed>)
         
        */
 /*       
        //NamedQuery for HQL
        Query query = session.createNamedQuery("p1");// if we have only this we will get No query defined for that name [p1]. You have to to use @NamedQuery in Entity(Domain) class. @NamedQuery(name="p1", query="from Product")
        
        List<Product> products = query.getResultList();
        products.forEach(System.out::println);
 */       
/*        
        //NamedQuery for Native query        
        NativeQuery query = session.getNamedNativeQuery("p2");//createnamednative query is only in core hibernate. not in hibernate 5
        //query.addEntity(Product.class);// in query you dont mention the entity thats y you need to mention here explicitely. If you mention result class in named nativequery annotation(, resultClass=Product.class), you dont need to add class here.
        // In native query only you can add entity. Query is for HQL. NativeQuery is for SQL.
        // Named query advantage is reusability
        //query.setParameter(0, 103);//If you have query as named query in annotation, the placeholder starts from 0. If you write the query here itself without annotation(like session.createNativeQuery("select * from techm_product where prodname='Wallet1'")), placeholder starts with 1.. If you want to avoid these confusion, we can go with the named placeholders (pid).
        query.setParameter("pid", 103); // you can use name as well
        List<Product> products = query.getResultList();//from product query will return product object hence we have the List as product
        products.forEach(System.out::println);
        */
/*        
        
        Query query = session.createNamedQuery("p1");// if we have only this we will get No query defined for that name [p1]. You have to to use @NamedQuery in Entity(Domain) class. @NamedQuery(name="p1", query="from Product")
        query.setParameter("pid", 110);
        //List<String> products = query.getResultList();// for query select p.prodName from Product p where p.prodId=:pid we are getting only one param string. so we need to have list type as string and not product
        
        List<Object[]> products = query.getResultList();//row array // for multiple return data for query, @NamedQuery(name="p1", query="select p.prodName, p.prodPrice from Product p where p.prodId=:pid")
        Object obj[] = products.get(0);//column array // This is for single row result.
        
        System.out.println(obj[0]);//Wallet1
        System.out.println(obj[1]);//3432.32
		// how you will do if you have multiple row ? look below. products.forEach((obj) -> System.out.println(obj[0] + " : "+obj[1]));
        
        //products.forEach(System.out::println);
        
        */
  
        
        Query query = session.createNamedQuery("p1");
        
        List<Object[]> products = query.getResultList();// query - @NamedQuery(name="p1", query="select p.prodName, p.prodPrice from Product p")

        products.forEach((obj) -> System.out.println(obj[0] + " : "+obj[1]));// If you have multiple row you need to go this way

        //Only when we have select * in query, we can cast the Entity. If you go with individual columns, you have to follow this object approach.
        
        
        //System.out.println(session);
    }
}
