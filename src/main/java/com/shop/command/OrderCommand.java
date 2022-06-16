package com.shop.command;

public class OrderCommand {
	
	private int itemNum;
	private String itemImg;
	private String itemName;
	private int price;
	private int discount;
	private int discountPrice;
	private int piece;
	private int itemTotal;
	
	public OrderCommand(int itemNum, String itemImg, String itemName, int price, int discount, int discountPrice,
			int piece, int itemTotal) {
		super();
		this.itemNum = itemNum;
		this.itemImg = itemImg;
		this.itemName = itemName;
		this.price = price;
		this.discount = discount;
		this.discountPrice = discountPrice;
		this.piece = piece;
		this.itemTotal = itemTotal;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
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

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public int getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(int itemTotal) {
		this.itemTotal = itemTotal;
	}
	
}
