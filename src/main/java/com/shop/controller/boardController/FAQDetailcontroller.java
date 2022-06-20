package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.FAQDao;
import com.shop.dao.QNADao;
import com.shop.dto.FAQ;
import com.shop.dto.QNA;

@WebServlet("/board/FAQ/Detail")
public class FAQDetailcontroller extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int faqNum=Integer.parseInt(req.getParameter("faqNum"));
		FAQDao dao=FAQDao.getInstance();
		FAQ vo=dao.FAQDetail(faqNum);
		
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/WEB-INF/page/board/FAQ_Detail.jsp").forward(req, resp);		
	}
	
}
