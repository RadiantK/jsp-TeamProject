package com.shop.controller.ordersController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.DeliveryDao;
import com.shop.dao.OrderDetailDao;
import com.shop.dao.OrdersDao;
import com.shop.dao.PaymentDao;
import com.shop.dto.Delivery;
import com.shop.dto.OrderDetail;
import com.shop.dto.Orders;
import com.shop.dto.Payment;

@WebServlet("/admin/orderdetailAdmin")
public class OrderdetailAdminController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		int orderNum = Integer.parseInt(req.getParameter("orderNum"));
		
		// 주문서 정보 가져오기 
		OrdersDao odao = OrdersDao.getInstance();
		Orders orders = odao.selectDetail(orderNum);
		
		// 주문상세 
		OrderDetailDao oddao = OrderDetailDao.getInstance();
		ArrayList<OrderDetail> details = new ArrayList<>();
		details = oddao.selecDetail(orderNum);
				
		// 배송
		DeliveryDao ddao = DeliveryDao.getInstance();
		Delivery delivery = ddao.selectDetail(orderNum);
		
		// 결제 
		PaymentDao pdao = PaymentDao.getInstance();
		Payment payment = pdao.selectDetail(orderNum);
		
		req.setAttribute("orders", orders);
		req.setAttribute("details", details);
		req.setAttribute("delivery", delivery);
		req.setAttribute("payment", payment);
		req.getRequestDispatcher("/WEB-INF/page/orders/orderDetail_admin.jsp").forward(req, resp);

	}
}
