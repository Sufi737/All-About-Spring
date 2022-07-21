package com.allaboutspring.demo.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity implements UserInterface {
	
	@Id
	@Column(name="entity_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private int age;
	
	UserEntity() {
		//required
	}
	
	public UserEntity(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}

/*
 * For GeneratedValue annotation refer https://stackoverflow.com/a/47676491/10195722
 * Here we are using IDENTITY so it will rely on the underlying database to generate a new value. 
 * In this case I had a MySQL table which was auto increment
 * If it is set to AUTO and if it is using Hibernate then it will try to find a hibernate_sequence table
 * We can also specify our own custom sequence table name as explained in the link above
 * 
 * @Id annotation is used to specify that the given field is a primary key and uniquely identifies a row
 * 
 * @Column is only required if the database column name is different than that of the field name
 * */
