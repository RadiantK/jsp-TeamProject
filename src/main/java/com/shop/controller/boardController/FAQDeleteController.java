package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.FAQDao;

@WebServlet("/board/FAQ/Delete")
public class FAQDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int faqNum=Integer.parseInt(req.getParameter("faqNum"));
		FAQDao dao=FAQDao.getInstance();
		int n=dao.deleteFAQ(faqNum);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/FAQ/List");
		}else {
			System.out.println("삭제실패!");
		}
	}
}
