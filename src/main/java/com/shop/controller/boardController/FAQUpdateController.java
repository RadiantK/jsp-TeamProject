package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.FAQDao;
import com.shop.dto.FAQ;

@WebServlet("/board/FAQ/Update")
public class FAQUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int faqNum=Integer.parseInt(req.getParameter("faqNum"));
		FAQDao dao=FAQDao.getInstance();
		FAQ vo=dao.FAQDetail(faqNum);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/WEB-INF/page/board/FAQ_updateForm.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int faqNum=Integer.parseInt(req.getParameter("faqNum"));
		String title=req.getParameter("title");
		String nickname=req.getParameter("nickname");
		String content=req.getParameter("content");
		
		FAQDao dao=FAQDao.getInstance();
		FAQ vo=new FAQ(faqNum, null, nickname, title, content, null);
		int n=dao.updateFAQ(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/FAQ/List");
			
		}else {
			System.out.println("1:1문의 수정 실패!");
		}
	}

}
