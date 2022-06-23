package com.shop.controller.productController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.command.ProductCommand;
import com.shop.dao.CategoryDao;
import com.shop.dao.ProductDao;
import com.shop.dto.Bcategory;
import com.shop.dto.Scategory;

@WebServlet("/admin/product/insert")
public class AdminProductInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryDao cdao = CategoryDao.getInstance();
		request.setAttribute("cdao", cdao);
		
		// 대분류 카테고리 리스트
		List<Bcategory> bcgList = cdao.selectBcgAll();
		request.setAttribute("bcgList", bcgList);
		
		// 소분류 카테고리 객체
		List<Scategory> scgList = new ArrayList<Scategory>();
		request.setAttribute("scgList", scgList);
		
		request.getRequestDispatcher("/WEB-INF/page/admin/adminProductInsert.jsp")
		.forward(request, response);
	}
}
