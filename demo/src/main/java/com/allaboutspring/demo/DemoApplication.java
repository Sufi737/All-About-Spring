package com.allaboutspring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.allaboutspring.demo.autowiring.UsingPrimary;
import com.allaboutspring.demo.autowiring.UsingQualifier;
import com.allaboutspring.demo.beanscope.PrototypeScope;
import com.allaboutspring.demo.beanscope.SingletonScope;
import com.allaboutspring.demo.di.UsesDependencies;
import com.allaboutspring.demo.profiles.BeanForLocal;

//footnote 1
@SpringBootApplication(scanBasePackages = "com.allaboutspring")
public class DemoApplication {

	public static void main(String[] args) {
		//footnote 2
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		UsesDependencies obj = context.getBean(UsesDependencies.class);
		obj.useProvider();
		obj.useThirdParty();
		
		//demonstrating bean scopes (see footnote 3)
		SingletonScope singleton1 = context.getBean(SingletonScope.class);
		SingletonScope singleton2 = context.getBean(SingletonScope.class);
		System.out.println("Singleton scope both objects are same: "+(singleton1 == singleton2));
		
		PrototypeScope prototype1 = context.getBean(PrototypeScope.class);
		PrototypeScope prototype2 = context.getBean(PrototypeScope.class);
		System.out.println("Prototype scope both objects are same: "+(prototype1 == prototype2));
		
		//to demo autowiring with qualifier and primary
		UsingQualifier usingQualifier = context.getBean(UsingQualifier.class);
		usingQualifier.useBean();
		UsingPrimary usingPrimary = context.getBean(UsingPrimary.class);
		usingPrimary.useBean();
		
		//to demo profiles
		BeanForLocal localBean = context.getBean(BeanForLocal.class);
		localBean.print();
		
		//shutting down application context (footnote 4)
//		((ConfigurableApplicationContext) context).close();
		((AbstractApplicationContext) context).registerShutdownHook(); //more preferred way
		System.exit(0);
	}
}

/* Footnotes below
 * 
 * 1
 * @SpringBootApplication enables the following features for an application:
 * 1. @EnableAutoConfiguration
 * 2. @ComponentScan
 * 3. @Configuration
 * 
 * What is auto configuration mechanism?
 * Spring auto-configures dependencies based on the JARs added on the application. We don't need to manually specify.
 * 
 * @ComponentScan
 * Component scan means scanning for beans in specific packages. We can specify packages in this annotation using basePackages property in @Component annotation
 * Spring will include beans annotated with @Component for the packages
 * If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.
 * 
 * In the docs it is recommended that to have one single configuration class, and it's better if it is the same with the main method
 * As @Configuration is already included here, we can keep this same class as our primary configuration class
 * 
 * Note that this class is called the start class, and can be configured to any class using Maven <start-class> property in pom.xml
 * */


/* 2
 * Here we are using ApplicationContext to get UsesDependencies bean
 * UsesDependencies has dependency on other classes and application context gets those dependencies resolved for us
 * Most of the times we won't be using application context as we use setter methods for that, but as there is no other way for main() method we are using it.
 * The ApplicationContext interface is a wrapper for org.springframework.beans.factory.BeanFactory which is just an object pool
 * The BeanFactory creates objects by reading configuration metadata. 
 * The configuration metadata can be in the form of XML or Java code
 * There are various implementations provided of ApplicationContext such as 
 * AnnotationConfigApplicationContext
 * ClassPathXmlApplicationContext
 * FileSystemXmlApplicationContext
 */


/* 3
 * Here prototype scope bean will return new instance everytime new bean is fetched and hence both the objects are not equal
 * Singleton scope beans will be returned with the same instance everytime bean is fetched or dependency is specified
 */


/* 4
 * There are two methods as written above to close application context. 
 * registerShutdownHook() is more preferred but it will wait till JVM is destroyed which is why using System.exit(0)
 */