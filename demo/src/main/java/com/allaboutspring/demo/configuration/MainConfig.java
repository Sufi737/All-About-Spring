package com.allaboutspring.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.allaboutspring.demo.di.ThirdPartyImplementation;
import com.allaboutspring.demo.di.ThirdPartyInterface;
import com.allaboutspring.demo.lifecycle.BeanFactoryPostProcessorDemo;

@Configuration
@PropertySource("classpath:properties/custom.properties")
public class MainConfig {
	
	@Autowired
	Environment env; //for reading properties file values

	@Bean
	public ThirdPartyInterface returnThirdParty() {
		//below is commented out because using BeanFactoryPostProcessor and env autowiring happens late
//		System.out.println("Demonstrating use of property source: "+env.getProperty("key"));
		return new ThirdPartyImplementation();
	}
	
	@Bean
	public BeanFactoryPostProcessor customBeanFactoryPostProcessor() {
		return new BeanFactoryPostProcessorDemo();
	}
}

/* This is our configuration class annotated with @Configuration
 * Configuration classes are mainly used to configure beans
 * We cannot use final classes as configuration because they are subclassed by the Sprint container using CGLIB
 * 
 * We have special methods here annotated with @Bean
 * This is used to register our class ThirdPartyImplementation as a bean as it is not annotated with @Component 
 * (which is the case for third party classes)
 * In this way we can register classes not developed by us as beans!
 * The @Bean has properties such as destroyMethod, initMethod, name
 * The default bean id or name is the method's name and can be overridden using @Bean(name="") or @Bean ("beanname")
 * 
 * In this method just added one line to demonstrate how we can read custom properties file
 * We have an annotation @PropertySource where we provide the path of our properties file
 * NOTE: This annotation only works for @Configuration class and it does not work for yaml files
 */