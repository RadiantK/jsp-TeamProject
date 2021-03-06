package com.shop.controller.boardController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.dao.QNADao;
import com.shop.dto.QNA;

@WebServlet("/board/QNA/List")
public class QNAListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String email=(String)session.getAttribute("sessionId");
		
		if(email==null || email.equals("")) { // 세션에 저장된 이메일이 없을 경우 = 비로그인상태(비회원)은 1:1 문의 목록에 아무것도 보이지 않도록 설정.
			String spageNum=req.getParameter("pageNum");
			int pageNum=1;
			if(spageNum!=null) {
				pageNum=Integer.parseInt(spageNum);
			}
			int startRow=(pageNum-1)*10+1;
			int endRow=startRow+9;
			QNADao dao=QNADao.getInstance();
			ArrayList<QNA> list=dao.memberQNAList(startRow, endRow, email);
			int count=dao.getCount();
			int pageCount=(int)Math.ceil(count/10.0); 
			int startPage=((pageNum-1)/10*10)+1; 
			int endPage=startPage+9; 
			if(endPage>pageCount) {
				endPage=pageCount;
			}
			req.setAttribute("list", list);
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageNum", pageNum);
			
			
			req.getRequestDispatcher("/WEB-INF/page/board/QNA_List.jsp").forward(req, resp);
		}
		
		else if(email.equals("admin")) { // 세션에 저장된 이메일이 관리자일 경우 모든 QNA 목록이 보이도록 설정
			String spageNum=req.getParameter("pageNum");
			int pageNum=1;
			if(spageNum!=null) {
				pageNum=Integer.parseInt(spageNum);
			}
			int startRow=(pageNum-1)*10+1;
			int endRow=startRow+9;
			QNADao dao=QNADao.getInstance();
			ArrayList<QNA> list=dao.QNAList(startRow, endRow);
			int count=dao.getCount();
			int pageCount=(int)Math.ceil(count/10.0);
			int startPage=((pageNum-1)/10*10)+1;
			int endPage=startPage+9;
			if(endPage>pageCount) {
				endPage=pageCount;
			}
			req.setAttribute("list", list);
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageNum", pageNum);
			
			
			req.getRequestDispatcher("/WEB-INF/page/admin/adminQNAList.jsp").forward(req, resp);
		}else if(email != null) { // 세션에 저장된 아이디가 null이 아니며 관리자가 아닐경우 (회원) - 세션아이디를 통해 자신의 글만 보이도록 설정
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		QNADao dao=QNADao.getInstance();
		ArrayList<QNA> list=dao.memberQNAList(startRow, endRow, email);
		int count=dao.getCount(); // 전체 글의 갯수
		int pageCount=(int)Math.ceil(count/10.0); // 전체 페이지 갯수
		int startPage=((pageNum-1)/10*10)+1; // 시작 페이지 번호  // ex) (pagenum=10) -> 9/10 = 0 *10 = 0 +1 = 1
		int endPage=startPage+9; // 끝 페이지 번호
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		
		
		req.getRequestDispatcher("/WEB-INF/page/board/QNA_List.jsp").forward(req, resp);
		}
	}

}
