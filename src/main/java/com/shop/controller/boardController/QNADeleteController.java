package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.QNACommentDao;
import com.shop.dao.QNADao;

@WebServlet("/board/QNA/Delete")
public class QNADeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 문의 답변이 있을 경우 삭제가 안되기 때문에 문의 답변 삭제 후 문의 내용 삭제되도록 처리
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		QNACommentDao qdao=QNACommentDao.getInstance();
		int n=qdao.QNACommentDelete(qnaNum);
		
		QNADao dao=QNADao.getInstance();
		n=dao.QNADelete(qnaNum);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/QNA/List");
		}else {
			System.out.println("삭제실패!");
		}
	}
}
