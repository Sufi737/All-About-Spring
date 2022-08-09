package com.allaboutspring.demo.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("image-extract-info-user", 
				"KA#$DlAQw^7d2eFOMetdd", 
				new ArrayList<>()
		);
	}

}

/*
This is our own custom user details service

This is where we set user details and password and authenticate a user
In case we want to authenticate by reading from the database, this method will call the findUserById() of JPA repository

So the entire flow is as follows:
User requests -> DelegatingFilterProxy -> AuthenticationManager authenticate() -> Checks which type of authentication using each 
AuthenticationProvider's support() -> AuthenticationProvider's authenticate() -> UserDetailsService loadUserByUsername() ->
If JPA is used then fetch user using repository -> return User instance -> User info with principal is returned to AuthenticationManager
-> Principal and user info is stored in SecurityContext which is used for subsequent requests
*/