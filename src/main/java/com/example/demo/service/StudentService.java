package com.example.demo.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptionstu.UserAlreadyExistException;
import com.example.demo.Exceptionstu.UserNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;

@Service
public class StudentService {
	
	StudentRepo spRepo;
	@Autowired
	public StudentService(StudentRepo seRepo) {
		this.spRepo=seRepo;
	}
	
	public Student adding(Student s) throws UserAlreadyExistException {
		Student saveStudent=spRepo.findByEmail(s.getEmail());
		if(saveStudent==null) {
		return spRepo.save(s);
		}
		else {
			throw new UserAlreadyExistException("User with email already exists");
		}
	}
	
	public List<Student>addall(List<Student>s){
		return spRepo.saveAll(s);
	}
	public Student getbyid(Long id) throws UserNotFoundException {
return	spRepo.findById(id).orElseThrow(()->
		 new UserNotFoundException("user not available with this id"));
			
		}
	
	public List<Student>getingall(){
		return spRepo.findAll();
	}
	public  Student  del(long id) throws UserNotFoundException {
		Student student=spRepo.findById(id)
				.orElseThrow(()->new UserNotFoundException("user with id not avl"));
		spRepo.delete(student);
		return student;
		
	}
	
	public Student updating(Long id,Student ss) throws UserNotFoundException {
		Student student=spRepo.findById(id).orElseThrow(()->new UserNotFoundException("user id not found"));
		student.setName(ss.getName());
		student.setAge(ss.getAge());
		student.setEmail(ss.getEmail());
		student.setMobile(ss.getMobile());
		
		return spRepo.save(student);
	}
	
	public Student patching(Long id,Map<String, Object>k)throws UserNotFoundException {
		Student ss = spRepo.findById(id).orElseThrow(
				()->new UserNotFoundException("not found"));
		k.forEach((key,value)->{
			switch(key) {
			case "name":
				ss.setName((String)value);
				break;
			case "age":
				ss.setAge((int)value);
				break;
			case "email":
				ss.setEmail((String)value);
				break;
			case "mobile":
				ss.setMobile((String)value);
				break;
			}
		});
		
		
		
		return spRepo.save(ss);
	}

}
