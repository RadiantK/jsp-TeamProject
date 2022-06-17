package com.shop.controller.userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.JoinCommand;
import com.shop.config.ServiceConfig;
import com.shop.dao.MemberDao;
import com.shop.dto.Member;
import com.shop.exception.DuplicateNickNameException;
import com.shop.service.MemberEditService;

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

		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
		String nickName = request.getParameter("nickName");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		
		ServiceConfig config = ServiceConfig.getInstance();
		MemberEditService service = config.getMemberEditService();
		
		JoinCommand command = new JoinCommand(email, password, "", nickName, phone, "", gender);
		
		try {
			int n = service.MemberEdit(command);
			
			if(n > 0) {
				response.sendRedirect(request.getContextPath()+"/user/mypage/profile");
				return;
			}else {
				request.setAttribute("errorMsg", "수정중 에러가 발생했습니다.");
				request.getRequestDispatcher("/WEB-INF/page/mypage/memberEdit.jsp")
				.forward(request, response);
				return;
			}
			
		}catch(DuplicateNickNameException e) {
			request.setAttribute("dupleNickName", "닉네임이 중복되었습니다.");
			request.getRequestDispatcher("/WEB-INF/page/mypage/memberEdit.jsp")
			.forward(request, response);
		}
	}
}
