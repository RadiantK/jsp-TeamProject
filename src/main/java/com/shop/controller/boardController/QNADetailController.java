package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.dao.QNACommentDao;
import com.shop.dao.QNADao;
import com.shop.dto.QNA;
import com.shop.dto.QNAComment;

@WebServlet("/board/QNA/Detail")
public class QNADetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		QNADao dao=QNADao.getInstance();
		QNA vo=dao.QNADetail(qnaNum);
		int n=dao.isComment(qnaNum);
		if(n==0) {
			QNA dto=new QNA(qnaNum, null, null, null, null, null, null, null);
			int a=dao.noState(dto);
		}else if(n>=1) {
			QNA dto=new QNA(qnaNum, null, null, null, null, null, null, null);
			int a=dao.isState(dto);
		}
		
		QNACommentDao cdao=QNACommentDao.getInstance();
		QNAComment dto=cdao.QNACommentDetail(qnaNum);
		
		
		
		req.setAttribute("vo", vo);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/WEB-INF/page/board/QNA_Detail.jsp").forward(req, resp);		
	}
}
