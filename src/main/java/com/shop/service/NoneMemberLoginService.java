package com.shop.service;

import com.shop.command.LoginCommand;
import com.shop.dao.OrdersDao;
import com.shop.exception.MemberNotFoundException;

public class NoneMemberLoginService {

	private OrdersDao ordersDao;

	public NoneMemberLoginService(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}
	
	public LoginCommand nonMemberLogin(String email, String orderPwd) {
		LoginCommand login = ordersDao.nonMemberLogin(email, orderPwd);
		
		if(login == null) {
			throw new MemberNotFoundException();
		}
		
		return login;
	}
}
