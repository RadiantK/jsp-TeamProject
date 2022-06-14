package com.shop.dto;

import java.sql.Date;

public class Product {
	private int productNum;
	private int scategoryNum;
	private String code;
	private String pname;
	private int price;
	private int discount;
	private int cnt;
	private Date regdate;
	private String image;
	
	private int bcategoryNum;
	
	public Product() {}

	public Product(int productNum, int scategoryNum, String code, String pname, int price, int discount, int cnt,
			Date regdate, String image, int bcategoryNum) {
		this.productNum = productNum;
		this.scategoryNum = scategoryNum;
		this.code = code;
		this.pname = pname;
		this.price = price;
		this.discount = discount;
		this.cnt = cnt;
		this.regdate = regdate;
		this.image = image;
		this.bcategoryNum = bcategoryNum;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

	public int getBcategoryNum() {
		return bcategoryNum;
	}

	public void setBcategoryNum(int bcategoryNum) {
		this.bcategoryNum = bcategoryNum;
	}

	
}
