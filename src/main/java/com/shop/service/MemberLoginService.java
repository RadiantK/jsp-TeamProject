package com.shop.service;

import com.shop.command.LoginCommand;
import com.shop.dao.MemberDao;

public class MemberLoginService {

	private MemberDao memberDao;

	public MemberLoginService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	// 회원로그인
	public LoginCommand login(String email, String password) {
		return null;
	}
}
