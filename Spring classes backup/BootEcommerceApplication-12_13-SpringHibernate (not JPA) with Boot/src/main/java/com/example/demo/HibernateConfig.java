package com.example.demo;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;//see carefully. Its not hibernate jars. its spring integrated with Hibernate. This is not JPA. This is Spring integrated with hibernate.
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;//see carefully. Its not hibernate jars. its spring integrated with Hibernate. This is not JPA. This is Spring integrated with hibernate.
//Hibernate is an ORM which is connecting to DB(H2) here
@Configuration
public class HibernateConfig {

	// 	//step 1 : Create Datasource (Boot will do automatically)
	
	//here we would have created datasource by getting value from application.properties
	
		// @Value("${spring.datasource.driver-class-name}")
		// private String DRIVER;
		//If we comment this it will take default one - H2 DB
		/*
		 * @Bean public DataSource dataSource() { DriverManagerDataSource dataSource =
		 * new DriverManagerDataSource(); dataSource.setDriverClassName(DRIVER);
		 * dataSource.setUrl(URL); dataSource.setUsername(USERNAME);
		 * dataSource.setPassword(PASSWORD); return dataSource; }
		 */

		@Value("${spring.jpa.show-sql}")
		private String SHOW_SQL;

		@Value("${spring.jpa.hibernate.ddl-auto}")
		private String HBM2DDL_AUTO;

		@Value("${sessionFactory.packagestoscan}")
		private String PACKAGES_TO_SCAN;
		// 2. Create SessionFactory(datasource)

		//We dont need to create datasource here. Boot will create based on the property file.
		@Autowired
		DataSource dataSource;

		@Bean
		public LocalSessionFactoryBean sessionFactory() throws SQLException {
				//This LocalSessionFactoryBean and HibernateTransactionManager are the one important in this Spring n Hibernate integration. We will get current session and use for transaction.
			// This is not the Spring data JPA. 
			
			/*
			 * Understand this clearly : 
			 * 
			 * Spring Data JPA jar has 3 things(so dont confuse we are using data JPA here.LocalSessionFactoryBean and HibernateTransactionManager are not part of Spring data JPA. they are part of Spring integrated with hibernate.
			 * 	1. Spring JDBC
			 *  2. Spring integrated Hibernate
			 *  3. Spring Data JPA
			 *  
			 *  What we are seeing here with LocalSessionFactoryBean and HibernateTransactionManager are as part of Spring with Hibernate integrations.
			 *  When we go with Spring integrated Hibernate we have to manually need to give LocalSessionFactory and HibernateTransactionManager.
			 *  But in case of Spring DataJPA's there are 2 advantages.
			 *  	1. No need of LocalSessionFactory and HibernateTransactionManager. 
			 *  	2. No need of redundant queries like getone getall updateone updateall, that will be takencare by @EnableTransactionmanager(JPA) and @Repository(JPA) (JPA repository, CRUD repsitory)
			 
			 *  Spring operations is mitigated with Spring boot. Similarly,
			 *  		Spring JDBC --> Spring integrated Hibernate(what we are seeing here LocalSessionFactoryBean and HibernateTransactionManager)  --> Spring JPA.
			 *  Spring JPA will interact internally with Hibernate and Hibernate internally interact with JDBC.
			 *  
			 *  Even in Spring JPA's if you want your own Hibernate queries you can write HQL,Namedqueries or Creteria queries.
			 */
			 
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			Properties hibernateProperties = new Properties();
			// hibernateProperties.put("hibernate.dialect", DIALECT);
			sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
			hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
			hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
			sessionFactory.setHibernateProperties(hibernateProperties);//LocalSessionFactoryBean is not from Hibernate. Its from Spring integrated with Hibaernate

			return sessionFactory;
		}

		//step 3 : Create TransactionManager
 		// we will outsource the transaction also. HibernateTransatctionManager - Spring itegrated with Hibernate class . We are not using direct Hibernate pkgs.
		
		//If you comment this you will get message": "no transaction is in progress",
		// This is needed only for DML and not for DQL.
		// If you want to disable this, we can write our own begin and commit transactions
		@Bean
		public HibernateTransactionManager transactionManager() throws SQLException {// we dont need to do begin transaction and end transaction like we did in normal Hibernate
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory().getObject());
			return transactionManager;
		}
	

}
