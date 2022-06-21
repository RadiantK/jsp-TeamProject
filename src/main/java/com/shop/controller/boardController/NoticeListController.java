package com.shop.controller.boardController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.shop.dao.NoticeDao;
import com.shop.dto.Notice;

@WebServlet("/board/Notice/List")
public class NoticeListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		NoticeDao dao=NoticeDao.getInstance();
		ArrayList<Notice> list=dao.NoticeList(startRow, endRow);
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
		req.getRequestDispatcher("/WEB-INF/page/board/Notice_List.jsp").forward(req, resp);
	}
}
