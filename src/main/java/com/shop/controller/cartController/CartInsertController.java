package com.shop.controller.cartController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.shop.config.ServiceConfig;
import com.shop.exception.CartException;
import com.shop.service.MemberCartAddService;

@SuppressWarnings("serial")
@WebServlet("/user/cart")
public class CartInsertController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 상품번호
		String productNum_ = request.getParameter("pnum");
		int productNum = 0;
		if(productNum_ != null && !productNum_.equals("")) 
			productNum = Integer.parseInt(productNum_);
		
		// 세션에서 이메일 얻어오기
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("sessionId");
		
		// 서비스 객체 생성
		ServiceConfig config = ServiceConfig.getInstance();
		MemberCartAddService service = config.getMemberCartAddService();
		
		JSONObject jo = new JSONObject();
		if(email != null) { // 회원일 때 장바구니
			try {
				// 장바구니에 상품추가
				int n = service.addCart(productNum, email);
				if(n > 0) {
					jo.put("code", true);
				}else {
					jo.put("code", false);
				}
			}catch(CartException e) {
				jo.put("code", "error");
			}
		}else { // 비회원일 때 장바구니
			List<Integer> cart = (List<Integer>) session.getAttribute("cart");
			//List<ProductCommand> cart = new ArrayList<>();
			if(cart == null) {
				List<Integer> list = new ArrayList<>();
//				NonMemberCartCommand command = new NonMemberCartCommand(
//						p.getProductNum(), p.getPname(), p.getImage(),
//						p.getPrice(), p.getCnt());
				list.add(productNum);
				session.setAttribute("cart", list);
				cart = new ArrayList<>(list);
			}else {
				cart.add(productNum);
			}
			if(cart != null) {
				jo.put("code", true);
			}
			
			System.out.println(cart);
		}
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(jo);
	}
}
