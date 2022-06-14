package com.shop.dto;

public class Scategory {
	private int scategoryNum;
	private String btype;
	private String stype;
	
	public Scategory() {}

	public Scategory(int scategoryNum, String btype, String stype) {
		this.scategoryNum = scategoryNum;
		this.btype = btype;
		this.stype = stype;
	}

	public int getScategoryNum() {
		return scategoryNum;
	}

	public void setScategoryNum(int scategoryNum) {
		this.scategoryNum = scategoryNum;
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
	
}
