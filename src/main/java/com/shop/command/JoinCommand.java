package com.shop.command;

public class JoinCommand {
	private String email;
	private String password;
	private String name;
	private String nickName;
	private String phone;
	private String address;
	private String gender;
	
	public JoinCommand(String email, String password, String name, String nickName,
			String phone, String address, String gender) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getGender() {
		return gender;
	}
	
}
