package com.shop.dto;

import java.sql.Date;

public class Member {
	private String memberNum;
	private String email;
	private String password;
	private String name;
	private String nickName;
	private String phone;
	private String gender;
	private Date regDate;
	private String orderPwd;
	private int available;
	private int role;
	
	public Member() {}

	public Member(String memberNum, String email, String password, String name, 
			String nickName, String phone, String gender, Date regDate, 
			String orderPwd, int available, int role) {
		this.memberNum = memberNum;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.phone = phone;
		this.gender = gender;
		this.regDate = regDate;
		this.orderPwd = orderPwd;
		this.available = available;
		this.role = role;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getOrderPwd() {
		return orderPwd;
	}

	public void setOrderPwd(String orderPwd) {
		this.orderPwd = orderPwd;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
