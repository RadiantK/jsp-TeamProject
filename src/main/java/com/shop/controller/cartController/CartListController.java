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

import org.json.JSONArray;
import org.json.JSONObject;

import com.shop.dao.CartDao;
import com.shop.dao.CartDetailDao;
import com.shop.dao.MemberDao;
import com.shop.dao.ProductDao;
import com.shop.dto.Cart;
import com.shop.dto.CartDetail;
import com.shop.dto.Member;
import com.shop.dto.Product;

@SuppressWarnings("serial")
@WebServlet("/user/cart/list")
public class CartListController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 페이지
//		String page_ = request.getParameter("p");
//		int page = 1;
//		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("sessionId");
		
		MemberDao memberDao = MemberDao.getInstance();
		ProductDao productDao = ProductDao.getInstance();
		CartDao cartDao = CartDao.getInstance();
		CartDetailDao cartDetailDao = CartDetailDao.getInstance();

		// JSON 배열 생성
		JSONArray ja = new JSONArray();
		if(email != null) {
			// 회원번호 얻기
			Member member = memberDao.selectOne(email);
			// 장바구니 번호 얻기
			Cart cart = cartDao.selectOne(member.getMemberNum());
			if(cart != null) {
				int cartNum = cart.getCartNum();
				
				// 회원의 장바구니 상세정보를 얻어옴
				List<CartDetail> list = cartDetailDao.selectList(cartNum);
				// 회원의 장바구니의 아이템 갯수
//				int count = cartDetailDao.getCount(cartNum);
//				int pageCount = (int)Math.ceil(count / 5.0);
//				int startPage = ((page - 1) / 5 * 5) + 1;
//				int endPage = startPage + 4;
//				if(endPage > pageCount) endPage = pageCount;
				
				for(CartDetail c : list) {
					JSONObject jo = new JSONObject();
					// 상품정보 얻어오기
					Product p = productDao.selectOne(c.getProductNum());
					jo.put("cartDetailNum", c.getCartDetailNum());
					jo.put("cartNum", c.getCartNum());
					jo.put("productNum", c.getProductNum());
					jo.put("pname", c.getPname());
					jo.put("cnt", c.getCnt());
					jo.put("price", c.getPrice());
					jo.put("img", p.getImage());
					
					ja.put(jo);
				}
			}
			
		}else {
			// 리스트의 목록에서 카트목록을 출력
			List<Integer> cart = (List<Integer>)session.getAttribute("cart");
			if(cart != null) {
				// 상품번호가 담겨있음
				int n = 0; // 인덱스값
				for(int c : cart) {
					// 리스트에 담긴 상품번호를 통해 상품 정보를 얻어옴
					Product p = productDao.selectOne(c);
					JSONObject jo = new JSONObject();
					jo.put("index", n++);
					jo.put("productNum", p.getProductNum());
					jo.put("pname", p.getPname());
					jo.put("cnt", p.getCnt());
					int realPrice = (int) (p.getPrice() - (p.getPrice() * (p.getDiscount() / 100.0)));
					jo.put("price", realPrice);
					jo.put("img", p.getImage());
					
					ja.put(jo);
				}
			}else {
			}
		}
		
		JSONObject jo = new JSONObject();
		jo.put("cart", ja);
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(jo);
		
	}
}
