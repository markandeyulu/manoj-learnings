package com.hibernate.demo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
@Entity
public class Person {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)//to generate value automatically
	//@GeneratedValue(generator="gen")
	//@GenericGenerator(name = "gen", strategy = "foreign", parameters=@Parameter(name="property", value="address"))// P.K will be shared here. Primary key join column.
	// one column is missing in person table. that we created for joincolumn.

	@GeneratedValue(strategy=GenerationType.AUTO)
	private int personId;
	private String personName;
	private int personAge;
	
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)// one person has only one address// default fetchtype for one to many is LAZY. one to one is eager. many one one eager. many to many is lazy.. What is the diff between cascade and getchtype ?
	//@JoinColumn(name="address_person_id") //- TYPE1 of ONE2ONE // new column will be created in the child table to refer the P.K of the parent table. Person is parent Address is child
	//@PrimaryKeyJoinColumn  //- TYPE 2 of ONE2ONE

	/* need to create below for id
	 * @GeneratedValue(generator="gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters=@Parameter(name="property", value="address"))// P.K will be shared here. Primary key join column.

	 */
	@JoinTable(name="Address_Person", joinColumns=@JoinColumn(name="Person_ID"), inverseJoinColumns=@JoinColumn(name="Address_ID")) //- TYPE 3 of ONE2ONE // these are new columns will be created. not the one we have in the class. This is to create a new table for join operations with the required columns. Address_Person table will be created. getting data is same as others. Temporary table generated (it will heppen in oracle as well till we commit the transaction. but in Hibernate persisting it), HT_{Parenttable} to keep references.
	
	Address address;// person has an address - hasser relationship. not inherit relationship
	
//	alter table Person add constraint FKlqh2wg4xqngxd4g4bmneavo8j foreign key (address_person_id) references Address

	
	public Person(int personId, String personName, int personAge) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.personAge = personAge;
	}
	
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}
	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Person() {
		
	}

}
