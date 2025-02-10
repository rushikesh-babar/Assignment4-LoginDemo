package com.assignment4.assignment4_loginDemo.model;

public class Contact {

	private int id;
	private String name;
	private String mobile;
	private String message;
	public Contact(int id, String name, String mobile, String message) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", mobile=" + mobile + ", message=" + message + "]";
	}
}
