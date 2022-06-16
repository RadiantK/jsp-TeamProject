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
@WebServlet("/product/list")
public class ProductListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 상품 출력 */
		// header 메뉴에서 파라미터로 대분류 카테고리 번호 받기
		int bcnum = Integer.parseInt(request.getParameter("bcnum"));
		String tScnum = request.getParameter("scnum");
		int scnum = 0;
		if(tScnum!=null) {
			scnum = Integer.parseInt(tScnum);
		}
		
		// 전달받은 번호에 해당하는 대분류 카테고리 객체 생성
		CategoryDao cdao = CategoryDao.getInstance();
		Bcategory bcg = cdao.selectBcg(bcnum);
		
		// 상품 데이터 출력용 dao 객체 생성
		ProductDao pdao = ProductDao.getInstance();
		
		// 대분류 카테고리 객체명으로 소분류 카테고리 리스트 생성
		List<Scategory> scgList = cdao.selectScg(bcg.getBtype());
		
		// 대분류,소분류 전체카운트
		int bcgCnt = pdao.getCountBcg(bcnum);
		int scgCnt = pdao.getCountScg(scnum);
		
		/* 페이징 처리 */
		String spageNum = request.getParameter("pageNum"); // 페이지번호
		String sort = request.getParameter("sort"); // 정렬기준
		
		int pageNum = 1;
		if(spageNum!=null) {
			pageNum = Integer.parseInt(spageNum); // 첫페이지 1로 초기화
		}
		
		int startRow = (pageNum-1)*8+1; // 첫번째 글번호(한페이지에 상품 1~8개씩 출력)
		int endRow = startRow+7; // 마지막 글번호
		
		int pageNumBox = 10; // 하단에 페이지 10개씩 나열
		int count = 0; // 등록된 전체글 개수
		if(scnum == 0) {
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
		// 소분류 카테고리 파라미터가 0이면
		if(scnum == 0) {
			pList = pdao.selectBcg(bcnum, startRow, endRow, sort);
		}
		// 소분류 카테고리 파라미터가 1이상이면
		else {
			pList = pdao.selectScg(scnum, startRow, endRow, sort);
		}
		
		
		/* 응답 */
		request.setAttribute("bcnum", bcnum);
		request.setAttribute("scnum", scnum);
		
		request.setAttribute("bcg", bcg);
		System.out.println("대분류데이터 : " + bcg);
		
		request.setAttribute("bcgCnt", bcgCnt);
		System.out.println("대분류카운트 : " + bcgCnt);
		
		request.setAttribute("scgList", scgList);
		System.out.println("소분류데이터 : " + scgList);
		
		request.setAttribute("pdao", pdao);
		System.out.println("pdao : " + pdao);
		
		request.setAttribute("pList", pList);
		System.out.println("상품리스트 : " + pList);
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPageNum);
		request.setAttribute("endPage", endPageNum);
		request.setAttribute("pageNumBox", pageNumBox);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("sort", sort);
		
		
		request.getRequestDispatcher("/WEB-INF/page/product/productList.jsp")
		.forward(request, response);
		
		// http://localhost:8085/shop.com/product/list?bcnum=1&scnum=0
	}
}
