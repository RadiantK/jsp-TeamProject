package com.shop.controller.ordersController;

import java.io.IOException;

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

@WebServlet("/orders/orderOK")
public class OrderOKController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 신규주문 추가 (주문서 테이블)
		String memberNum = req.getParameter("mnum");
		String email = req.getParameter("email1")+"@"+req.getParameter("email2");
		String name = req.getParameter("orderName");
		String phone = req.getParameter("orderPhone");
		int amount = Integer.parseInt(req.getParameter("total"));
		String orderPwd = req.getParameter("orderPwd");
		
		Orders orders = new Orders(0, memberNum, email, name, phone, amount, orderPwd, null, null);
		OrdersDao oDao = OrdersDao.getInstance();
		int newo = oDao.newOrder(orders);
		System.out.println("order : " + newo);
		
		if(newo<0) {
			req.setAttribute("errorMsg", "fail");
			req.getRequestDispatcher("/WEB-INF/page/orders/orderOK.jsp").forward(req, resp);
		}
	
		// 주문번호 가져오기
		int newOrderNum = oDao.newOrderNum(memberNum,amount);		
		
		// 주문상세 테이블 추가
		int cartCnt = Integer.parseInt(req.getParameter("cartCnt"));
		OrderDetailDao odDao = OrderDetailDao.getInstance();
		int newod = 0;
		
		if (cartCnt>1){ // 장바구니 여러 품목 구매
			String[] itemNum = req.getParameterValues("itemNum");
			String[] itemName = req.getParameterValues("itemName");
			String[] itemPiece = req.getParameterValues("itemPiece");
			
			for(int i=0; i<cartCnt; i++) {
				int pnum = Integer.parseInt("itemNum[i]");
				int piece = Integer.parseInt("itemPiece[i]");
				
				OrderDetail od = new OrderDetail(0,newOrderNum,pnum,itemName[i],piece);
				newod = odDao.newOrderDetail(od);
			}
						
		} else { // 단품 구매
			int pnum = Integer.parseInt(req.getParameter("itemNum"));
			String itemName = req.getParameter("itemName");
			int piece = Integer.parseInt(req.getParameter("itemPiece"));
				
			OrderDetail od = new OrderDetail(0,newOrderNum,pnum,itemName,piece);
			newod = odDao.newOrderDetail(od);
		}
		System.out.println("orderdetail: " + newod);
		
		if(newod<0) {
			req.setAttribute("errorMsg", "fail");
			req.getRequestDispatcher("/WEB-INF/page/orders/orderOK.jsp").forward(req, resp);
		}
		
		// 배송테이블 추가 
		String delName = req.getParameter("receiveName");
		String delPhone = req.getParameter("receivePhone");
		String address = "";
		
		String zonecode = req.getParameter("kakaoZonecode");
		if(zonecode!=null && !zonecode.equals("")) address += "("+zonecode+")";
		
		String kakaoAddr = req.getParameter("kakaoAddr");
		if(kakaoAddr!=null && !kakaoAddr.equals("")) address += kakaoAddr;
		
		String detailAddr = req.getParameter("detailAddr");
		if(detailAddr!=null && !detailAddr.equals("")) address += " " + detailAddr;
		
		String delMsg = req.getParameter("shippingMsg");
		
		Delivery del = new Delivery(0,newOrderNum,delName,delPhone,address,delMsg,null);
		DeliveryDao dDao = DeliveryDao.getInstance();
		int newdel = dDao.newDelivery(del);
		
		System.out.println("delivery: " + newdel);
		
		if(newdel<0) {
			req.setAttribute("errorMsg", "fail");
			req.getRequestDispatcher("/WEB-INF/page/orders/orderOK.jsp").forward(req, resp);
		}
		
		// 결제테이블 추가 
		Payment pay = new Payment(0,newOrderNum,"card",amount,null);
		PaymentDao pDao = PaymentDao.getInstance();
		int newpay = pDao.newPayment(pay);
		System.out.println("payment: " + newpay);
		
		if(newpay<0) {
			req.setAttribute("errorMsg", "fail");
			req.getRequestDispatcher("/WEB-INF/page/orders/orderOK.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/WEB-INF/page/orders/orderOK.jsp").forward(req, resp);
		}
		
	}
}
