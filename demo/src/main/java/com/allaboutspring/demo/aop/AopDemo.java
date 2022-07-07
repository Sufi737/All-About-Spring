package com.allaboutspring.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopDemo {
	
	@Pointcut("execution(* printMessage(..))")
	public void anyPrintMessage() {
		System.out.println("@Pointcut: Execution before printMessage()");
	}
	
	@Before("execution(public void printMessage())")
	public void executeBeforePrintMessage() {
		System.out.println("@Before: Execution before printMessage()");
	}
	
	@After("execution(* printMessage())")
	public void executeAfterPrintMessage() {
		System.out.println("@After: Execution after printMessage()");
	}
	
	@AfterReturning(
			pointcut="execution(public * returnsSomething())",
			returning="returnValue"
	)
	public void executeAfterReturn(Object returnValue) {
		System.out.println("@AfterReturning: Execution after returnsSomething() returns something. Returned value is: "+returnValue);
	}
	
	@AfterThrowing("execution(public void throwsException())")
	public void executeAfterThrowsException() {
		System.out.println("@AfterThrowing: Execution after printMessage() throws an exception");
	}
	
	@Around("execution(* originalMethod(..))")
	public void aroundOriginalMethod(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		for(Object argument: args) {
			System.out.println(argument);
		}
		int value = (Integer) args[0];
		if (value > 50) {
			pjp.proceed(args);
		} else {
			System.out.println("Original method invokation skipped");
		}
	}
}

/*
This class applies AOP to method with name printMessage()
We are using here @Aspect annotation to register this class as an aspect

An Aspect represents a cross-cutting functionality, which we want to span across multiple areas of an application
For example, transaction managemenet is done across multiple methods and can be treated as an aspect

Then there are annotations which help us define a "Pointcut". A pointcut is a point during the execution, which in our case,
are before, after and so one. We have a @Pointcut annotation, which takes in a pointcut expression. 
Here, the method name anyPrintMessage is our pointcut

In the pointcut expression we are using "executing" which is a pointcut designator. There are other designators supported in spring
The pointcut expressions can also be combined using logical operators
We can also use the pointcut name for example anyPrintMessage in other pointcut expressions

Then we are annotations such as @Before which are called as advices
@Before - called before method invocation
@After - called after method execution is complete
@AfterReturning - called only when the method returns something
@AfterThrowing - called only when the method throws an exeption
@Around - called both for before method is invoked and also after method is executed. This allows complete modification of behaviour
	We can also skip the original method call by not calling proceed()


*/