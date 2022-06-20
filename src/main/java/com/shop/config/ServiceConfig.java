package com.shop.config;

import com.shop.dao.CartDao;
import com.shop.dao.CartDetailDao;
import com.shop.dao.MemberDao;
import com.shop.dao.OrdersDao;
import com.shop.dao.ProductDao;
import com.shop.service.KakaoLoginService;
import com.shop.service.MemberCartAddService;
import com.shop.service.MemberEditService;
import com.shop.service.MemberLoginService;
import com.shop.service.MemberRegistService;
import com.shop.service.NoneMemberLoginService;

public class ServiceConfig {

	private MemberDao memberDao;
	private OrdersDao ordersDao;
	private ProductDao productDao;
	private CartDao cartDao;
	private CartDetailDao cartDetailDao;
	
	private MemberRegistService memberRegistService;
	private MemberLoginService memberLoginService;
	private NoneMemberLoginService noneMemberLoginService;
	private MemberEditService memberEditService;
	private KakaoLoginService kakaoLoginService;
	private MemberCartAddService memberCartAddService;
	
	private static ServiceConfig serviceConfig = null;
	
	private ServiceConfig() {
		// dao 초기화
		memberDao = MemberDao.getInstance();
		ordersDao = OrdersDao.getInstance();
		productDao = ProductDao.getInstance();
		cartDao = CartDao.getInstance();
		cartDetailDao = CartDetailDao.getInstance();
		
		// 서비스 초기화
		memberRegistService = new MemberRegistService(memberDao);
		memberLoginService = new MemberLoginService(memberDao);
		noneMemberLoginService = new NoneMemberLoginService(ordersDao);
		memberEditService = new MemberEditService(memberDao);
		kakaoLoginService = new KakaoLoginService(memberDao);
		memberCartAddService = new MemberCartAddService(
				cartDao, cartDetailDao, productDao, memberDao);
				
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

	public KakaoLoginService getKakaoLoginService() {
		return kakaoLoginService;
	}

	public MemberCartAddService getMemberCartAddService() {
		return memberCartAddService;
	}
	
}
