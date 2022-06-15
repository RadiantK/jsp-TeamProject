package com.shop.dto;

public class OrderParam {

	private int itemCnt;
	private String itemImg;
	private String itemName;
	private int itemPiece;
	private int itemPrice;
	private int itemCal;
	private int totalCnt;
	private int total;
	private String mnum;
	private String mname;
	private String phone;
	
	public OrderParam() {}

	public OrderParam(int itemCnt, String itemImg, String itemName, int itemPiece, int itemPrice, int itemCal, int totalCnt,
			int total, String mnum, String mname, String phone) {

		this.itemImg = itemImg;
		this.itemName = itemName;
		this.itemPiece = itemPiece;
		this.itemPrice = itemPrice;
		this.itemCal = itemCal;
		this.totalCnt = totalCnt;
		this.total = total;
		this.mnum = mnum;
		this.mname = mname;
		this.phone = phone;
	}

	public String getItemImg() {
		return itemImg;
	}

	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPiece() {
		return itemPiece;
	}

	public void setItemPiece(int itemPiece) {
		this.itemPiece = itemPiece;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemCal() {
		return itemCal;
	}

	public void setItemCal(int itemCal) {
		this.itemCal = itemCal;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getMnum() {
		return mnum;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getItemCnt() {
		return itemCnt;
	}

	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}

}
