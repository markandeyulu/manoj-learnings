package com.entities;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class Test {

	public static void main(String a[])
	{
		
AnnotationConfiguration ac = new AnnotationConfiguration();
		
		ac.configure();
		
		SessionFactory sf = ac.buildSessionFactory();
		
		Session ses = sf.openSession();
		
		Transaction tx = ses.beginTransaction();
		
College c = (College) ses.get(College.class, 1);
		
		tx.begin();
		
		ses.delete(c);
		
		/*System.out.println(c.getCollegeName());
		
		List<Student> stud = c.getStudent();
		
		for(Student s : stud)
		{
			System.out.println(s.getStudId());
			System.out.println(s.getStudName());
		}*/
		
		/*College c1 = new College();
		
		c1.setCollegeName("Loyola");
		
		College c2 = new College();
		
		c2.setCollegeName("St.Joseph");
		ses.save(c1);
		ses.save(c2);
		
		Student s1 = new Student();
		Student s2 = new Student();
		Student s3 = new Student();
		Student s4 = new Student();
		
		s1.setStudName("Rakesh");
		s2.setStudName("Paul");
		s3.setStudName("Kishore");
		s4.setStudName("Fernando");
		
		s1.setCollege(c1);
		s2.setCollege(c2);
		s3.setCollege(c2);
		s4.setCollege(c1);
		
		
		ses.save(s1);
		ses.save(s2);
		ses.save(s3);
		ses.save(s4);*/
		
		tx.commit();
		
	}
	
}
