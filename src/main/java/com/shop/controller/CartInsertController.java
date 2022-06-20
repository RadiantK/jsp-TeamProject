package com.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/user/cart")
public class CartInsertController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productNum_ = request.getParameter("pnum");
		int productNum = 0;
		if(productNum_ != null && !productNum_.equals("")) 
			productNum = Integer.parseInt(productNum_);
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("sessionId");
		
		if(email != null) {
			
		}else {
			
		}
	}
}
