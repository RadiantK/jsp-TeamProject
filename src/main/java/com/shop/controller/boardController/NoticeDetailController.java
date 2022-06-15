package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.NoticeDao;
import com.shop.dto.Notice;

@WebServlet("/board/Notice/Detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int noticeNum=Integer.parseInt(req.getParameter("noticeNum"));
		NoticeDao dao=NoticeDao.getInstance();
		Notice vo=dao.NoticeDetail(noticeNum);

		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/WEB-INF/page/board/Notice_Detail.jsp").forward(req, resp);
	}
}
