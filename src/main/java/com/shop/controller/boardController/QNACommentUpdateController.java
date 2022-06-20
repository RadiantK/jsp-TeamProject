package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.QNACommentDao;
import com.shop.dto.QNAComment;

@WebServlet("/board/QNA/Comment/Update")
public class QNACommentUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		QNACommentDao dao=QNACommentDao.getInstance();
		QNAComment vo=dao.QNACommentDetail(qnaNum);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/WEB-INF/page/board/QNA_CommentupdateForm.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		String title=req.getParameter("title");
		String nickname=req.getParameter("nickname");
		String content=req.getParameter("content");		
		
		QNAComment vo=new QNAComment(0, null, qnaNum, nickname, title, content, null);
		
		QNACommentDao dao=QNACommentDao.getInstance();
		int n=dao.updateQNAComment(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/board/QNA/Detail"+"?qnaNum"+"="+qnaNum);
		}else {
			System.out.println("답변 수정 실패!");
		}
	}

}
