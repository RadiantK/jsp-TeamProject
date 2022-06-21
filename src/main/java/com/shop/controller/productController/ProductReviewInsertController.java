package com.shop.controller.productController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.dao.MemberDao;
import com.shop.dao.ReviewDao;
import com.shop.dto.Member;
import com.shop.dto.Review;

@WebServlet("/product/detail/review/insert")
public class ProductReviewInsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션에 저장된 로그인 회원정보로 member dto 불러오기
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("sessionId");
		MemberDao mdao = MemberDao.getInstance();
		Member member = mdao.selectOne(email);
		String mnum = member.getMemberNum(); // 회원번호
		String nickname = member.getNickName(); // 회원닉네임
		
		int pnum = Integer.parseInt(request.getParameter("pnum")); // 상품번호
//		String image = request.getParameter("image");
		String image = "";
		String content = request.getParameter("content"); // 리뷰내용
		int score = Integer.parseInt(request.getParameter("score")); // 별점
		
		ReviewDao rdao = ReviewDao.getInstance();
		int n = rdao.insert(new Review(0, mnum, pnum, nickname, image, content, score, null));

		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		JSONObject json = new JSONObject();
		
		if(n>0) {
			json.put("code", "success");
		}else {
			json.put("code", "fail");
		}
		pw.print(json);
		
//		/* 이미지 업로드 처리 -> X */
//		String saveDir = "${cp}/upload";
//		MultipartRequest mr = new MultipartRequest(request, saveDir, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
		
	}
}










