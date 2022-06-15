package com.shop.controller.userController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.shop.dao.MemberDao;

@SuppressWarnings("serial")
@WebServlet("/user/join/check_nick")
public class CheckNickNameController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nickName_ = request.getParameter("nickName");
		String nickName = "";
		
		if(nickName_ != null && !nickName_.equals("")) nickName = nickName_;
		
		MemberDao memberDao = MemberDao.getInstance();
		int n = memberDao.findByNickName(nickName);
		JSONObject jo = new JSONObject();
		
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
