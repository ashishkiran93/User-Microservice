package com.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.exceptions.UserNotFoundException;


public interface UserService {

	public User addUser(User user);
	public User getUserById(int id) throws UserNotFoundException;
	public User updateUser(User user, String username);
	public User deleteUser(int id) throws UserNotFoundException;
	public List<User> getAllUsers();
}
