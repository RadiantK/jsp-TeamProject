package com.shop.controller.userController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.shop.dao.MemberDao;
import com.shop.dto.Member;

@SuppressWarnings("serial")
@WebServlet("/user/check_nick")
public class CheckNickNameController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nickName_ = request.getParameter("nickName");
		String nickName = "";
		
		if(nickName_ != null && !nickName_.equals("")) nickName = nickName_;
		
		MemberDao memberDao = MemberDao.getInstance();
		int n = memberDao.findByNickName(nickName);
		
		JSONObject jo = new JSONObject();
		
		// 회원정보 수정 시 자신의 닉네임을 표시
		HttpSession session = request.getSession();
		String myEmail = (String) session.getAttribute("sessionId");
		if(myEmail != null) {
			Member member = memberDao.selectOne(myEmail);
			if(member.getNickName().equals(nickName)) {
				jo.put("nick", "myNickName");
			}
		}
		
		if(n > 0) {
			jo.put("code", true);
		}else {
			jo.put("code", false);
		}
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(jo);
	}
}
