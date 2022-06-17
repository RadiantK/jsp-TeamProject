package com.shop.dto;

import java.sql.Date;

public class Payment {

	private int paymentNum;
	private int orderNum;
	private String means;
	private int amount;
	private Date regdate;
	
	public Payment() {}
	
	public Payment(int paymentNum, int orderNum, String means, int amount, Date regdate) {
		super();
		this.paymentNum = paymentNum;
		this.orderNum = orderNum;
		this.means = means;
		this.amount = amount;
		this.regdate = regdate;
	}
	
	public int getPaymentNum() {
		return paymentNum;
	}
	public void setPaymentNum(int paymentNum) {
		this.paymentNum = paymentNum;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getMeans() {
		return means;
	}
	public void setMeans(String means) {
		this.means = means;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
