package com.allaboutspring.demo.di;

import org.springframework.stereotype.Component;

@Component
public class ProviderImplementation implements ProviderInterface {

	@Override
	public String returnString() {
		return "heyy!!";
	}

}

/*
 * We want to use this implementatin as a dependency in MainDemo class. 
 * For this we need to declare this as a bean first so that it is scanned. This is done using @Component annotation
 * 
 * Why not @Bean?
 * @Bean is preferrable to use only in certain scenarios where auto-configuration is not possible.
 * For example, if 3rd party classes cannot be annotated with @Component we can use @Bean at the method level to register it as a bean in spring.
 * */