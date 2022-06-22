package com.shop.command;

import java.sql.Date;

public class SalesCommand {
	private int num;
	private String email;
	private String name;
	private int pnum;
	private int cnt;
	private int amount;
	private String state;
	private Date orderDate;
	
	public SalesCommand() {}
	
	public SalesCommand(int num, String email, String name, int pnum, int cnt,
			int amount, String state, Date orderDate) {
		this.num = num;
		this.email = email;
		this.name = name;
		this.pnum = pnum;
		this.cnt = cnt;
		this.amount = amount;
		this.state = state;
		this.orderDate = orderDate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
