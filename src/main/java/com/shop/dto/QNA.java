package com.shop.dto;

import java.sql.Date;

public class QNA {
	private int qnaNum;
	private String memberNum;
	private String nickname;
	private String title;
	private String content;
	private String image;
	private Date regdate;
	private String qnastate;
	
	public QNA(int qnaNum, String memberNum, String nickname, String title, String content, String image, Date regdate,
			String qnastate) {
		super();
		this.qnaNum = qnaNum;
		this.memberNum = memberNum;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.image = image;
		this.regdate = regdate;
		this.qnastate = qnastate;
	}
	public QNA() {}
	
	public int getQnaNum() {
		return qnaNum;
	}
	public void setQnaNum(int qnaNum) {
		this.qnaNum = qnaNum;
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
	public String getImage() {
		return image;
	}
	public String getQnastate() {
		return qnastate;
	}
	public void setQnastate(String qnastate) {
		this.qnastate = qnastate;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	

}
