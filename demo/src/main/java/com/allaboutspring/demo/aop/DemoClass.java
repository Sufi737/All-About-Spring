package com.allaboutspring.demo.aop;

import org.springframework.stereotype.Component;

@Component
public class DemoClass {
	
	public void printMessage() {
		System.out.println("Class method called");
	}
	
	public String returnsSomething() {
		System.out.println("returnSomething is called");
		return "hello";
	}
	
	public String throwsException() throws Exception {
		System.out.println("throwsException is called");
		throw new Exception("Custom exception");
	}
	
	public Integer originalMethod(Integer value) {
		System.out.println("originalMethod is called");
		return value;
	}
}

/*
 * We are using this class to demo AOP. We will use all types of aspects on printMessage()
*/
