package com.shop.controller.boardController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/Notice/Listmove")
public class NoticeListmoveController extends HttpServlet{
	// NoticeListController에서 저장한 값을 가지고 JSP 파일로 이동하는 컨트롤러
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/page/board/Notice_List.jsp").forward(req, resp);
	}

}
