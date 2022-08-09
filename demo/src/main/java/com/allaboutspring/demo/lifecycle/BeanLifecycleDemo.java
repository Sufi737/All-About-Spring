package com.allaboutspring.demo.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanLifecycleDemo 
implements ApplicationContextAware, BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory is called");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("setBeanName is called");
		System.out.println("Bean name: "+name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("setApplicationContext is called");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet is called");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy is called");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Post construct is called");
	}
	
	@PreDestroy
	public void customDestroy() {
		System.out.println("Pre destroy is called");
	}

}

/*
* Bean creation lifecycle is as follows:
* 1. Bean is instantiated (using non-parameterized default constructor)
* 2. Properties are populated
* 3. setBeanName() of BeanNameAware is called. BeanNameAware is an interface which we need to implement and override setBeanName()
* 4. setBeanFactory() of BeanFactoryAware is called. BeanFactoryAware is again an interface we can implement.
* 5. setApplicationContext() of ApplicationContextAware is called. This is again an interface.
* 6. postProcessBeforeInitialization() is called (BeanPostProcessors interface)
* 7. Custom init method is called (init-method in xml or @PostContruct annotation)
* 8. afterPropertiesSet() is called of InitializingBean interface 
* 9. postProcessAfterInitialization() is called (BeanPostProcessors interface)
* 10. And now bean is ready to use
* 
* Bean destroy lifecycle is as follows:
* 1. Container shutdown
* 2. Call custom destroy method (destroy-method in xml or @PreDestroy annotation)
* 3. DisposableBean's destroy() method is called
* 
* Note: Even when we are not using this bean. You will see this bean to be initialized because 
* the IoC container pre-initializes all singleton beans at startup
* We can set them up to load lazily or make thier scope prototype
* 
* We can use @Lazy annotation above configuration class so that all beans defined using @Bean will load lazily
*/