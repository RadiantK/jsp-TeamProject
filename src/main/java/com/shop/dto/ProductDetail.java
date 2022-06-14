package com.shop.dto;

public class ProductDetail {
	private int productdetailNum;
	private int productNum;
	private String images;
	
	public ProductDetail() {}

	public ProductDetail(int productdetailNum, int productNum, String images) {
		this.productdetailNum = productdetailNum;
		this.productNum = productNum;
		this.images = images;
	}

	public int getProductdetailNum() {
		return productdetailNum;
	}

	public void setProductdetailNum(int productdetailNum) {
		this.productdetailNum = productdetailNum;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	
}
