package com.shop.controller.ordersController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.shop.dao.OrdersDao;

@WebServlet("/orders/cancle")
public class OrderCancleController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int orderNum = Integer.parseInt(req.getParameter("orderNum"));
		String orderState = req.getParameter("orderState");
		
		JSONObject json = new JSONObject();
		
		if( orderState.equals("배송중") || orderState.equals("배송완료") ) {
			json.put("code", false);
			json.put("msg", "이미 배송 중이거나 배송 완료된 주문입니다. ");
			
		} else if (orderState.equals("주문취소")) {
			json.put("code", false);
			json.put("msg", "이미 취소된 주문입니다. ");
			
		} else {
		
			OrdersDao dao = OrdersDao.getInstance();
			int n = dao.orderCancle(orderNum);
		
			if(n<0){
				json.put("code", false);
				json.put("msg", "주문 취소에 실패하였습니다. 다시 시도해주세요. ");
			}
			json.put("code", true);
			json.put("msg", "주문이 취소되었습니다.");
		}
		
		resp.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
