package com.shop.controller.cartController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.shop.dao.CartDetailDao;

@SuppressWarnings("serial")
@WebServlet("/user/cart/delete")
public class CartDeleteController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 제거할 장바구니 번호 얻기
		String cnum_ = request.getParameter("cnum");
		int cnum = 0;
		if(cnum_ != null && !cnum_.equals("")) cnum = Integer.parseInt(cnum_);

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("sessionId");
		
		JSONObject jo = new JSONObject();
		
		if(email != null) { //회원이면
			CartDetailDao cartDetailDao = CartDetailDao.getInstance();
			int n = cartDetailDao.delete(cnum);
			
			if(n > 0) {
				jo.put("code", true);
			}else {
				jo.put("code", false);
			}
		}else { // 비회원이면
			// 장바구니 정보 얻어오기
			List<Integer> cart = (List<Integer>) session.getAttribute("cart");
			cart.remove(cnum);
			System.out.println("deleteCart" + cart);
			session.setAttribute("cart", cart);
			jo.put("code", true);
		}
		
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(jo);
	}
}
