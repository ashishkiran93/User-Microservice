package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.config.ApplicationConfig;
import com.user.entity.User;
import com.user.exceptions.UserNotFoundException;
import com.user.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    UserService userService;

	/*@Autowired
	UserController(UserService userService)
	{
		this.userService=userService;
	}*/
	
     @Autowired	
     ApplicationConfig config;

    //@Autowired
    //private RestTemplate restTemplate;

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user)
    {
    	User saveduser = userService.addUser(user);
		return saveduser;
    }
    
    @GetMapping("/userid/{id}")
    public User getUser(@PathVariable int id) throws UserNotFoundException
    {
    	User saveduser = userService.getUserById(id);
    	
    	List contacts =null;
    	
    	//http://localhost:8080/contact/contactid/userid
    	try {
    		 contacts = config.resttemplate().getForObject("http://localhost:9002/contact/contactid/" + saveduser.getUserId(), List.class);
    	}
    	catch(Exception e) {
    		e.getMessage();
    	}
    	
        saveduser.setContacts(contacts);
		return saveduser;
    }
    
    @GetMapping("/allusers")
    public List<User> getAll()
    {
		return userService.getAllUsers();
     }
    
    @DeleteMapping("/delete/{id}")
    public String deleteuser(@PathVariable int id) throws UserNotFoundException
    {
    	userService.deleteUser(id);
    	return "user with useridch" +id+ "deleted";
    }
}
