package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.QNACommentDao;
import com.shop.dto.QNAComment;

@WebServlet("/board/QNA/Comment/insert")
public class QNACommentInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		String title=req.getParameter("title");
		String nickname=req.getParameter("nickname");
		String content=req.getParameter("content");
		QNAComment dto=new QNAComment(0, null, qnaNum, nickname, title, content, null);
		
		QNACommentDao dao=QNACommentDao.getInstance();
		int n=dao.insertComment(dto);
		
		if(n>0){
			resp.sendRedirect(req.getContextPath()+"/board/QNA/Detail"+"?qnaNum"+"="+qnaNum);
		}else{
			
		}
		
	}

}
