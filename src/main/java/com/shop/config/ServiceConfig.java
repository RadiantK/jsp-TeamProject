package com.shop.config;

import com.shop.dao.MemberDao;
import com.shop.service.MemberLoginService;
import com.shop.service.MemberRegistService;

public class ServiceConfig {

	private MemberDao memberDao;
	
	private MemberRegistService memberRegistService;
	private MemberLoginService memberLoginService;
	
	private static ServiceConfig serviceConfig = null;
	
	private ServiceConfig() {
		// dao 초기화
		memberDao = MemberDao.getInstance();
		
		// 서비스 초기화
		memberRegistService = new MemberRegistService(memberDao);
		memberLoginService = new MemberLoginService(memberDao);
	}
	
	public static ServiceConfig getInstance() {
		if(serviceConfig == null) {
			serviceConfig = new ServiceConfig();
		}
		return serviceConfig;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegistService getMemberRegistService() {
		return memberRegistService;
	}

	public MemberLoginService getMemberLoginService() {
		return memberLoginService;
	}

}
