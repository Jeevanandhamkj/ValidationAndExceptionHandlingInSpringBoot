package com.example.demo.Exceptionstu;

public class UserAlreadyExistException extends Exception {
	
	public UserAlreadyExistException(String message) {
		super(message);
	}

}
