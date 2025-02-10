package com.assignment4.assignment4_loginDemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.assignment4.assignment4_loginDemo.model.Contact;

@Repository
public class ContactDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int saveContact(Contact contact){
	String sql="insert into contact (name,mobile,message) values (?,?,?)";
	return jdbcTemplate.update(sql,contact.getName(),contact.getMobile(),contact.getMessage());
	}
	
	
	public List<Contact>getMessages(){
		String sql="select * from contact";
		
		return jdbcTemplate.query(sql, (rs,rowNum)->new Contact(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getString("mobile"),
				rs.getString("message")

				));
}
}
