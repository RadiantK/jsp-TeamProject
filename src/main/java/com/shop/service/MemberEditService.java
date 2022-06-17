package com.shop.service;

import com.shop.command.JoinCommand;
import com.shop.dao.MemberDao;
import com.shop.dto.Member;
import com.shop.exception.DuplicateNickNameException;

public class MemberEditService {

	MemberDao memberDao;

	public MemberEditService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public int MemberEdit(JoinCommand command) {
		Member member = memberDao.selectOne(command.getEmail());
		int duple = memberDao.findByNickName(command.getNickName());
		
		// 닉네임 중복체크
		if(duple > 0) {
			// 원래 사용하던 닉네임이 아니면
			if(!member.getNickName().equals(command.getNickName())) {
				throw new DuplicateNickNameException();
			}
		}
		// 회원정보수정
		int n = memberDao.editInfo(command);
		return n;
	}
}
