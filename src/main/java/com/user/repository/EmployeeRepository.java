package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {
	
	
	@Query("select e from Employee e where e.username = :username")
	public Employee getDeatilsByUserName(@Param("username") String username);

}
