package com.allaboutspring.demo.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsingPrimary {

	@Autowired
	private CustomInterface myBean;
	
	public void useBean() {
		myBean.print();
	}
}

/*
* Here we are not specifying using @Qualifier that which implementationw we want to inject
* There is another way we can instruct Spring to consider a bean by default to inject when there are multiple implementations
* And that method is to add @Primary annotation to the bean we want to be injected by default
*/