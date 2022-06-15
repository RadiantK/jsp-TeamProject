package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.NoticeDao;

@WebServlet("/board/Notice/Delete")
public class NoticeDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noticeNum=Integer.parseInt(req.getParameter("noticeNum"));
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.NoticeDelete(noticeNum);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/Notice/List");
		}else {
			System.out.println("삭제실패!");
		}
	}

}
