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
		NoticeDao dao=NoticeDao.getInstance();
		int startRow = (pageNum-1)*10+1;
		int endRow = startRow+9;
		ArrayList<Notice> list=dao.NoticeList(startRow, endRow);
		int pageCount=(int)Math.ceil(dao.getCount()/10.0);
		int startPage=(pageNum-1)/10*10+1;
		int endPage=startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		JSONObject data=new JSONObject();
		JSONArray jarr=new JSONArray();
		for(Notice vo:list){
			JSONObject obj=new JSONObject();
			obj.put("noticeNum", vo.getNoticeNum());
			obj.put("memberNum", vo.getMemberNum());
			obj.put("nickname", vo.getNickname());
			obj.put("title", vo.getTitle());
			obj.put("content", vo.getContent());
			obj.put("regdate", vo.getRegdate());
			obj.put("hit", vo.getHit());
			jarr.put(obj);
		}
		data.put("list", jarr);
		data.put("pageCount", pageCount);
		data.put("startPage", startPage);
		data.put("endPage", endPage);
		data.put("pageNum", pageNum);
		
		pw.print(data);
	}
}
