package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptionstu.UserAlreadyExistException;
import com.example.demo.Exceptionstu.UserNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/student")
public class Studentcontroller {
	
	private StudentService service;
	@Autowired
	public Studentcontroller(StudentService service) {
		this.service=service;
	}
	@PostMapping("/add")
	public ResponseEntity<Student>create(@RequestBody @Valid Student s)throws UserAlreadyExistException{
		return new ResponseEntity<Student>(service.adding(s), HttpStatus.CREATED);
	}
	@PostMapping("/addall")
	public ResponseEntity<List<Student>>createAll(@RequestBody @Valid  List<Student>ss){
	return new ResponseEntity<List<Student>>(service.addall(ss),HttpStatus.CREATED);
	}
	@GetMapping("/show/{id}")
	public ResponseEntity<Student> getbyid(@PathVariable("id")Long id) throws UserNotFoundException{
		return ResponseEntity.ok(service.getbyid(id));
	}
	
	@GetMapping("/showall")
	public ResponseEntity<List<Student>>all(){
		return ResponseEntity.ok(service.getingall());
	}
	@PutMapping("/up/{id}")
	public ResponseEntity<Student>up(@PathVariable("id")long id,Student ss)throws UserNotFoundException{
		Student student= service.updating(id, ss);
		return ResponseEntity.ok(student);
	}
	@PatchMapping("/pat/{id}")
	public ResponseEntity<Student>pat(@PathVariable("id")long id,@RequestBody Map<String,Object>m)throws UserNotFoundException{
     Student ss=service.patching(id, m);
     return ResponseEntity.ok(ss);
	}
	@DeleteMapping("/del/{id}")
	public ResponseEntity<Student> del(@PathVariable("id")long id)throws UserNotFoundException{
		Student del= service.del(id);
		return ResponseEntity.ok(del);
	}

}
