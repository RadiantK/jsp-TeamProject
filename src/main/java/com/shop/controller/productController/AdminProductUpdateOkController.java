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

@WebServlet("/admin/product/updateok")
public class AdminProductUpdateOkController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletContext().getRealPath("/upload/product");
		MultipartRequest mr = new MultipartRequest(request, path, 1024*1024*5, "UTF-8", new DefaultFileRenamePolicy());
	
		// DB저장을 위한 데이터 받아오기
		int pnum = Integer.parseInt(mr.getParameter("pnum"));
		System.out.println("pnum:"+pnum);
		
		String thumImgName = mr.getFilesystemName("thumImgFile");
		System.out.println("thumImgName:"+thumImgName);
		
		String descImgName = mr.getFilesystemName("descImg");
		System.out.println("descImgName:"+descImgName);
		
		String bigCg = mr.getParameter("bigCg");
		System.out.println("bigCg:"+bigCg);
		String smallCg = mr.getParameter("smallCg");
		System.out.println("smallCg:"+smallCg);
		// smallCg->snum
		CategoryDao cdao = CategoryDao.getInstance();
		int snum = cdao.selectCgNum(smallCg).getScategoryNum();
		
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
		
		ProductCommand prdComm = new ProductCommand(pnum, snum, prdName, shotDesc, price, priceOff, qty, null, thumImgName, 0, descImgName, bigCg, smallCg);
		ProductDao pdao = ProductDao.getInstance();
		
		if( (thumImgName == null || thumImgName.equals("null"))
				&& (descImgName == null || descImgName.equals("null")) ) {
			// 썸네일 변경 X, 상세이미지 변경 X면
			pdao.updateNoImg(pnum, prdComm);
			
		}else if( (thumImgName != null)
				&& (descImgName == null || descImgName.equals("null")) ) {
			// 썸네일 변경 O, 상세이미지 변경 X면
			pdao.updateNoDescImg(pnum, prdComm);
			
		}else if( (thumImgName == null || thumImgName.equals("null"))
				&& (descImgName != null) ) {
			// 썸네일 변경 X, 상세이미지 변경 O면
			pdao.updateNoThum(pnum, prdComm);
			
		}else if( (thumImgName != null)
				&& (descImgName != null) ) {
			// 썸네일변경 O, 상세이미지 변경 O면
			pdao.update(pnum, prdComm);
			
		}
		
		request.getRequestDispatcher("/admin/product/list")
		.forward(request, response);
	}
}
