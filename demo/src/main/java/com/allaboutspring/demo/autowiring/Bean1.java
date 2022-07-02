package com.allaboutspring.demo.autowiring;

import org.springframework.stereotype.Component;

@Component
public class Bean1 implements CustomInterface {

	@Override
	public void print() {
		System.out.println("bean 1");
	}

}
