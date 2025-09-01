package com.example.demo.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Aop.StudentAopExam;
import com.example.demo.Exceptionstu.UserAlreadyExistException;
import com.example.demo.Exceptionstu.UserNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/student")
public class Studentcontroller {

   

    
	private static final Logger lg=LoggerFactory.getLogger(Studentcontroller.class);
	
	private StudentService service;
	@Autowired
	public Studentcontroller(StudentService service) {
		this.service=service;
		
		
	}
	@PostMapping("/add")
	public ResponseEntity<Student>create(@RequestBody @Valid Student s)throws UserAlreadyExistException{
	//	lg.info("Get/student/add-Adding new UserwithEmail:{}",s.getEmail());
		return new ResponseEntity<Student>(service.adding(s), HttpStatus.CREATED);
	}
	@PostMapping("/addall")
	public ResponseEntity<List<Student>>createAll(@RequestBody @Valid  List<Student>ss){
		//lg.info("post/student/addall -adding student using list:{}",ss);
	return new ResponseEntity<List<Student>>(service.addall(ss),HttpStatus.CREATED);
	}
	@GetMapping("/show/{id}")
	public ResponseEntity<Student> getbyid(@PathVariable("id")Long id) throws UserNotFoundException{
		//lg.info("Get/student/show/id -get student using id:{}",id);
		return ResponseEntity.ok(service.getbyid(id));
	}
	
	@GetMapping("/showall")
	public ResponseEntity<List<Student>>all(){
		//lg.info("Get/student/showall -getting all student list");
		return ResponseEntity.ok(service.getingall());
	}
	@PutMapping("/up/{id}")
	public ResponseEntity<Student>up(@PathVariable("id")long id,@RequestBody Student ss)throws UserNotFoundException{
		//lg.info("Put/student/up/id -updating student using by id:{}",id);
		Student student= service.updating(id, ss);
		//lg.debug("Student updated success:{}",ss);
		return ResponseEntity.ok(student);
	}
	@PatchMapping("/pat/{id}")
	public ResponseEntity<Student>pat(@PathVariable("id")long id,@RequestBody Map<String,Object>m)throws UserNotFoundException{
		//lg.info("Patch/student/pat/id -updating student using by id:{}",id);
		Student ss=service.patching(id, m);
		//lg.debug("Student updated success:{}",m);
     return ResponseEntity.ok(ss);
	}
	@DeleteMapping("/del/{id}")
	public ResponseEntity<Student> del(@PathVariable("id")long id)throws UserNotFoundException{
		//lg.info("Delete/del/id -delete student by id:{}",id);
		Student del= service.del(id);
		//lg.warn("Student delete success:{}",del);
		return ResponseEntity.ok(del);
	}
	@GetMapping("/p")
	public ResponseEntity<Page<Student>>pagg(@RequestParam int page,@RequestParam int size){
		return ResponseEntity.ok(service.pagging(page, size));
	}
	@GetMapping("s/{field}/{dir}")
	public ResponseEntity<List<Student>>sorthing(@PathVariable String field, @PathVariable String dir){
	return ResponseEntity.ok(service.sorthingAlluser(field, dir));
	}

}
