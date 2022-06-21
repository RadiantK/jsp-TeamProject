package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.dao.QNADao;
import com.shop.dto.QNA;

@WebServlet("/board/QNA/Update")
public class QNAUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		QNADao dao=QNADao.getInstance();
		QNA vo=dao.QNADetail(qnaNum);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/WEB-INF/page/board/QNA_updateForm.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnaNum=Integer.parseInt(req.getParameter("qnaNum"));
		ServletContext context = this.getServletContext();
		String saveDir=context.getRealPath("/upload"); // 경로 담는 변수 
		//1.파일 업로드
		MultipartRequest mr=new MultipartRequest(
				req, // request객체
				saveDir, // 저장할 디렉토리
				1024*1024*5, // 최대 업로드 가능한 크기 ( 바이트 단위 )
				"utf-8", // 인코딩 방식
				new DefaultFileRenamePolicy() // 동일한 파일명이 존재할 시 이를 처리할 객체
				);	
		String memberNum=mr.getParameter("memberNum");
//		System.out.println("memberNum:"+ memberNum);
		String nickname=mr.getParameter("nickname");
		String title=mr.getParameter("title");
		String content=mr.getParameter("content");
		String image=mr.getFilesystemName("image"); // 저장된 파일명
		
		// 업로드된 파일 정보를 qna테이블에 저장
		QNA vo=new QNA(qnaNum, memberNum, nickname, title, content, image, null,null);
		QNADao dao=QNADao.getInstance();
		int n=dao.QNAUpdate(vo);
		if(n>0){
			resp.sendRedirect(req.getContextPath()+"/board/QNA/List");
		}else{
			
		}
	}

}
