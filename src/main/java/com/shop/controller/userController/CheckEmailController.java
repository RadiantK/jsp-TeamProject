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

@SuppressWarnings("serial")
@WebServlet("/user/join/check_email")
public class CheckEmailController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email_ = request.getParameter("email");
		String email = "";
		if(email_ != null && !email_.equals("")) email = email_;
		
		MemberDao memberDao = MemberDao.getInstance();
		int n = memberDao.findByEmail(email);
		
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
