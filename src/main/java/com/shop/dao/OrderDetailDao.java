package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shop.dto.OrderDetail;
import com.shop.util.DBPool;

public class OrderDetailDao {
	
	private static OrderDetailDao orderDetailDao = null;

	private OrderDetailDao() {}
	
	public static OrderDetailDao getInstance() {
		if (orderDetailDao == null ) {
			orderDetailDao = new OrderDetailDao();
		}
		return orderDetailDao;
	}
	
	// 신규주문 제품 추가 
	public int newOrderDetail(OrderDetail orderDetail) {
		String sql = "insert into orderdetail values(seq_orderdetail.nextval,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderDetail.getOrderNum());
			pstmt.setInt(2, orderDetail.getPnum());
			pstmt.setString(3, orderDetail.getPname());
			pstmt.setInt(4, orderDetail.getPiece());
			int n = pstmt.executeUpdate();
			
			return n;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
