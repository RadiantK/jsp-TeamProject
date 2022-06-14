package com.shop.dto;

import java.sql.Date;

public class Notice {
	private int noticeNum;
	private String memberNum;
	private String nickname;
	private String title;
	private String content;
	private Date regdate;
	private int hit;
	
	public Notice() {}

	public Notice(int noticeNum, String memberNum, String nickname, String title, String content, Date regdate,
			int hit) {
		super();
		this.noticeNum = noticeNum;
		this.memberNum = memberNum;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
