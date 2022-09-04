package com.user.entity;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class Contact {
	@Id
	//@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long contactId;
    private String conatct_email;
    private String contact_Name;
    private Long userId;
    
  //Constructors and Getters & Setters 
    public Contact() {
    	
    }
    
    



	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getConatct_email() {
		return conatct_email;
	}
	public void setConatct_email(String conatct_email) {
		this.conatct_email = conatct_email;
	}
	public String getContact_Name() {
		return contact_Name;
	}
	public void setContact_Name(String contact_Name) {
		this.contact_Name = contact_Name;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Contact(Long contactId, String conatct_email, String contact_Name, Long userId) {
		super();
		this.contactId = contactId;
		this.conatct_email = conatct_email;
		this.contact_Name = contact_Name;
		this.userId = userId;
	}





	@Override
	public int hashCode() {
		return Objects.hash(conatct_email, contactId, contact_Name, userId);
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(conatct_email, other.conatct_email) && Objects.equals(contactId, other.contactId)
				&& Objects.equals(contact_Name, other.contact_Name) && Objects.equals(userId, other.userId);
	}
	
	
    

}