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

		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("sessionId");
		
		MemberDao memberDao = MemberDao.getInstance();
		
		Member member = memberDao.selectOne(email);
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/page/mypage/memberEdit.jsp")
		.forward(request, response);
	}
}
