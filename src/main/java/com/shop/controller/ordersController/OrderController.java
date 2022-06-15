package com.shop.controller.ordersController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.dao.MemberDao;
import com.shop.dto.Member;
import com.shop.dto.OrderParam;

@WebServlet("/orders/order")
public class OrderController extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 제품종류 개수
		// 장바구니에서 주문할 때는 제품종류 개수 파라미터로 전달받고 
		// 상세페이지에서 바로 주문하면 전달 없이 무조건 한개  
		int itemCnt = 1;
		String itemCount = req.getParameter("itemCnt");
		if (itemCount!=null) itemCnt = Integer.parseInt(itemCount); 
		System.out.println(itemCnt);
		
		int totalCnt = 0; // 전체 구매수량
		int total = 0;

		// 제품이미지, 제품명, 구매수량, 금액 파라미터로 전달 받기 
		String itemImg = req.getContextPath()+"/images/orderTest.png";
		String itemName = "두닷 콰트로 에어데스크 1000";
		int itemPiece = 1;
			totalCnt += itemPiece;
		int itemPrice = 10000;
		int itemCal = itemPiece*itemPrice; // 제품별 합계
			total += itemCal;
			
		
		// 세션아이디 있으면 회원번호, 이름, 전화번호 정보 가져오기 
		String mnum = "";
		String mname = "";
		String phone = "";
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("sessionId");

		if(id!=null) {
			MemberDao dao = MemberDao.getInstance();
			Member vo = dao.selectOne(id);			
			mnum = vo.getMemberNum();
			mname = vo.getName();
			phone = vo.getPhone();
		}
		
		OrderParam orderParam = new OrderParam(itemCnt,itemImg,itemName,itemPiece,itemPrice,itemCal,totalCnt,total,mnum,mname,phone);
		
		resp.setContentType("text/plain;charset=utf-8");
		req.setAttribute("orderParam",orderParam);
		req.getRequestDispatcher("/WEB-INF/page/orders/order.jsp").forward(req, resp);

	}
}
