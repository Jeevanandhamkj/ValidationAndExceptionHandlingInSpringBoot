package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NotBlank(message = "Name cannot Empity")
	@Size(min=3,max = 30,message = "name minium 3 and maximum 30 char")
	String name;
	
	@Min(value = 0,message = "age minimum 0") 
	@Max(value = 130,message = "maximum age 130")  
	int age;
	@Email(message = "Email should be valid")
	String email;
	@Pattern(regexp = "^[0-9]{10}$",message = "enter valid mob number")
	
	String mobile;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Student(long id,
			@NotBlank(message = "Name cannot Empity") @Size(min = 3, max = 30, message = "name minium 3 and maximum 30 char") String name,
			@NotEmpty @Size(min = 0, message = "age minimum 0") @Size(max = 130, message = "maximum age 130") int age,
			@Email(message = "Email should be valid") String email,
			@Pattern(regexp = "^[0-9]{10}$", message = "enter valid mob number") String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.mobile = mobile;
	}
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", mobile=" + mobile
				+ "]";
	}
	

}
