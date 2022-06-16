package com.shop.controller.ordersController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/orderOK")
public class OrderOKController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	
		req.getRequestDispatcher("/WEB-INF/page/orders/orderOK.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// form 파라미터 받아오기 
		
		// 주문서테이블 insert
		
		// 주문번호 가져오기
		
		// 주문서상세테이블 insert
		
		// 배송테이블 insert
		
		// 결제테이블 insert
		
		
		
	}
}
