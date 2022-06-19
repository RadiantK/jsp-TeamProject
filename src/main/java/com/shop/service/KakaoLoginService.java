package com.shop.service;

import com.shop.command.LoginCommand;
import com.shop.dao.MemberDao;
import com.shop.dto.Member;
import com.shop.exception.MemberNotFoundException;

public class KakaoLoginService {

	private MemberDao memberDao;

	public KakaoLoginService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public LoginCommand kakaoLogin(String email) { 
		Member member = memberDao.selectOne(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		LoginCommand login = memberDao.kakaoLogin(email);
		return login;
	}
}
