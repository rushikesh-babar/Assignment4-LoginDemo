package com.assignment4.assignment4_loginDemo.model;

public class User {

	private int userId;
	private String name;
	private String email;
	private String type;
	private String password;
	
	public User() {};

	public User(int userId, String name, String email, String type, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.type = type;
		this.password = password;
	}
	
		public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", type=" + type + ", password="
				+ password + "]";
	}
	
	
	
}
