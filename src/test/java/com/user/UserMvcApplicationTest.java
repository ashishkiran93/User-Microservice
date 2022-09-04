package com.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.entity.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserMvcApplicationTest {
	
    public  MockMvc mockMvc;
    
    @Autowired
    public WebApplicationContext context;
    
    ObjectMapper om = new ObjectMapper();
    
    
    
    @BeforeEach
    void testSetup() {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        
    }
    
	
	//1. URL: user/adduser
	//   method: POST
    @Test
    void testAddUser() throws Exception {
    User newUser =	new User("Ashish" , "8249447895");
    User newUser1 =	new User("Omm" , "8249447895");
    
    String jsonRequest = om.writeValueAsString(newUser);
    String jsonRequest1 = om.writeValueAsString(newUser1);
    
    System.out.println("----------------------------------------------------");
    System.out.println(jsonRequest);
    System.out.println("----------------------------------------------------");
    
    MvcResult result = mockMvc.perform(post("http://localhost:9001/user/adduser").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();  
                       mockMvc.perform(post("http://localhost:9001/user/adduser").content(jsonRequest1).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
    
    // checking equality                  
   // Assertions.assertEquals(result.getResponse().getStatus(), 200);
    
    }
	
	//2. URL: user/findById
	//   method: GET
	@Test
	void testGetMethod() throws Exception {
		MvcResult result =  mockMvc.perform(get("http://localhost:9001/user/userid/1")).andReturn();  
		Assertions.assertEquals(result.getResponse().getStatus(), 200);
	}
	
	
	//3. URL: user/allusers
	//   method: GET
	@Test
	void testGetAllUsermethod() throws Exception {
		MvcResult result = mockMvc.perform(get("http://localhost:9001/user/allusers")).andReturn();
		Assertions.assertEquals(result.getResponse().getStatus(), 200);
	}
	
	
	//4. URL: user/deleteuser
	//   method: DELETE
	@Test
    void testDeleteMethod() throws Exception {
		MvcResult result = mockMvc.perform(delete("http://localhost:9001/user/delete/2")).andReturn();
		Assertions.assertEquals(result.getResponse().getStatus(), 200);
	}
}
