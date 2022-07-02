package com.allaboutspring.demo.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UsingQualifier {

	@Autowired
	@Qualifier("bean1")
	private CustomInterface myBean;
	
	public void useBean() {
		myBean.print();
	}
}

/*
* Here we are using @Autowired to inject CustomInterface, but if you see there are 2 implementations for the same
* So either we need to annotate one bean with @Primary to instruct IoC container to inject that bean
* OR
* With @Autowired use @Qualifier to instruct Spring which specific bean we want to inject
* Here we are using Qualifier
* 
* We can also assign a qualifier name on a bean by using the same @Qualifier annotation at the bean and giving a name to it
* Then the same qualifier name can be used here during injection
*/