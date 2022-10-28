package com.pushyourself11.Entity;

public class LoginResponse {

	private final String jwt;
	
	private int userId;
	
	private String firstname;
	
	
	
	public LoginResponse(String jwt , int userid , String name) {
		this.jwt = jwt;
		this.userId = userid;
		this.firstname = name;
		
	}
	
	public String getJwt() {
		return jwt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return firstname;
	}

	public void setName(String name) {
		this.firstname = name;
	}
	
	
	
	
}
