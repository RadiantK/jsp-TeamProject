package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	// 상세페이지 조회
	public Payment selectDetail(int orderNum) {
		
		String sql = "select * from payment where order_num=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int payNum = rs.getInt("payment_num");
				String means = rs.getString("means");
				int amount = rs.getInt("amount");
				Date regdate = rs.getDate("regdate");
				
				return new Payment(payNum,orderNum,means,amount,regdate);

			}
			return null;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
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
