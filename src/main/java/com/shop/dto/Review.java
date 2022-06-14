package com.shop.dto;

import java.sql.Date;

public class Review {
	private int reviewNum;
	private int productNum;
	private String nickname;
	private String image;
	private String content;
	private int score;
	private Date regdate;
	
	public Review() {}

	public Review(int reviewNum, int productNum, String nickname, String image, String content, int score,
			Date regdate) {
		this.reviewNum = reviewNum;
		this.productNum = productNum;
		this.nickname = nickname;
		this.image = image;
		this.content = content;
		this.score = score;
		this.regdate = regdate;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
