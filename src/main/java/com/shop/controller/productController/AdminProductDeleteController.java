package com.shop.controller.productController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;

@WebServlet("/admin/product/delete")
public class AdminProductDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제할 상품번호 받아오기
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		System.out.println("pnum:"+pnum);
		
		ProductDao pdao = ProductDao.getInstance();
		int n = pdao.delete(pnum);
		System.out.println("삭제:"+n);

		request.getRequestDispatcher("/admin/product/list")
		.forward(request, response);
	}
}
