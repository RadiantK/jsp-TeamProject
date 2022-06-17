package com.shop.config;

import com.shop.dao.MemberDao;
import com.shop.dao.OrdersDao;
import com.shop.service.MemberEditService;
import com.shop.service.MemberLoginService;
import com.shop.service.MemberRegistService;
import com.shop.service.NoneMemberLoginService;

public class ServiceConfig {

	private MemberDao memberDao;
	private OrdersDao ordersDao;
	
	private MemberRegistService memberRegistService;
	private MemberLoginService memberLoginService;
	private NoneMemberLoginService noneMemberLoginService;
	private MemberEditService memberEditService;
	
	private static ServiceConfig serviceConfig = null;
	
	private ServiceConfig() {
		// dao 초기화
		memberDao = MemberDao.getInstance();
		ordersDao = OrdersDao.getInstance();
		
		// 서비스 초기화
		memberRegistService = new MemberRegistService(memberDao);
		memberLoginService = new MemberLoginService(memberDao);
		noneMemberLoginService = new NoneMemberLoginService(ordersDao);
		memberEditService = new MemberEditService(memberDao);
	}
	
	public static ServiceConfig getInstance() {
		if(serviceConfig == null) {
			serviceConfig = new ServiceConfig();
		}
		return serviceConfig;
	}

	public MemberRegistService getMemberRegistService() {
		return memberRegistService;
	}

	public MemberLoginService getMemberLoginService() {
		return memberLoginService;
	}

	public NoneMemberLoginService getNoneMemberLoginService() {
		return noneMemberLoginService;
	}

	public MemberEditService getMemberEditService() {
		return memberEditService;
	}

}
