package com.user;

import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.user.entity.User;
import com.user.exceptions.UserNotFoundException;
import com.user.repository.UserRepository;
import com.user.service.UserServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceApplicationTest {
	
	

	
	static UserRepository mockRepo = Mockito.mock(UserRepository.class);
	UserServiceImpl service = new UserServiceImpl(mockRepo);
	
	@BeforeAll
	public static void initialSetUp() {
		
		List<User> userList = Arrays.asList(new User("Ashish" , "8249447895"),new User("Ruchita" , "9876543210"));
		
		Mockito.when(mockRepo.save(new User("Ashish" , "8249447895"))).thenReturn(new User(1,"Ashish" , "8249447895",null));
		Mockito.when(mockRepo.findById(1)).thenReturn(Optional.of(new User(1,"Ashish" , "8249447895",null)));
		Mockito.when(mockRepo.findById(50)).thenReturn(Optional.ofNullable(null));
		Mockito.when(mockRepo.findAll()).thenReturn(userList);
		Mockito.when(mockRepo.save(new User("Ashish" , "8249447895"))).thenReturn(new User(1,"Ashish" , "8249447895", null));
	}
	
	
	

	//1. method: addUser
	//   definition: none  
	@Test 
	void testingAddUserMethod() {
		User ExpectedUser = new User(1,"Ashish" , "8249447895", null);
		User ActualUser = service.addUser(new User("Ashish" , "8249447895"));
		Assertions.assertEquals(ExpectedUser, ActualUser );
		
	}
	
	
	//2. method: findById
    //   definition: with correct Input
	
    @Test 
     void testingGetUserByIdMethod() throws UserNotFoundException {
	 User ExpectedUser= new User(1,"Ashish" , "8249447895",null);
	 User ActualUser =  service.getUserById(1);
	
	   Assertions.assertEquals(ActualUser, ExpectedUser);
     }
	

	//3. method: findById
    //   definition: with Incorrect Input
    @Test
    void testingAddUserMethodWithIncorrectInput() throws UserNotFoundException {
	  Assertions.assertThrows(UserNotFoundException.class, ()->service.getUserById(50));
    }
	
	
	
	//4. method: deleteById
    //   definition: with Correct Input
    @Test
    void testDeleteById() throws UserNotFoundException {
    	User ExpectedUser = new User(1,"Ashish" , "8249447895", null);
    	User ActualUser = service.deleteUser(1);
    	
    	Assertions.assertEquals(ActualUser, ExpectedUser);
    }
	
	
	
	
	//5. method: deleteById
    //   definition: with Incorrect Input
    @Test
    void testingDeleteUserMethodWithIncorrectInput() throws UserNotFoundException {
	  Assertions.assertThrows(UserNotFoundException.class, ()->service.deleteUser(50));
    }
	
	
	
	//6. method: getAllUsers
    //   definition: none
    @Test
    void testGetAllMethod() {
    	List<User> ExpectedList = Arrays.asList(new User("Ashish" , "8249447895"),new User("Ruchita" , "9876543210"));
    	List<User> ActualList = service.getAllUsers(); 
    	
    	Assertions.assertEquals(ActualList, ExpectedList);
    	
    }
}
