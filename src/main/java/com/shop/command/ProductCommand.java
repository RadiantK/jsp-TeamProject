package com.shop.command;

import java.sql.Date;

public class ProductCommand {
	private int productNum;
	private int scategoryNum;
	private String pname;
	private String pdesc;
	private int price;
	private int discount;
	private int cnt;
	private Date regdate;
	private String image;
	private int productdetailNum;
	private String descImg;
	
	private String btype;
	private String stype;
	
	public ProductCommand() {}
	
	public ProductCommand(int productNum, int scategoryNum, String pname, String pdesc, int price, int discount,
			int cnt, Date regdate, String image, int productdetailNum, String descImg, String btype, String stype) {
		this.productNum = productNum;
		this.scategoryNum = scategoryNum;
		this.pname = pname;
		this.pdesc = pdesc;
		this.price = price;
		this.discount = discount;
		this.cnt = cnt;
		this.regdate = regdate;
		this.image = image;
		this.productdetailNum = productdetailNum;
		this.descImg = descImg;
		this.btype = btype;
		this.stype = stype;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public ProductCommand(int productNum, int scategoryNum, String pname, String pdesc, int price, int discount,
			int cnt, Date regdate, String image, int productdetailNum, String descImg) {
		this.productNum = productNum;
		this.scategoryNum = scategoryNum;
		this.pname = pname;
		this.pdesc = pdesc;
		this.price = price;
		this.discount = discount;
		this.cnt = cnt;
		this.regdate = regdate;
		this.image = image;
		this.productdetailNum = productdetailNum;
		this.descImg = descImg;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getScategoryNum() {
		return scategoryNum;
	}

	public void setScategoryNum(int scategoryNum) {
		this.scategoryNum = scategoryNum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getProductdetailNum() {
		return productdetailNum;
	}

	public void setProductdetailNum(int productdetailNum) {
		this.productdetailNum = productdetailNum;
	}

	public String getDescImg() {
		return descImg;
	}

	public void setDescImg(String descImg) {
		this.descImg = descImg;
	}
	
}
