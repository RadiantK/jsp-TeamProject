package com.shop.dto;

public class CartDetail {
	private int cartDetailNum; // 장바구니 상세번호
	private int cartNum; // 장바구니번호
	private int productNum; // 상품번호
	private String pname; // 상품명
	private int cnt; // 수량
	private int price; // 가격
	
	public CartDetail() {}
	
	public CartDetail(int cartDetailNum, int cartNum, int productNum, String pname, int cnt, int price) {
		this.cartDetailNum = cartDetailNum;
		this.cartNum = cartNum;
		this.productNum = productNum;
		this.pname = pname;
		this.cnt = cnt;
		this.price = price;
	}

	public int getCartDetailNum() {
		return cartDetailNum;
	}

	public void setCartDetailNum(int cartDetailNum) {
		this.cartDetailNum = cartDetailNum;
	}

	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}