package com.shop.service;

import com.shop.command.LoginCommand;
import com.shop.dao.MemberDao;
import com.shop.dto.Member;
import com.shop.exception.MemberNotFoundException;
import com.shop.exception.WrongIdPasswordException;

public class MemberLoginService {

	private MemberDao memberDao;

	public MemberLoginService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	// 회원로그인
	public LoginCommand login(String email, String password) {
		Member member = memberDao.selectOne(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		if(!member.getPassword().equals(password)) {
			throw new WrongIdPasswordException();
		}
		
		LoginCommand login = memberDao.memberLogin(email, password);
		
		return login;
	}
}
