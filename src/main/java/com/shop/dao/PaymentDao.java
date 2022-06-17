package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shop.dto.Payment;
import com.shop.util.DBPool;

public class PaymentDao {

	private static PaymentDao paymentDao = null;
	
	private PaymentDao() {}
	
	public static PaymentDao getInstance() {
		if (paymentDao == null) {
			paymentDao = new PaymentDao();
		}
		return paymentDao;
	}
	
	// 신규주문 결제정보 추가 
	public int newPayment(Payment payment) {
		String sql = "insert into payment VALUES(seq_payment.nextval,?,?,?,sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, payment.getOrderNum());
			pstmt.setString(2, payment.getMeans());
			pstmt.setInt(3, payment.getAmount());
			int n = pstmt.executeUpdate();
			
			return n;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	
}
