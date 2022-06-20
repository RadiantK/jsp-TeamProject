package com.shop.controller.productController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.shop.dao.ReviewDao;
import com.shop.dto.Review;

@WebServlet("/product/detail/review")
public class ProductReviewListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 페이징처리용 */
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		int startRow = (pageNum-1)*5+1;
		int endRow = startRow+4;
		
		ReviewDao rdao = ReviewDao.getInstance();
		List<Review> rList = rdao.selectReview(pnum, startRow, endRow);
		
		int pageCount = (int)Math.ceil(rdao.getCnt(pnum)/5.0);
		int startPage = (pageNum-1)/5*5+1;
		int endPage = startPage+4;
		if(pageCount<endPage) {
			endPage = pageCount;
		}
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		JSONObject data = new JSONObject();
		JSONArray arr = new JSONArray();
		
		for(Review vo:rList) {
			JSONObject ob = new JSONObject();
			ob.put("reviewNum", vo.getReviewNum());
			ob.put("memberNum", vo.getMemberNum());
			ob.put("productNum", vo.getProductNum());
			ob.put("nickname", vo.getNickname());
			ob.put("image", vo.getImage());
			ob.put("content", vo.getContent());
			ob.put("score", rdao.getStar(vo.getScore()));
			ob.put("regdate", vo.getRegdate());
			arr.put(ob);
		}
		
		data.put("list", arr);
		data.put("pageCount", pageCount);
		data.put("startPage", startPage);
		data.put("endPage", endPage);
		data.put("pageNum", pageNum);
		
		pw.print(data);
		// http://localhost:8085/shop.com/product/detail/review?pnum=1&pageNum=1
	}
}
