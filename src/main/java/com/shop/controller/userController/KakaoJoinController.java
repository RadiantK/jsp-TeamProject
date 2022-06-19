package com.shop.controller.userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.MemberDao;
import com.shop.dto.Member;

@SuppressWarnings("serial")
@WebServlet("/user/kakao/join")
public class KakaoJoinController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String nickName = request.getParameter("nickName");
		String gender = request.getParameter("gender");
		
		MemberDao memberDao = MemberDao.getInstance();
		Member newMember = new Member(
				"", email, "", "", nickName,"", "", gender, null, 0, 0);
		int n = memberDao.join(newMember);
		
		if(n > 0) {
			response.sendRedirect(request.getContextPath()+"/user/join/success");
		}else {
			response.sendRedirect(request.getContextPath()+"/user/join");
		}
	}
}
