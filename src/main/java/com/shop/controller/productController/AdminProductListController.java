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
import com.shop.dto.Product;
import com.shop.dto.Scategory;

@SuppressWarnings("serial")
@WebServlet("/admin/product/list")
public class AdminProductListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 카테고리 출력 */
		CategoryDao cdao = CategoryDao.getInstance();
		
		// 대분류 카테고리 리스트 전달
		List<Bcategory> bcgList = cdao.selectBcgAll();
		request.setAttribute("bcgList", bcgList);
		
		// 대분류 미선택시 bcnum 0으로
		String tBcnum = request.getParameter("bcnum");
		int bcnum = 0;
		String btype = "";
		if(tBcnum!=null) {
			bcnum = Integer.parseInt(tBcnum);
			if(bcnum!=0) {
				btype = cdao.selectBcg(bcnum).getBtype();
			}
		}
		request.setAttribute("bcnum", bcnum);
		request.setAttribute("btype", btype);
		
		
		// 소분류 미선택시 scnum 0으로
		String tScnum = request.getParameter("scnum");
		int scnum = 0;
		if(tScnum!=null) scnum = Integer.parseInt(tScnum);
		request.setAttribute("scnum", scnum);
		
		// 상품 데이터 출력용 dao 객체 생성
		ProductDao pdao = ProductDao.getInstance();
		request.setAttribute("pdao", pdao);
		
		// 전체 상품 카운트
		int totCnt = pdao.getCountAll();
		request.setAttribute("totCnt", totCnt);
		
		// 대분류별 전체 상품 카운트
		int bcgCnt = 0; 
		if(bcnum>0) {
			bcgCnt = pdao.getCountBcg(bcnum);
		}
		request.setAttribute("bcgCnt", bcgCnt);
		
		// 소분류 상품 카운트
		int scgCnt = 0;
		if(scnum>0) {
			scgCnt = pdao.getCountScg(scnum);
		}
		request.setAttribute("scgCnt", scgCnt);
		
		// bcnum 0 이상이면 소분류 카테고리 리스트 전달
		List<Scategory> scgList = null;
		if(bcnum>0) {
			scgList = cdao.selectScg(cdao.selectBcg(bcnum).getBtype());
		}
		request.setAttribute("scgList", scgList);
		
		
		/* 페이징 처리 */
		String spageNum = request.getParameter("pageNum"); // 페이지번호
		String sort = request.getParameter("sort"); // 정렬기준
		
		int pageNum = 1;
		if(spageNum!=null) {
			pageNum = Integer.parseInt(spageNum); // 첫페이지 1로 초기화
		}
		
		int startRow = (pageNum-1)*10+1; // 첫번째 글번호(한페이지에 상품 1~10개씩 출력)
		int endRow = startRow+9; // 마지막 글번호
		
		int pageNumBox = 10; // 하단에 페이지 10개씩 나열
		int count = 0; // 등록된 전체상품 개수
		if(bcnum == 0 && scnum == 0) {
			count = totCnt;
		}else if(scnum == 0) {
			count = bcgCnt; // 소분류 미선택시 대분류카운트 리턴
		}else {
			count = scgCnt; // 소분류 선택시 소분류카운트 리턴
		}
		int pageCount = count/pageNumBox + ((count%pageNumBox==0)?0:1); // 전체 페이지 개수
		int startPageNum = ((pageNum-1)/pageNumBox*pageNumBox)+1; // 시작페이지 번호
		int endPageNum = startPageNum+pageNumBox-1; // 끝페이지 번호
		if(pageCount<endPageNum) {
			endPageNum = pageCount; // 전체 페이지 개수가 끝페이지 번호보다 적으면 끝페이지 번호를 전체 페이지 개수와 동일하도록 처리
		}
		
		
		/* 상품리스트 리턴 */
		List<Product> pList = null;
		// 대분류 카테고리 파라미터가 0이면
		if(bcnum == 0) {
			pList = pdao.selectAll(startRow, endRow, sort);
		}
		// 소분류 카테고리 파라미터가 0이면
		else if(bcnum > 0 && scnum == 0) {
			pList = pdao.selectBcg(bcnum, startRow, endRow, sort);
		}
		// 소분류 카테고리 파라미터가 1이상이면
		else {
			pList = pdao.selectScg(scnum, startRow, endRow, sort);
		}
		request.setAttribute("pList", pList);
		
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPageNum);
		request.setAttribute("endPage", endPageNum);
		request.setAttribute("pageNumBox", pageNumBox);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("sort", sort);
		
		request.getRequestDispatcher("/WEB-INF/page/admin/adminProductList.jsp")
		.forward(request, response);
	}
}
