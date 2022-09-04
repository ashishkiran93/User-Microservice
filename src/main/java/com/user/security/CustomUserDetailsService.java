package com.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.user.entity.Employee;
import com.user.repository.EmployeeRepository;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	public EmployeeRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = repo.getDeatilsByUserName(username);
		
		UserDetails employeeUserDetails = new CustomUserDetails(employee);
		return employeeUserDetails;
	}

}
