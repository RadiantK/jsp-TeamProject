package com.shop.controller.productController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.ProductCommand;
import com.shop.dao.CategoryDao;
import com.shop.dao.ProductDao;
import com.shop.dto.Bcategory;
import com.shop.dto.Scategory;

@SuppressWarnings("serial")
@WebServlet("/admin/product/update")
public class AdminProductUpdateController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 수정할 상품번호 받아오기
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		
		ProductDao pdao = ProductDao.getInstance();
		ProductCommand prdComm = pdao.selectDescPlus(pnum);
		
		String thumImgFile = prdComm.getImage();
		String bigCg = prdComm.getBtype();
		String smallCg = prdComm.getStype();
		String prdName = prdComm.getPname();
		String shotDesc = prdComm.getPdesc();
		int price = prdComm.getPrice();
		int priceOff = prdComm.getDiscount();
		int qty = prdComm.getCnt();
		String descImg = prdComm.getDescImg();
		
		request.setAttribute("pnum", pnum);
		request.setAttribute("thumImgFile", thumImgFile);
		request.setAttribute("bigCg", bigCg);
		request.setAttribute("smallCg", smallCg);
		request.setAttribute("prdName", prdName);
		request.setAttribute("shotDesc", shotDesc);
		request.setAttribute("price", price);
		request.setAttribute("priceOff", priceOff);
		request.setAttribute("qty", qty);
		request.setAttribute("descImg", descImg);
		
		CategoryDao cdao = CategoryDao.getInstance();
		request.setAttribute("cdao", cdao);
		
//		// 대분류 카테고리 리스트
//		List<Bcategory> bcgList = cdao.selectBcgAll();
//		request.setAttribute("bcgList", bcgList);
//		
//		// 소분류 카테고리 객체
//		List<Scategory> scgList = new ArrayList<Scategory>();
//		request.setAttribute("scgList", scgList);
		
		request.getRequestDispatcher("/WEB-INF/page/admin/adminProductUpdate.jsp")
		.forward(request, response);
	}
}
