package com.shop.controller.ordersController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.OrderCommand;
import com.shop.dao.MemberDao;
import com.shop.dao.ProductDao;
import com.shop.dto.Member;
import com.shop.dto.Product;

@WebServlet("/orders/order")
public class OrderController extends HttpServlet{


	@SuppressWarnings("unused")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
										throws ServletException, IOException {
		
		// 장바구니에서 주문할 때는 품목수 파라미터로 전달받고 
		// 상세페이지에서 바로 주문하면 한개  
		String[] cart = req.getParameterValues("cart");
		int cartCnt = 1;
		int itemNum = 0; // 제품번호 
		int piece = 0; // 구매수량 
		int totalCnt = 0; // 전체 구매수량
		int total = 0; // 전체 구매금액 
		
		if(cart==null) { // 바로 구매할 때 
			itemNum = Integer.parseInt(req.getParameter("pnum"));
			piece = Integer.parseInt(req.getParameter("piece"));
		} else {
			String[] pnums = req.getParameterValues("pnum");
			String[] pieces = req.getParameterValues("piece"); 
			cartCnt = cart.length;
		}
		
		System.out.println(cartCnt);
		
		// 제품 정보 가져오기 
		ArrayList<OrderCommand> orderList = new ArrayList<OrderCommand>();
			
		for(int i=0; i<cartCnt; i++) {
			if(cart!=null) {
				itemNum = Integer.parseInt("pnums[i]");
				piece = Integer.parseInt("pieces[i]");
			}
				
			ProductDao dao = ProductDao.getInstance();
			Product vo = dao.selectOne(itemNum);
			
			String itemImg = vo.getImage();
			String itemName = vo.getPname();
			int price = vo.getPrice();
			int discount = vo.getDiscount();
			int discountPrice = price*(100-discount)/100;
			totalCnt += piece;
			int itemTotal = discountPrice*piece;
			total += itemTotal;
				
			OrderCommand oc = 
						new OrderCommand(itemNum,itemImg,itemName,price,discount,discountPrice,piece,itemTotal);
			orderList.add(oc);
		}			
		
		// 세션아이디 있으면 회원번호, 이름, 전화번호 정보 가져오기 
		String mnum = "";
		String mname = "";
		String phone = "";
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("sessionId");

		if(id!=null) {
			MemberDao dao = MemberDao.getInstance();
			Member member = dao.selectOne(id);			
			mnum = member.getMemberNum();
			mname = member.getName();
			phone = member.getPhone();
		}
	
		resp.setContentType("text/plain;charset=utf-8");
		req.setAttribute("orderList", orderList);
		req.setAttribute("cartCnt", cartCnt);
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("total", total);
		req.setAttribute("mnum", mnum);
		req.setAttribute("mname", mname);
		req.setAttribute("phone", phone);
		req.getRequestDispatcher("/WEB-INF/page/orders/order.jsp").forward(req, resp);

	}
}
