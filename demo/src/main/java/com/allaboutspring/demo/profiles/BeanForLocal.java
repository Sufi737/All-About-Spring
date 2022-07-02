package com.allaboutspring.demo.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class BeanForLocal {
	public void print() {
		System.out.println("Only works for local profile");
	}
}

/*
As the above bean is annotated with @Profile, the bean will only be loaded if local profile is active
We can also use ! to specify that a bean to not be loaded when that profile is not active

If the specified profile is not active and we attempt to use the bean it results in exception
No qualifying bean of type 'com.allaboutspring.demo.profiles.BeanForLocal' available
*/