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
@WebServlet("/user/mypage/edit")
public class MemberEditController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 세션에서 이메일값을 얻어옴
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("sessionId");

		// 이메일을 통해서 내 정보 조회
		MemberDao memberDao = MemberDao.getInstance();
		Member member = memberDao.selectOne(email);
		// "member" 변수에 내 정보를 담음
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/page/mypage/memberEdit.jsp")
		.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}
}
