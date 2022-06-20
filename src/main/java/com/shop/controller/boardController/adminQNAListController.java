package com.shop.controller.boardController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.QNADao;
import com.shop.dto.QNA;

@WebServlet("/admin/QNA/List")
public class adminQNAListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		QNADao dao=QNADao.getInstance();
		ArrayList<QNA> list=dao.QNAList(startRow, endRow);
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
		
		
		req.getRequestDispatcher("/WEB-INF/page/admin/adminQNAList.jsp").forward(req, resp);
	}
}
