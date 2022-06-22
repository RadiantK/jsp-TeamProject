package com.shop.controller.cartController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/user/cart/itemCal")
public class CartCalController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String getPiece = req.getParameter("piece");
		String getPrice = req.getParameter("price");
		
		int piece = Integer.parseInt(getPiece);
		int price = Integer.parseInt(getPrice);
		int itemCal = piece * price;
		
		JSONObject json = new JSONObject();
		json.put("itemCal", itemCal);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		
	}
}
