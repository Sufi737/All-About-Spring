package com.allaboutspring.demo.autowiring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Bean2 implements CustomInterface {

	@Override
	public void print() {
		System.out.println("bean 2");
	}

}

/*
* Here we are using @Primary annotation to instruct Sprint that if there is no qualifier used during injection
* then consider this bean by default
*/