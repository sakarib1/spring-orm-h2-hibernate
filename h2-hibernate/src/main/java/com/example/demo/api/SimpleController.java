package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeDAO;

@RestController
public class SimpleController {
	
	@Autowired
	EmployeeDAO emplaoyeeDAO;
	
	@GetMapping("/greetings")
	public String getMessage(){
		
		emplaoyeeDAO.listEmployees();
		
		return "Hello, Welcome!!";
	}

}
