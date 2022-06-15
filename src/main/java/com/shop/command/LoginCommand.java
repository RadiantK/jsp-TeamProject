package com.shop.command;

public class LoginCommand {

	private String email;
	private String pwd;
	private int role;
	
	public LoginCommand(String email, String pwd, int role) {
		this.email = email;
		this.pwd = pwd;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public String getPwd() {
		return pwd;
	}

	public int getRole() {
		return role;
	}

}