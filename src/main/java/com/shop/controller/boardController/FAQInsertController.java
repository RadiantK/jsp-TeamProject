package com.shop.controller.boardController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.shop.dao.FAQDao;
import com.shop.dto.FAQ;

@WebServlet("/board/FAQ/insert")
public class FAQInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/page/board/FAQ_insertForm.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title=req.getParameter("title");
		String nickname=req.getParameter("nickname");
		String content=req.getParameter("content");

		FAQDao dao=FAQDao.getInstance();
		int n=dao.insertFAQ(new FAQ(0, null, nickname, title, content, null));

		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/FAQ/List");
		}else {
			System.out.println("공지등록 실패");
		}


	}

}
