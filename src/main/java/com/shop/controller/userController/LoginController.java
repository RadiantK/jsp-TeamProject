package com.shop.controller.userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.LoginCommand;
import com.shop.config.ServiceConfig;
import com.shop.exception.MemberNotFoundException;
import com.shop.exception.WrongIdPasswordException;
import com.shop.service.MemberLoginService;

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
		
		ServiceConfig config = ServiceConfig.getInstance();
		MemberLoginService service = config.getMemberLoginService();
		
		try {
			LoginCommand login = service.login(email, pwd);
			
			if(login != null) {
				HttpSession session = request.getSession();
				session.setAttribute("sessionId", email);
				
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
		}catch(MemberNotFoundException e) {
			request.setAttribute("errorEmail", "일차히는 이메일이 존재하지 않습니다.");
			request.getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
		}catch(WrongIdPasswordException e) {
			request.setAttribute("errorPwd", "비밀번호가 일치하지 않습니다.");
			request.getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
		}
		
	}
}
