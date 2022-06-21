package com.shop.controller.ordersController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.OrdersDao;

@WebServlet("/admin/orderCancleAdmin")
public class OrderCancleAdminController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int orderNum = Integer.parseInt(req.getParameter("orderNum"));
		
		OrdersDao dao = OrdersDao.getInstance();
		int n = dao.orderCancle(orderNum);
		
		if(n<0) {
			req.setAttribute("msg", "주문취소에 실패하였습니다. 다시 시도해주세요. ");
			req.getRequestDispatcher("/WEB-INF/page/admin/orderDetail_admin.jsp").forward(req, resp);

		} else {			
			req.setAttribute("orderNum", orderNum);
			req.getRequestDispatcher("/admin/orderdetailAdmin").forward(req, resp);
		}
	}
}
