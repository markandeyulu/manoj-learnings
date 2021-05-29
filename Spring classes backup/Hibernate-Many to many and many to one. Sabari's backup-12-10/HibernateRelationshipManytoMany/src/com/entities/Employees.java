package com.entities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Employee_Mahindra")
public class Employees {

	@Id
	@GeneratedValue
	private int empId;
	private String empName;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Employees_Meetings",joinColumns=@JoinColumn(name="empId"),inverseJoinColumns=@JoinColumn(name="meetingId"))
	List<Meetings> meetings = new LinkedList<Meetings>();
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public List<Meetings> getMeetings() {
		return meetings;
	}
	public void setMeetings(List<Meetings> meetings) {
		this.meetings = meetings;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
	
}
