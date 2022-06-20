package com.shop.controller.productController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;	
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/product/qty_change")
public class ProductQtyChangeController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String qtyValue = request.getParameter("qty");
		String priceValue = request.getParameter("price");

		int qty = Integer.parseInt(qtyValue);
		int price = Integer.parseInt(priceValue);
		String tPrice = String.valueOf(qty * price);
		
		JSONObject json = new JSONObject();
		json.put("tPrice",tPrice);
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);

	}
}
