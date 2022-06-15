package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.QNADao;
import com.shop.dto.QNA;

@WebServlet("/board/QNA/Detail")
public class QNADetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		QNADao dao=QNADao.getInstance();
		QNA vo=dao.QNADetail(qnaNum);
		
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/WEB-INF/page/board/QNA_Detail.jsp").forward(req, resp);		
	}
}
