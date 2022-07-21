package com.allaboutspring.demo.jpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/repository")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	UserController(
		UserRepository userRepository
	) {
		this.userRepository = userRepository;
	}

	@GetMapping("/user")
	public String getUserDetails() {
		Optional<UserEntity> user = userRepository.findById(1);
		System.out.println(user.toString());
		
		//creating new user
		UserEntity newUser = new UserEntity();
		newUser.setAge(30);
		newUser.setName("abc");
		userRepository.save(newUser);
		return "user details"; 
	}
}

/*
 * In this controller we are using a repository to fetch data from the database 
 */
