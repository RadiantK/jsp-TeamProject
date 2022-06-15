package com.shop.dto;

import java.sql.Date;

public class Member {
	private String memberNum;
	private String email;
	private String password;
	private String name;
	private String nickName;
	private String phone;
	private String address;
	private String gender;
	private Date regDate;
	private int available;
	private int role;
	
	public Member() {}

	public Member(String memberNum, String email, String password, String name,
			String nickName, String phone, String address, String gender,
			Date regDate, int available, int role) {
		this.memberNum = memberNum;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.regDate = regDate;
		this.available = available;
		this.role = role;
	}

	public String getMemberNum() {
		return memberNum;
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

	public Date getRegDate() {
		return regDate;
	}

	public int getAvailable() {
		return available;
	}

	public int getRole() {
		return role;
	}


}
