package com.shop.controller.ordersController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.OrdersDao;

@WebServlet("/orders/orderCancle")
public class OrderCancleController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int orderNum = Integer.parseInt(req.getParameter("orderNum"));
		
		OrdersDao dao = OrdersDao.getInstance();
		int n = dao.orderCancle(orderNum);
		String msg = null;
		
		
		if(n<0) {
			msg = "주문이 취소되었습니다.";
				
		} else {
			msg = "주문취소를 실패했습니다. 다시 시도해주세요.";
		}
		System.out.println(msg);
		
		req.setAttribute("orderNum", orderNum);
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/orders/orderdetailMypage").forward(req, resp);
	
	}
}
