package com.assignment4.assignment4_loginDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment4.assignment4_loginDemo.dao.ContactDao;
import com.assignment4.assignment4_loginDemo.model.Contact;

@Service
public class ContactService {
	 @Autowired
		ContactDao contactDao;
	    
	    public int saveContact(Contact contact) {
	    	
	    	return contactDao.saveContact(contact);
	    }
		
	    public List<Contact> getMessages(){
	    	return contactDao.getMessages();
}
}
