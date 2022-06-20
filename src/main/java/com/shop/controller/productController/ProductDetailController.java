package com.shop.controller.productController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.ProductCommand;
import com.shop.dao.CategoryDao;
import com.shop.dao.ProductDao;
import com.shop.dao.ReviewDao;
import com.shop.dto.Review;
import com.shop.dto.Scategory;

@WebServlet("/product/detail")
public class ProductDetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		
		/* 상품정보 조회용 */
		ProductDao pdao = ProductDao.getInstance();
		ProductCommand prd = pdao.selectDesc(pnum);
		
		/* 카테고리명 조회용 */
		int scnum = prd.getScategoryNum();
		CategoryDao cdao = CategoryDao.getInstance();
		Scategory scg = cdao.selectCgName(scnum);
		String stype = scg.getStype();
		String btype = scg.getBtype();
		
		/* 리뷰정보 조회용 */
		ReviewDao rdao = ReviewDao.getInstance();
		int rcnt = rdao.getCnt(pnum); // 리뷰개수
		
		request.setAttribute("pnum", pnum);
		request.setAttribute("prd", prd);
		request.setAttribute("btype", btype);
		request.setAttribute("stype", stype);
		request.setAttribute("rcnt", rcnt);
		request.setAttribute("rdao", rdao); // getStar 사용 위해서... 어떻게 대체할지 고민좀
		
		
//		/* 페이징처리용 */
//		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
//		
//		int startRow = (pageNum-1)*5+1;
//		int endRow = startRow+4;
		
		request.getRequestDispatcher("/WEB-INF/page/product/productDetail.jsp")
		.forward(request, response);
	}
}





















