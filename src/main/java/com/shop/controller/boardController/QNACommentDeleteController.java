package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.QNACommentDao;

@WebServlet("/board/QNA/Comment/Delete")
public class QNACommentDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		QNACommentDao dao=QNACommentDao.getInstance();
		int n=dao.QNACommentDelete(qnaNum);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/QNA/Detail"+"?qnaNum"+"="+qnaNum);
		}else {
			System.out.println("삭제실패!");
		}
	}
}
