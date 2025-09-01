package com.example.demo.Aop;

import java.lang.System.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class StudentAopExam {
	
	org.slf4j.Logger logger =LoggerFactory.getLogger(StudentAopExam.class);
	//@Pointcut(value = "execution(*com.example.demo.(..)")
	 @Pointcut("execution(* com.example.demo..*(..))")
	public void pointcut() {
		
	}
	
	
	@Around("pointcut()")
	public Object applicatonLogging(ProceedingJoinPoint pj)throws Throwable{
		
		ObjectMapper mapper=new ObjectMapper();
		String methodname=pj.getSignature().getName();
		String classname=pj.getTarget().getClass().getSimpleName();
		Object[]array=pj.getArgs();
		logger.info(methodname+" "+classname+""+mapper.writeValueAsString(array));
		
		Object ob=pj.proceed();
		logger.info(methodname+" "+classname+""+mapper.writeValueAsString(ob));
		
		
		return ob;
		
		
	}

}
