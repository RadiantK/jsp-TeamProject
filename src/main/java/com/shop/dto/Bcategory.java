package com.shop.dto;

public class Bcategory {
	private int bcategoryNum;
	private String btype;
	
	public Bcategory() {}
	
	public Bcategory(int bcategoryNum, String btype) {
		this.bcategoryNum = bcategoryNum;
		this.btype = btype;
	}
	
	public int getBcategoryNum() {
		return bcategoryNum;
	}
	
	public void setBcategoryNum(int bcategoryNum) {
		this.bcategoryNum = bcategoryNum;
	}
	
	public String getBtype() {
		return btype;
	}
	
	public void setBtype(String btype) {
		this.btype = btype;
	}
	
}
