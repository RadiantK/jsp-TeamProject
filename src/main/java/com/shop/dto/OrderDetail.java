package com.shop.dto;

public class OrderDetail {
	
	private int orderDetailNum;
	private int orderNum;
	private int pnum;
	private String pname;
	private int piece;
	
	public OrderDetail() {}
	
	public OrderDetail(int orderDetailNum, int orderNum, int pnum, String pname, int piece) {
		super();
		this.orderDetailNum = orderDetailNum;
		this.orderNum = orderNum;
		this.pnum = pnum;
		this.pname = pname;
		this.piece = piece;
	}
	public int getOrderDetailNum() {
		return orderDetailNum;
	}
	public void setOrderDetailNum(int orderDetailNum) {
		this.orderDetailNum = orderDetailNum;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPiece() {
		return piece;
	}
	public void setPiece(int piece) {
		this.piece = piece;
	}

}
