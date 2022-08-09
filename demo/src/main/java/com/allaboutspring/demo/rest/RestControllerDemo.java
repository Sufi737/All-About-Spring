package com.allaboutspring.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allaboutspring.demo.jpa.UserEntity;
import com.allaboutspring.demo.jpa.UserRepository;

@RestController
@RequestMapping("/rest/user")
public class RestControllerDemo {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping()
	public List<UserEntity> getUsers() {
		List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
		return users;
	}

	@PostMapping()
	public ResponseEntity<String> createUser(@RequestBody UserEntity user) {
		try {
			userRepository.save(user);
			return new ResponseEntity<>("User created successfully!", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(
						"An error occurred on the server: "+e.getMessage(), 
						HttpStatus.BAD_REQUEST
					);
			
		}
	}
}

/*
* @RestController is different than @Controller
* @RestController includes @Controller within it along with @ResponseBody
* All requests and response for a controller annotated with @RestController go through HttpMessageConverted
* 
* Message converters are used to convert objects into appropriate responses
* For example, if the request to a server includes accept header as application/json then the message converted will convert
* the object to appropirate json format (we use Jackson for the same, and yes, Jackson is a message converter)
* 
* Even for requests the message converter intercepts the data coming in and converts into appropriate objects
* For example when use @RequestBody as a parameter
*/