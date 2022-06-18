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
@WebServlet("/user/search/where/em")
public class SearchEmailAjaxController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		MemberDao memberDao = MemberDao.getInstance();
		String email = memberDao.searchEmail(name, phone);
		
		JSONObject jo = new JSONObject();
		if(email != null) {
			jo.put("code", true);
			jo.put("em", email);
		}else {
			jo.put("code", false);
		}
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(jo);
	}
}
