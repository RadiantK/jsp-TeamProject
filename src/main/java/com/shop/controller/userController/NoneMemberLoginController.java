package com.shop.controller.userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.LoginCommand;
import com.shop.config.ServiceConfig;
import com.shop.exception.MemberNotFoundException;
import com.shop.service.NoneMemberLoginService;

@SuppressWarnings("serial")
public class NoneMemberLoginController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		ServiceConfig config = ServiceConfig.getInstance();
		NoneMemberLoginService service = config.getNoneMemberLoginService();
		try {
			
			LoginCommand login = service.nonMemberLogin(email, pwd);
			//로그인 성공시 객체를 담아서 주문서를 보여줄 페이지로 이동시키면 됨
			request.setAttribute("login", login);
			
			LoginCommand re = (LoginCommand)request.getAttribute("login");
			request.getRequestDispatcher("").forward(request, response);
			
		}catch(MemberNotFoundException e) { // 로그인 실패 시
			response.sendRedirect(request.getContextPath()+"/user/login");
		}
	}
}
