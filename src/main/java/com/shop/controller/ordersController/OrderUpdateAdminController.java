package com.shop.controller.ordersController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.DeliveryDao;
import com.shop.dao.OrdersDao;
import com.shop.dto.Delivery;
import com.shop.dto.Orders;

@WebServlet("/admin/orderUpdateAdmin")
public class OrderUpdateAdminController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		int orderNum = Integer.parseInt(req.getParameter("orderNum"));
		
		// 주문서테이블 수정 
		String orderState = req.getParameter("orderState");
		String change = req.getParameter("orderStateChange");
		if(!orderState.equals(change)) orderState = change;
		
		String orderName = req.getParameter("orderName");
		String orderPhone = req.getParameter("orderPhone");
		String email = req.getParameter("email");
		
		Orders orders = new Orders(orderNum,null,email,orderName,orderPhone,0,null,orderState,null);
		OrdersDao odao = OrdersDao.getInstance();
		int n = odao.orderUpdate(orders);
		
		if(n<0) {
			req.setAttribute("msg", "주문정보 수정에 실패하였습니다. 다시 시도해주세요. ");
			req.getRequestDispatcher("/WEB-INF/page/orders/orderDetail_admin.jsp").forward(req, resp);
		}
		
		// 배송테이블 수정
		String delName = req.getParameter("receiveName");
		String delPhone = req.getParameter("receivePhone");
		String delMsg = req.getParameter("shippingMsg");
		String address = "";
		
		String zonecode = req.getParameter("kakaoZonecode");
		if(zonecode!=null && !zonecode.equals("")) {
			address += "("+zonecode+")";

			String kakaoAddr = req.getParameter("kakaoAddr");
			if(kakaoAddr!=null && !kakaoAddr.equals("")) address += kakaoAddr;
		
			String detailAddr = req.getParameter("detailAddr");
			if(detailAddr!=null && !detailAddr.equals("")) address += " " + detailAddr;
		
		} else {
			address = req.getParameter("orgAddr");
		}
		
		DeliveryDao ddao = DeliveryDao.getInstance();
		Delivery del = new Delivery(0,orderNum,delName,delPhone,address,delMsg,null);
		int nn = ddao.deliveryUpdate(del);
		
		if(nn<0) {
			req.setAttribute("msg", "배송정보 수정에 실패하였습니다. 다시 시도해주세요. ");
			req.getRequestDispatcher("/WEB-INF/page/orders/orderDetail_admin.jsp").forward(req, resp);
			
		} else { // 수정성공하면 관리자 주문내역리스트로 이동
			resp.sendRedirect(req.getContextPath()+"/admin/orderlistAdmin");
		}

	}
}
