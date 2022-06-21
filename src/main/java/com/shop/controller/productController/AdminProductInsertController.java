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

@WebServlet("/admin/product/insert")
public class AdminProductInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryDao cdao = CategoryDao.getInstance();
		
		// 대분류 카테고리 리스트 전달
		List<Bcategory> bcgList = cdao.selectBcgAll();
		request.setAttribute("bcgList", bcgList);
		
		
		
		request.getRequestDispatcher("/WEB-INF/page/admin/adminProductInsert.jsp")
		.forward(request, response);
	}
}
