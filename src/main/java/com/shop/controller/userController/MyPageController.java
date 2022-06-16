package com.shop.controller.userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.dao.MemberDao;
import com.shop.dto.Member;

@SuppressWarnings("serial")
@WebServlet("/user/mypage/profile")
public class MyPageController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("sessionId");
		
		MemberDao memberDao = MemberDao.getInstance();
		Member member = memberDao.selectOne(email);
		request.setAttribute("m", member);
		
		request.getRequestDispatcher("/WEB-INF/page/mypage/mypage.jsp")
		.forward(request, response);
	}
}
