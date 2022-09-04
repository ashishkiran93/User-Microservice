package com.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "admin")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public int userId;
    private String User_Name;
    private String User_phone;
     
    @Embedded
    List<Contact> contacts=new ArrayList<>();
    
   //Constructors and Getters & Setters 
    public User() {
      }
    
    

	public User(String user_Name, String user_phone) {
	super();
	User_Name = user_Name;
	User_phone = user_phone;
}

    

	public User(int userId, String user_Name, String user_phone, List<Contact> contacts) {
		super();
		this.userId = userId;
		User_Name = user_Name;
		User_phone = user_phone;
		this.contacts = contacts;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getUser_phone() {
		return User_phone;
	}

	public void setUser_phone(String user_phone) {
		User_phone = user_phone;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}



	@Override
	public int hashCode() {
		return Objects.hash(User_Name, User_phone, contacts, userId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(User_Name, other.User_Name) && Objects.equals(User_phone, other.User_phone)
				&& Objects.equals(contacts, other.contacts) && userId == other.userId;
	}

	

}