package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path ="/")
	public String helloWorld() {
		System.out.println("string");
		return "{\"message\":\"Hello \"}";

	}
}
