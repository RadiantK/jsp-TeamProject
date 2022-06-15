package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.NoticeDao;
import com.shop.dto.Notice;

@WebServlet("/board/Notice/insert")
public class NoticeInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/page/board/Notice_insertForm.jsp").forward(req, resp);

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		String title=req.getParameter("title");
		String nickname=req.getParameter("nickname");
		String content=req.getParameter("content");

		Notice dto=new Notice(0, null, nickname, title, content, null, 0);

		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.insertNotice(dto);

		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/Notice/List");
		}else {
			System.out.println("공지등록 실패");
		}
	}
}
