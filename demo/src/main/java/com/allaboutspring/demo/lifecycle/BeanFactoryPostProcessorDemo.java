package com.allaboutspring.demo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryPostProcessorDemo implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryPostProcessor called");
	}

}

/*
* BeanFactoryPostProcessor allows us to configure bean definitions before any bean is instantiated
* This is called when IoC container loads all the bean configuration metadata
* Here we can configure bean definitions and its configuration metadata as well before any bean is initialized
* Note that this is different than BeanPostProcessor as it is called in the later stage of the lifecycle. 
* BeanPostProcessor is called after the beans are initialized
*/