package com.shop.controller.ordersController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.dao.OrderDetailDao;
import com.shop.dao.OrdersDao;
import com.shop.dto.OrderDetail;
import com.shop.dto.Orders;

@WebServlet("/orders/orderlistMypage")
public class OrderlistMypageController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		String email = req.getParameter("email");
		String orderPwd = "0";
		
		if(email!=null && email!="") { // request에 id가 있으면(비회원인 경우)
			orderPwd = req.getParameter("pwd"); // 주문비밀번호 가져오기 
			
		} else { // request에 id가 없으면 (회원인 경우) 세션에서 아이디 가져오기
			HttpSession session = req.getSession();
			email = (String)session.getAttribute("sessionId");
		}
		System.out.println(email + ", " + orderPwd);

		String getPageNum = req.getParameter("pageNum");
		String getState = req.getParameter("orderState");
		System.out.println(getState);
		
		int pageNum = 1;
		if(getPageNum!=null && getPageNum!="") pageNum = Integer.parseInt("getPageNum");
		int endRow = pageNum*5;
		int startRow = endRow-4;
		
		OrdersDao dao = OrdersDao.getInstance();
		OrderDetailDao oddao = OrderDetailDao.getInstance();
		ArrayList<Orders> orderList = null;
		ArrayList<OrderDetail> orderDetail = null;
		
		int cnt = 0;
		
		if(getState!=null && getState!="") { // 주문상태 검색인 경우 
			orderList = dao.searchList(startRow,endRow,email,orderPwd,getState);
			cnt = dao.getSearchCnt(email,orderPwd,getState);
			orderDetail = oddao.searchList(startRow, endRow, email, orderPwd, getState);

		} else { // 검색이 아닌 경우
			orderList = dao.selectList(startRow, endRow, email, orderPwd);
			cnt = dao.getCnt(email, orderPwd);
			orderDetail = oddao.selectList(startRow, endRow, email, orderPwd);
			
		}
		
		int pageCnt = (int)Math.ceil(cnt/5.0);
		int startPage = ((pageNum-1)/5*5) + 1;
		int endPage = startPage + 4;
		if(endPage > pageCnt) { endPage = pageCnt; }

		// 주문상태별 주문내역 카운트
		int paid = dao.getSearchCnt(email, orderPwd,"결제완료");
		int prepare = dao.getSearchCnt(email, orderPwd, "배송준비");
		int	inTransit = dao.getSearchCnt(email, orderPwd, "배송중");
		int delivered = dao.getSearchCnt(email, orderPwd, "배송완료");
		int cancle = dao.getSearchCnt(email, orderPwd, "주문취소");
		
		req.setAttribute("orderList", orderList);
		req.setAttribute("orderDetail", orderDetail);
		req.setAttribute("pageCnt", pageCnt);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("paid", paid);
		req.setAttribute("prepare", prepare);
		req.setAttribute("inTransit", inTransit);
		req.setAttribute("delivered", delivered );
		req.setAttribute("cancle", cancle);
		req.getRequestDispatcher("/WEB-INF/page/orders/orderList_mypage.jsp").forward(req, resp);

	}
}
