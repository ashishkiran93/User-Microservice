package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.Employee;
import com.user.repository.EmployeeRepository;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public Employee saveNewEmployee(@RequestBody Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		return repo.save(employee);
	}
	
	@GetMapping("/getemployee/{username}")
	public ResponseEntity<Employee> getemployee(@PathVariable("username") String username) {
		Employee employee = repo.getDeatilsByUserName(username);
		return new ResponseEntity(employee,HttpStatus.FOUND);
	}
	
	@GetMapping("/message")
	public String wishEmployee() {
		return "Hello Employee How are you";
	}

}
