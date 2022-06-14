package com.shop.dto;

import java.sql.Date;

public class FAQ {
	private int faqNum;
	private String memberNum;
	private String nickname;
	private String title;
	private String content;
	private Date regdate;
	public FAQ () {}
	public FAQ(int faqNum, String memberNum, String nickname, String title, String content, Date regdate) {
		super();
		this.faqNum = faqNum;
		this.memberNum = memberNum;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	public int getFaqNum() {
		return faqNum;
	}
	public void setFaqNum(int faqNum) {
		this.faqNum = faqNum;
	}
	public String getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
