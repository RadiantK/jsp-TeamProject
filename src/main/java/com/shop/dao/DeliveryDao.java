package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shop.dto.Delivery;
import com.shop.util.DBPool;

public class DeliveryDao {
	
	private static DeliveryDao deliveryDao = null;
	
	private DeliveryDao() {}
	
	public static DeliveryDao getInstance() {
		if (deliveryDao == null) {
			deliveryDao = new DeliveryDao();
		}
		return deliveryDao;
	}
	
	// 상세페이지 조회
	public Delivery selectDetail(int orderNum) {
		
		String sql = "select * from delivery where order_num=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int delNum = rs.getInt("delivery_num");
				String delName = rs.getString("delname");
				String delPhone = rs.getString("delphone");
				String address = rs.getString("address");
				String delMsg = rs.getString("delmsg");
				Date deliDate = rs.getDate("delidate");
				
				return new Delivery(delNum,orderNum,delName,delPhone,address,delMsg,deliDate);

			}
			return null;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}

	// 신규주문 배송정보 추가 
	public int newDelivery(Delivery delivery) {
		String sql = "insert into delivery VALUES(seq_delivery.nextval,?,?,?,?,?,sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, delivery.getOrderNum());
			pstmt.setString(2, delivery.getDelName());
			pstmt.setString(3, delivery.getDelPhone());
			pstmt.setString(4, delivery.getAddress());
			pstmt.setString(5, delivery.getDelMsg());
			int n = pstmt.executeUpdate();
			
			return n;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
