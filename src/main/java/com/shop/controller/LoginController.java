package com.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.LoginCommand;
import com.shop.dao.MemberDao;

@SuppressWarnings("serial")
@WebServlet("/user/login")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/page/user/login.jsp")
		.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email_ = request.getParameter("email");
		String email = "";
		if(email_ != null && !email_.equals("")) email = email_;
		
		String pwd_ = request.getParameter("pwd");
		String pwd = "";
		if(pwd_ != null && !pwd_.equals("")) pwd = pwd_;
		
		System.out.println(email);
		System.out.println(pwd);
		MemberDao memberDao = MemberDao.getInstance();
		LoginCommand login = memberDao.memberLogin(email, pwd);
		
		System.out.println(login);
		if(login != null) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionId", email);
			System.out.println(session.getAttribute("sessionId"));
			if(login.getRole() == 1) {
				response.sendRedirect(request.getContextPath() + "/main");
				return;
			}else {
				response.sendRedirect(request.getContextPath() + "/admin/main");
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	}
}
