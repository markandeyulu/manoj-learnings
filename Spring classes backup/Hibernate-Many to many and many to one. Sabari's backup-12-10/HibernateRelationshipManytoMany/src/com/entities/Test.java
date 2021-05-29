package com.entities;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class Test {

	
	public static void main(String[] args) {
		 
AnnotationConfiguration ac = new AnnotationConfiguration();
		
		ac.configure();
		
		SessionFactory sf = ac.buildSessionFactory();
		
		Session ses = sf.openSession();
		
		Transaction tx = ses.beginTransaction();
		
		//Employees e = (Employees) ses.get(Employees.class,7);
		Meetings m = (Meetings) ses.get(Meetings.class, 6);
		/*List<Meetings> m = e.getMeetings();
		System.out.println(e.getEmpName());
		for(Meetings meet : m )
		{
		System.out.println(meet.getMeetingName());
		}*/
		List<Employees> ee = m.getEmployees();
		System.out.println(m.getMeetingId());
		
		System.out.println(m.getMeetingName());
		
		for(Employees e : ee )
		{
		System.out.println(e.getEmpName());
		}
		
		
		/*tx.begin();
		
		Employees e1 = new Employees();
		e1.setEmpName("Rajesh");
		Employees e2 = new Employees();
		e2.setEmpName("Kamal");
		Employees e3 = new Employees();
		e3.setEmpName("Naveen");
		Employees e4 = new Employees();
		e4.setEmpName("Joseph");
		
		Meetings m1 = new Meetings();
		m1.setMeetingName("Weekly Meeting");
		
		Meetings m2 = new Meetings();
		m2.setMeetingName("Monthly Meeting");
		
		Meetings m3 = new Meetings();
		m3.setMeetingName("Daily Meeting");
		
		e1.getMeetings().add(m1);
		e1.getMeetings().add(m3);
		e2.getMeetings().add(m1);
		e3.getMeetings().add(m2);
		e4.getMeetings().add(m3);
		e4.getMeetings().add(m2);
		
		ses.save(e1);
		ses.save(e2);
		ses.save(e3);
		ses.save(e4);*/
		
		//tx.commit();
		

		
	}

}

