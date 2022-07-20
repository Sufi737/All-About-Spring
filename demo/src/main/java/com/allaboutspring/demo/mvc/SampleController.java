package com.allaboutspring.demo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class SampleController {
	
	@RequestMapping(value="/{name}", method=RequestMethod.GET)
	public String sayHello(
		@PathVariable("name") String name,
		@RequestParam(name="age", required=false) Integer age,
		HttpServletRequest request,
		ModelMap model
	) {
		System.out.println("Name: "+name);
		System.out.println("Age: "+request.getParameter("age"));
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "say_hello";
	}
}

/*
The @RequestMapping annotation maps the controller to a URL path, we  can specify the method type in this annotation
We can use @PostMapping, @GetMapping and so on as well instead of @RequestMapping

Here we are using @PathVariable to map certain parameter which is in the URL itself
For example above, the name will be mapped in this URL https://localhost:8080/hello/sufyan so name=sufyan

@RequestParam is different than @PathVariable as those params are either passed in request body or as params in the URL
There is also another way to access parameters using HttpServletRequest

Here we are using model.addAttribute() which adds the data to our view which is a JSP page
*/