package com.shop.controller.userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.dao.MemberDao;

@SuppressWarnings("serial")
@WebServlet("/user/mypage/delmem")
public class DeleteMemberController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("sessionId");
		
		MemberDao memberDao = MemberDao.getInstance();
		int n = memberDao.withdraw(email);
		
		if(n > 0) {
			response.sendRedirect(request.getContextPath()+"/user/logout");
		}else {
			request.setAttribute("errorMsg", "회원탈퇴중 오류가 발생했습니다.");
			request.getRequestDispatcher("/WEB-INF/page/mypage/deleteMember.jsp")
			.forward(request, response);
		}
	}
}
