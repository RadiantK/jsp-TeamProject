package com.shop.service;

import com.shop.command.JoinCommand;
import com.shop.dao.MemberDao;
import com.shop.dto.Member;
import com.shop.exception.DuplicateMemberException;
import com.shop.exception.DuplicateNickNameException;

public class MemberRegistService {
	
	private MemberDao memberDao;

	public MemberRegistService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	// 회원등록
	public int MemberRegist(JoinCommand req) {
		Member member = memberDao.selectOne(req.getEmail());
		if(member != null) {
			throw new DuplicateMemberException();
		}
		
		int duple = memberDao.findByNickName(req.getNickName());
		if(duple > 0) {
			throw new DuplicateNickNameException();
		}
		
		Member newMember = new Member(
				"", req.getEmail(), req.getPassword(), req.getName(), req.getNickName(),
				req.getPhone(), req.getAddress(), req.getGender(), null, 0, 0);
		
		int n = memberDao.join(newMember);
		
		return n;
	}
}
