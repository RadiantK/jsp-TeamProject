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

// 일반회원이 관리자 페이지로 접근시 메인으로 돌려보냄
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("sessionId");
		
		
		if(email == null ) {
			resp.sendRedirect(req.getContextPath()+"/user/login");
		}else if(email != null && email.equals("admin")) {
			chain.doFilter(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath()+"/main");
		}
	}
}
