package com.shop.controller.adminController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.MemberDao;
import com.shop.dto.Member;

@SuppressWarnings("serial")
@WebServlet("/admin/user/list")
public class AdminUserListController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page_ = request.getParameter("p");
		int page = 1;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		String type_ = request.getParameter("t");
		String type = "member_num";
		if(type_ != null && !type_.equals("")) type = type_;
		
		String query_ = request.getParameter("q");
		String query = "";
		if(query_ != null && !query_.equals("")) query = query_;
		
		int startNum = 1 + (page - 1) * 10; // 시작번호
		int endNum = page * 10; // 끝번호
		
		MemberDao memberDao = MemberDao.getInstance();
		List<Member> list = memberDao.selectList(startNum, endNum, type, query);
		int count = memberDao.getCount(type, query);
		
		int pageCount = (int)Math.ceil(count / 10.0); // 전체 페이지수
		int startPage = ((page - 1) / 5 * 5) + 1; // 시작페이지
		int endPage = startPage + 4;
		if(endPage > pageCount) endPage = pageCount;
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.getRequestDispatcher("/WEB-INF/page/admin/adminUserList.jsp")
		.forward(request, response);
	}
}
