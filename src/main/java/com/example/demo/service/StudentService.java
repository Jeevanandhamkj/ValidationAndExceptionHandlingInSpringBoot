package com.example.demo.service;

import java.awt.print.Pageable;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptionstu.UserAlreadyExistException;
import com.example.demo.Exceptionstu.UserNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;

@Service
public class StudentService {
	
	private static final Logger log = LoggerFactory.getLogger(StudentService.class);
	
	StudentRepo spRepo;
	@Autowired
	public StudentService(StudentRepo seRepo) {
		this.spRepo=seRepo;
	}
	
	public Student adding(Student s) throws UserAlreadyExistException {
		//log.info("add user with email: {}",s.getEmail());
		Student saveStudent=spRepo.findByEmail(s.getEmail());
		if(saveStudent==null) {
			//log.debug("save the user with email:{}",s.getEmail());
		return spRepo.save(s);
		}
		else {
			//log.warn("User already Exist with email:{}",s.getEmail());
			throw new UserAlreadyExistException("User with email already exists");
		}
	}
	
	public List<Student>addall(List<Student>s){
		//log.info("adding all studnet:{}",s);
		return spRepo.saveAll(s);
	}
	public Student getbyid(Long id) throws UserNotFoundException {
		//log.info("getting user by id: {}",id);
		
       return	spRepo.findById(id).orElseThrow(()->

		 new UserNotFoundException("user not available with this id"));
			
		}
	
	public List<Student>getingall(){
		//log.info("getting all student:{}");
		return spRepo.findAll();
	}
	public  Student  del(long id) throws UserNotFoundException {
		//log.info("student findby id:{}"+id);
		Student student=spRepo.findById(id)
				
				.orElseThrow(()->new UserNotFoundException("user with id not avl"));
		//log.error("user deleted:{}",student);
		spRepo.delete(student);
		//log.debug("user deleted done:{}",student);
		return student;
		
	}
	
	public Student updating(Long id,Student ss) throws UserNotFoundException {
		//log.info("Student with id :{}",id);
		Student student=spRepo.findById(id).orElseThrow(()->new UserNotFoundException("user id not found"));
		//log.debug("setting values in stuent:{}",ss);
		student.setName(ss.getName());
		student.setAge(ss.getAge());
		student.setEmail(ss.getEmail());
		student.setMobile(ss.getMobile());
		//log.debug("Successfully updated:{}",student);
		return spRepo.save(student);
	}
	
	public Student patching(Long id,Map<String, Object>k)throws UserNotFoundException {
		//log.info("getting student by id:{}",id);
		Student ss = spRepo.findById(id).orElseThrow(
				()->new UserNotFoundException("not found"));
		k.forEach((key,value)->{
			switch(key) {
			
			case "name":
				//log.debug("setting name in student:{}",value);
				ss.setName((String)value);
				break;
			case "age":
				//log.debug("setting age in student:{}",value);
				ss.setAge((int)value);
				break;
			case "email":
				//log.debug("setting email in student:{}",value);
				ss.setEmail((String)value);
				break;
			case "mobile":
				//log.debug("setting mobile in student:{}",value);
				ss.setMobile((String)value);
				break;
			}
		});
		
		
		//log.debug("student updated success:{}",ss);
		return spRepo.save(ss);
	}
	
	public Page<Student>pagging(int page,int size){
		org.springframework.data.domain.Pageable pageable=PageRequest.of(page, size);
		return spRepo.findAll(pageable);
	}
	public List<Student>sorthingAlluser(String field,String dir){
		Sort sort=dir.equalsIgnoreCase("dsc")?Sort.by(field).descending():Sort.by(field).ascending();
		return spRepo.findAll(sort);
	}

}
