package com.shop.controller.userController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;
import com.shop.dto.Product;

@SuppressWarnings("serial")
@WebServlet("/search/product/list")
public class SearchProductController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 페이지 유효성 검사
		String page_ = request.getParameter("p");
		int page = 1;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		// 검색어
		String word_ = request.getParameter("word");
		String word = "";
		if(word_ != null && !word_.equals("")) word = word_;
		
		String sort_ = request.getParameter("sort");
		String sort = "pname";
		if(sort_ != null && !sort_.equals("")) sort = sort_;
		
		ProductDao productDao = ProductDao.getInstance();
		List<Product> list = productDao.productList(page, word, sort);
		int count = productDao.productCount(word);
		
		int pageCount = (int)Math.ceil(count / 8.0);
		int startPage = ((page -1) / 8 * 8) + 1;
		int endPage = startPage + 7;
		if(endPage > pageCount) endPage = pageCount;
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.getRequestDispatcher("/WEB-INF/page/product/searchProduct.jsp")
		.forward(request, response);
		
	}
}
