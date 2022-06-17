package com.shop.dto;

import java.sql.Date;

public class Orders {

	private int orderNum;
	private String memberNum;
	private String email;
	private String name;
	private String phone;
	private int amount;
	private String orderPwd;
	private String orderState;
	private Date regdate;
	
	public Orders() {}
	
	public Orders(int orderNum, String memberNum, String email, String name, String phone, int amount, String orderPwd,
			String orderState, Date regdate) {
		super();
		this.orderNum = orderNum;
		this.memberNum = memberNum;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.amount = amount;
		this.orderPwd = orderPwd;
		this.orderState = orderState;
		this.regdate = regdate;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getOrderPwd() {
		return orderPwd;
	}
	public void setOrderPwd(String orderPwd) {
		this.orderPwd = orderPwd;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
