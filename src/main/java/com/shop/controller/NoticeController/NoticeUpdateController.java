package com.shop.controller.NoticeController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.CSDao;
import com.shop.dto.Notice;

@WebServlet("/Notice_Update")
public class NoticeUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noticeNum=Integer.parseInt(req.getParameter("noticeNum"));
		CSDao dao=CSDao.getInstance();
		Notice vo=dao.NoticeDetail(noticeNum);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/WEB-INF/board/Notice_updateForm.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noticeNum=Integer.parseInt(req.getParameter("noticeNum"));
		String title=req.getParameter("title");
		String nickname=req.getParameter("nickname");
		String content=req.getParameter("content");
		
		Notice vo=new Notice(noticeNum, null, nickname, title, content, null, 0);
		
		CSDao dao=CSDao.getInstance();
		int n=dao.NoticeUpdate(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/Notice_List");
		}else {
			System.out.println("공지 수정 실패!");
		}
	}
}
