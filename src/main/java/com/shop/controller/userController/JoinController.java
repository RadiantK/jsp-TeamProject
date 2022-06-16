package com.shop.controller.userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.JoinCommand;
import com.shop.config.ServiceConfig;
import com.shop.exception.DuplicateMemberException;
import com.shop.exception.DuplicateNickNameException;
import com.shop.service.MemberRegistService;

@SuppressWarnings("serial")
@WebServlet("/user/join")
public class JoinController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/page/user/join.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String phone = request.getParameter("phone");
		
		String fullAddress = "";
		String postCode_ = request.getParameter("postcode");
		if(postCode_ != null && !postCode_.equals("")) fullAddress += postCode_ + " ";
		
		String address_ = request.getParameter("address");
		if(address_ != null && !address_.equals("")) fullAddress += address_;
		
		String detailAddress_ = request.getParameter("detailAddress");
		if(detailAddress_ != null && !detailAddress_.equals("")) fullAddress += " " + detailAddress_;
		
		String gender =  request.getParameter("gender");
		
		ServiceConfig config = ServiceConfig.getInstance();
		MemberRegistService service = config.getMemberRegistService();
		
		try {
			JoinCommand jc = new JoinCommand(
					email, pwd, name, nickName, phone, fullAddress, gender);
			int n = service.MemberRegist(jc);
			
			if(n > 0) {
				response.sendRedirect(request.getContextPath()+"/user/join/success");
			}else {
				response.sendRedirect(request.getContextPath()+"/user/join");
			}
		}catch(DuplicateMemberException e) {
			request.setAttribute("errorEmail", "중복된 이메일을 입력하셨습니다.");
			request.getRequestDispatcher("/WEB-INF/page/user/join.jsp")
			.forward(request, response);
		}catch(DuplicateNickNameException e) {
			request.setAttribute("errorNickName", "중복된 닉네임을 입력하셨습니다.");
			request.getRequestDispatcher("/WEB-INF/page/user/join.jsp")
			.forward(request, response);
		}
	}
}
