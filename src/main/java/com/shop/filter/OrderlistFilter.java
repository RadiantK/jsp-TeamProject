package com.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns= {"/orders/orderlistMypage", // 마이페이지 주문리스트
						 "/orders/orderDetailMypage"}) // 마이페이지 주문상세 
public class OrderlistFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("sessionId");
		
		if(email==null) {
			
			email = (String)req.getAttribute(email);

			if (email==null) {
				resp.sendRedirect(req.getContextPath()+"/user/login");
			}
		}
		chain.doFilter(req, resp);
	}
}