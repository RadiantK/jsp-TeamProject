package com.shop.controller.boardController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.shop.dao.FAQDao;
import com.shop.dao.NoticeDao;
import com.shop.dto.FAQ;
import com.shop.dto.Notice;
@WebServlet("/board/FAQ/List")
public class FAQListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String spageNum=req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		FAQDao dao=FAQDao.getInstance();
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		ArrayList<FAQ> list=dao.FAQList(startRow, endRow , field, keyword);
		int count=dao.getCount(field,keyword); // 전체 글의 갯수
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
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.getRequestDispatcher("/WEB-INF/page/board/FAQ_List.jsp").forward(req, resp);
	}
}
	
