package com.user.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.exceptions.UserNotFoundException;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userrepo;
	
	
	
	public UserServiceImpl() {
		super();
	}

	public UserServiceImpl(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	@Override
	public User addUser(User user) {
		
		return userrepo.save(user);
	}

	@Override
	public User getUserById(int id) throws UserNotFoundException{
		Optional<User> user = userrepo.findById(id);
		return user.orElseThrow(()->new UserNotFoundException());
	}

	@Override
	public User updateUser(User user, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(int id) throws UserNotFoundException {
		Optional<User> user = userrepo.findById(id);
		User targetUser = user.orElseThrow(()-> new UserNotFoundException());
		userrepo.deleteById(id);
		return targetUser;
	}

	@Override
	public List<User> getAllUsers() {
		return userrepo.findAll();
	}

}
