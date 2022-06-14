package com.shop.dto;

import java.sql.Date;

public class QNAComment {
	private int qnaCommentNum;
	private String memberNum;
	private int qnaNum;
	private String nickname;
	private String title;
	private String content;
	private Date regdate;
	
	public QNAComment() {}

	public QNAComment(int qnaCommentNum, String memberNum, int qnaNum, String nickname, String title, String content,
			Date regdate) {
		super();
		this.qnaCommentNum = qnaCommentNum;
		this.memberNum = memberNum;
		this.qnaNum = qnaNum;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public int getQnaCommentNum() {
		return qnaCommentNum;
	}

	public void setQnaCommentNum(int qnaCommentNum) {
		this.qnaCommentNum = qnaCommentNum;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public int getQnaNum() {
		return qnaNum;
	}

	public void setQnaNum(int qnaNum) {
		this.qnaNum = qnaNum;
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
