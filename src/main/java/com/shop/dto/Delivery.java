package com.shop.dto;

import java.sql.Date;

public class Delivery {
	
	private int deliveryNum;
	private int orderNum;
	private String delName;
	private String delPhone;
	private String address;
	private String delMsg;
	private Date deliDate;
	
	public Delivery() {}
	
	public Delivery(int deliveryNum, int orderNum, String delName, String delPhone, String address, String delMsg,
			Date deliDate) {
		super();
		this.deliveryNum = deliveryNum;
		this.orderNum = orderNum;
		this.delName = delName;
		this.delPhone = delPhone;
		this.address = address;
		this.delMsg = delMsg;
		this.deliDate = deliDate;
	}
	public int getDeliverNum() {
		return deliveryNum;
	}
	public void setDeliverNum(int deliverNum) {
		this.deliveryNum = deliverNum;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getDelName() {
		return delName;
	}
	public void setDelName(String delName) {
		this.delName = delName;
	}
	public String getDelPhone() {
		return delPhone;
	}
	public void setDelPhone(String delPhone) {
		this.delPhone = delPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public String getDelMsg() {
		return delMsg;
	}
	public void setDelMsg(String delMsg) {
		this.delMsg = delMsg;
	}
	public Date getDeliDate() {
		return deliDate;
	}
	public void setDeliDate(Date deliDate) {
		this.deliDate = deliDate;
	}

}
