package com.shop.controller.ordersController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.OrderDetailDao;
import com.shop.dao.OrdersDao;
import com.shop.dto.Orders;

@WebServlet("/admin/orderlistAdmin")
public class OrderlistAdminController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String getPageNum = req.getParameter("pageNum");
		String col = req.getParameter("col");
		String keyword = req.getParameter("keyword");
		
		int pageNum = 1;
		if(getPageNum!=null && getPageNum!="") pageNum = Integer.parseInt(getPageNum);
		int endRow = pageNum*10;
		int startRow = endRow-9;
		
		OrdersDao dao = OrdersDao.getInstance();
		OrderDetailDao oddao = OrderDetailDao.getInstance();
		ArrayList<Orders> orderList = null;
		
		int cnt = 0;
		
		if(keyword!=null && keyword!="") { // 검색인 경우 
			orderList = dao.adminSearch(startRow,endRow,col,keyword);
			cnt = dao.adminSearchCnt(col,keyword);

		} else { // 검색이 아닌 경우
			orderList = dao.adminList(startRow, endRow);
			cnt = dao.adminCnt();
			
		}
		
		int pageCnt = (int)Math.ceil(cnt/10.0);
		int startPage = ((pageNum-1)/10*10) + 1;
		int endPage = startPage + 9;
		if(endPage > pageCnt) { endPage = pageCnt; }
		
		req.setAttribute("orderList", orderList);
		req.setAttribute("pageCnt", pageCnt);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("/WEB-INF/page/orders/orderList_admin.jsp").forward(req, resp);

	}
}
