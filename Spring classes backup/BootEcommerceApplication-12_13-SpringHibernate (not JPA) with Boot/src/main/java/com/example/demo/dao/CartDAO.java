package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Cart;

@Repository
@Transactional(rollbackFor = {SQLException.class})
public class CartDAO {

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();//session factory created in the Hibernateconfig class
		return session;
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	// replica of what we have in PlayerService
		public boolean save(Cart cart) throws Exception {
			Session session = getSession();
			
			/*
			 * for (Item i : cart.getItems()) { i.setCart(cart); }
			 */
			
			//session.persist(cart);
			//session.merge(cart);
			//session.save(cart.getItems());
			//session.flush();
			//session.clear();
			//throw new SQLException("DEMO TRX management...!");
			/*
			 * Transaction tx = null; Integer i = 0; try { tx = session.beginTransaction();
			 * i = (Integer)session.save(player); //i = (Integer)session.save(player); //i =
			 * (Integer)session.save(player); throw new
			 * Exception("DEMO TRX management...!");
			 * 
			 * } catch (Exception e) { if (tx!=null) tx.rollback(); e.printStackTrace();
			 * //throw e; } tx.commit();
			 */
			//if(i>=1) return true; else return false;
			
			// Spring data JPA will provide readymade methods. JPA repository, CRUD repsitory, MANGO repository. The logics will be writted in Spring JPA's. Relevent to the entity it will automatically run the query and return data. we need @Repository for this. Even in Spring JPA's if you want your own Hibernate queries you can write HQL,Namedqueries or Creteria queries.
			// we are not using any transaction here like hibernate (we are using @Transactional-Spring integrated with hibernate)
			//session.close();// using same session for all methods
			
			
			//just check in which cases we will get HQL in sysout and normal SQL in sysouts - ??

			/*
			 * i = (Integer)session.save(player); i = (Integer)session.save(player); throw
			 * new Exception("DEMO TRX management...!");
			 */
			
			  return true;
			 
		}
		
		public List<Cart> findAll() {
			Session session = getSession();
			
			Query query = session.createQuery("from Cart", Cart.class);
			
			List<Cart> carts = query.getResultList();
			
			return carts;
		}
}
