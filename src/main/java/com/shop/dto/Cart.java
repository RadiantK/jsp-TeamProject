package com.shop.dto;

public class Cart {

	private int cartNum; // 장바구니 번호
	private int memberNum; // 회원번호
	
	public Cart() {}

	public Cart(int cartNum, int memberNum) {
		this.cartNum = cartNum;
		this.memberNum = memberNum;
	}

	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	
}
