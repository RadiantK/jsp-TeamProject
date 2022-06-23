package com.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.ChartCommand;
import com.shop.command.SalesCommand;
import com.shop.dao.TempOrdersDao;

@SuppressWarnings("serial")
@WebServlet("/admin/sales/list")
public class AdminSalesListController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String page_ = request.getParameter("p");
		int page = 1;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		String year_ = request.getParameter("year");
		String year = "all";
		if(year_ != null && !year_.equals("")) year = year_;
		
		String month_ = request.getParameter("month");
		String month = "";
		if(month_ != null && !month_.equals("") && month_.length() == 1) {
			month = "0" + month_;
		}else if(month_ != null && !month_.equals("")){
			month = month_;
		}
		System.out.println(year);
		System.out.println(month);
		
		String date1 = "";
		String date2 = "";
		if(year.equals("all")) {
			date1 = "20170101";
			date2 = "20230101";
		}else if(!year.equals("all") && month.equals("")) {
			date1 = year + "0101";
			date2 = (Integer.parseInt(year)+1) + "0101";
		}else {
			date1 = year + month + "01";
			if(!(month.equals("09")||month.equals("10")||month.equals("11")||month.equals("12"))) {
				date2 = year + ("0" + (Integer.parseInt(month)+1)) + "01";
			}else {
				date2 = year + ((Integer.parseInt(month)+1)) + "01";
			}
			if(month.equals("12")) {
				date2 = (Integer.parseInt(year)+1) + "0101";
			}
		}
		System.out.println(date1);
		System.out.println(date2);
		
		TempOrdersDao ordersDao = TempOrdersDao.getInstance();
		List<SalesCommand> list = ordersDao.selectList(page, date1, date2);
		int count = ordersDao.getCount(date1, date2);
		
		int pageCount = (int)(Math.ceil(count / 10.0));
		int startPage = ((page-1) / 5 * 5) +1;
		int endPage = startPage + 4;
		if(endPage > pageCount) endPage = pageCount;
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		List<ChartCommand> yearChart = ordersDao.yearList(year);
		List<ChartCommand> monthChart = ordersDao.monthList(date1, date2);
		int cancelPayment = ordersDao.cancelPayment(date1, date2);
		int completePayment = ordersDao.completedPayment(date1, date2);
		int totalSales = ordersDao.totalSales(date1, date2);
		
		request.setAttribute("yearChart", yearChart);
		request.setAttribute("monthChart", monthChart);
		request.setAttribute("cancelPayment", cancelPayment);
		request.setAttribute("completePayment", completePayment);
		request.setAttribute("totalSales", totalSales);
		
		request.getRequestDispatcher("/WEB-INF/page/admin/adminSalesList.jsp")
		.forward(request, response);
	}
}
