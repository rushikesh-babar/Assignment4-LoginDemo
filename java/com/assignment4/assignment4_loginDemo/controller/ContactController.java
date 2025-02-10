package com.assignment4.assignment4_loginDemo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment4.assignment4_loginDemo.model.Contact;
import com.assignment4.assignment4_loginDemo.model.User;
import com.assignment4.assignment4_loginDemo.service.ContactService;

import jakarta.servlet.http.HttpSession;

@RestController
public class ContactController {

	 @Autowired
		ContactService contactService;
	     
	     @PostMapping("/save")
	     public ResponseEntity<String>saveContact(@RequestBody Contact contact){
	    	 
	    	 contactService.saveContact(contact);
	    	 return ResponseEntity.ok("Contact Added Successfully");
	     }
	     
	     
	     @GetMapping("/getMessages")
	     public ResponseEntity<?> getMessages(HttpSession session) {
	         User user = (User) session.getAttribute("user");

	         if (user == null || !"admin".equalsIgnoreCase(user.getType())) {
	             System.out.println("Access denied: User is not admin");
	             return ResponseEntity.status(403).body(Map.of("error", "Access Denied"));
	         }

	         System.out.println("Admin user: " + user.getEmail() + " is accessing messages");
	         return ResponseEntity.ok(contactService.getMessages());
	     }
	 }


