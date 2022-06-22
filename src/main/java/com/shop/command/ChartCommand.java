package com.shop.command;

public class ChartCommand {
	private String day;
	private int price;
	
	public ChartCommand() {}

	public ChartCommand(String day, int price) {
		this.day = day;
		this.price = price;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
