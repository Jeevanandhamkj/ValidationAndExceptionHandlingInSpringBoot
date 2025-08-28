package com.example.demo.Exceptionstu;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleExcep {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String>errorMap(MethodArgumentNotValidException e){
		Map<String, String>eMap=new HashMap<>();
		e.getBindingResult().getFieldErrors()
		.forEach(ee->{
			eMap.put(ee.getField(), ee.getDefaultMessage());
		});
		
		
		return eMap;
	}
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String>bushandle(UserNotFoundException u){
		Map<String,String>hanMap=new HashMap<>();
		hanMap.put("Error message", u.getMessage());
		return hanMap;
	}
	@ExceptionHandler(UserAlreadyExistException.class)
	public Map<String, String>eee(UserAlreadyExistException us){
		Map<String, String>uMap=new HashMap<>();
		uMap.put("ErrorMessage", us.getMessage());
		return uMap;
	}

}
