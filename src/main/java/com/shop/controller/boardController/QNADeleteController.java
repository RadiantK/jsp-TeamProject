package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.QNADao;

@WebServlet("/board/QNA/Delete")
public class QNADeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		QNADao dao=QNADao.getInstance();
		int n=dao.QNADelete(qnaNum);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/QNA/List");
		}else {
			System.out.println("삭제실패!");
		}
	}
}
