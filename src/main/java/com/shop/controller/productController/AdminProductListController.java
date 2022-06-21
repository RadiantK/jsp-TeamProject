package com.shop.controller.productController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.CategoryDao;
import com.shop.dto.Bcategory;

@WebServlet("/admin/product/list")
public class AdminProductListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 카테고리 출력 */
		CategoryDao cdao = CategoryDao.getInstance();
		List<Bcategory> bcgList = cdao.selectBcgAll();
		
		request.setAttribute("bcgList", bcgList);
		
		request.getRequestDispatcher("/WEB-INF/page/admin/adminProductList.jsp")
		.forward(request, response);
	}
}
