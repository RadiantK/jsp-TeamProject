package com.shop.controller.productController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.CategoryDao;
import com.shop.dao.ProductDao;
import com.shop.dto.Bcategory;
import com.shop.dto.Scategory;

@SuppressWarnings("serial")
@WebServlet("/product/list")
public class ProductListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// header 메뉴에서 파라미터로 대분류 카테고리 번호 받기
		int bcnum = Integer.parseInt(request.getParameter("bcnum"));
		
		// 전달받은 번호에 해당하는 대분류 카테고리 객체 생성
		CategoryDao cdao = CategoryDao.getInstance();
		Bcategory bcg = cdao.selectBcg(bcnum);
		
		// test
		ProductDao pdao = ProductDao.getInstance();
		
		// 대분류 카테고리 객체명으로 소분류 카테고리 리스트 생성
		List<Scategory> scgList = cdao.selectScg(bcg.getBtype());
		
		// 대분류 카테고리 데이터 + 소분류 카테고리 데이터 응답 -> json으로?
		request.setAttribute("bcg", bcg);
		System.out.println("대분류데이터 : " + bcg);
		
		request.setAttribute("scgList", scgList);
		System.out.println("소분류데이터 : " + scgList);
		
		request.setAttribute("pdao", pdao);
		System.out.println("이미지명 : "+pdao.selectBcg(1).get(0).getImage());
		
		request.getRequestDispatcher("/WEB-INF/page/product/productList.jsp")
		.forward(request, response);
		
		// http://localhost:8085/shop.com/product/list?bcnum=1
	}
}
