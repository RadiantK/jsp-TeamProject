package com.shop.command;

public class ReviewScoreCommand {
	
	public ReviewScoreCommand() {}

	public String getStar(int score) {
		switch (score) {
		case 5:
			return "★★★★★";
		case 4:
			return "★★★★☆";
		case 3:
			return "★★★☆☆";
		case 2:
			return "★★☆☆☆";
		case 1:
			return "★☆☆☆☆";
		default:
			return null;
		}
	}
	
}
