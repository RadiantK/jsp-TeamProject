package com.shop.controller.productController;

import java.io.IOException;

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

@WebServlet("/admin/product/insertok")
public class AdminProductInsertOkController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 이미지 업로드용 mr객체 생성(폴더 나눌 방법 X...)
		String path = request.getServletContext().getRealPath("/upload/product");
		MultipartRequest mr = new MultipartRequest(request, path, 1024*1024*5, "UTF-8", new DefaultFileRenamePolicy());
		
		// DB저장을 위한 데이터 받아오기
		String thumImgName = mr.getFilesystemName("thumImgFile");
		System.out.println("thumImgName:"+thumImgName);
		String descImgName = mr.getFilesystemName("descImg");
		System.out.println("descImgName:"+descImgName);
		String bigCg = mr.getParameter("bigCg");
		System.out.println("bigCg:"+bigCg);
		String smallCg = mr.getParameter("smallCg");
		System.out.println("smallCg:"+smallCg);
		String prdName = mr.getParameter("prdName");
		System.out.println("prdName:"+prdName);
		String shotDesc = mr.getParameter("shotDesc");
		System.out.println("shotDesc:"+shotDesc);
		int price = Integer.parseInt(mr.getParameter("price"));
		System.out.println("price:"+price);
		int priceOff = Integer.parseInt(mr.getParameter("priceOff"));
		System.out.println("priceOff:"+priceOff);
		int qty = Integer.parseInt(mr.getParameter("qty"));
		System.out.println("qty:"+qty);
		
		// s카테고리번호 조회
		CategoryDao cdao = CategoryDao.getInstance();
		int snum = cdao.selectCgNum(smallCg).getScategoryNum();
		
		ProductCommand prdComm = new ProductCommand(0, snum, prdName, shotDesc, price, priceOff, qty, null, thumImgName, 0, descImgName);
		ProductDao pdao = ProductDao.getInstance();
		pdao.insert(prdComm);
		
		
		request.getRequestDispatcher("/admin/product/list")
		.forward(request, response);
	}
}
