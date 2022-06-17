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

@SuppressWarnings("serial")
@WebServlet("/user/mypage/check_pwd")
public class CheckPasswordController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pwd_ = request.getParameter("pwd");
		String pwd = "";
		
		if(pwd_ != null && !pwd_.equals("")) pwd = pwd_;
		
		HttpSession session = request.getSession();
		String myPwd = (String)session.getAttribute("sessionPwd");
		
		JSONObject jo = new JSONObject();
		if(pwd.equals(myPwd)) {
			jo.put("code", true);
		}else {
			jo.put("code", false);
		}
		
		response.setContentType("text/plain; utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(jo);
	}
}
