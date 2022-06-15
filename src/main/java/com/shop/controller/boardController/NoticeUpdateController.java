package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.NoticeDao;
import com.shop.dto.Notice;

@WebServlet("/board/Notice/Update")
public class NoticeUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noticeNum=Integer.parseInt(req.getParameter("noticeNum"));
		NoticeDao dao=NoticeDao.getInstance();
		Notice vo=dao.NoticeDetail(noticeNum);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/WEB-INF/page/board/Notice_updateForm.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noticeNum=Integer.parseInt(req.getParameter("noticeNum"));
		String title=req.getParameter("title");
		String nickname=req.getParameter("nickname");
		String content=req.getParameter("content");

		Notice vo=new Notice(noticeNum, null, nickname, title, content, null, 0);

		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.NoticeUpdate(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/Notice/List");
		}else {
			System.out.println("공지 수정 실패!");
		}
	}
}
