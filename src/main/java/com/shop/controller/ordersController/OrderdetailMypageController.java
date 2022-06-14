package com.shop.controller.ordersController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/orderdetailMypage")
public class OrderdetailMypageController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/page/orders/orderDetail_mypage.jsp").forward(req, resp);

	}
}
