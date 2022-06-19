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
import com.shop.service.KakaoLoginService;

@SuppressWarnings("serial")
@WebServlet("/user/kakao/login")
public class KakaoLoginController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String kakaoEmail = request.getParameter("kakaoEmail");
		String kakaoGender = request.getParameter("kakaoGender");
		
		ServiceConfig config = ServiceConfig.getInstance();
		KakaoLoginService service = config.getKakaoLoginService();
		try {
			LoginCommand login = service.kakaoLogin(kakaoEmail);
			
			if(login != null) {
				HttpSession session = request.getSession();
				session.setAttribute("sessionId", login.getEmail());
				
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
			request.setAttribute("email", kakaoEmail);
			request.setAttribute("gender", kakaoGender);
			request.getRequestDispatcher("/WEB-INF/page/user/kakaoJoin.jsp")
			.forward(request, response);
		}
	}
}
