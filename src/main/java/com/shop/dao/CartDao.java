package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shop.dto.Cart;
import com.shop.util.DBPool;

public class CartDao {

	private static CartDao cartDao = null;
	
	private CartDao() {}
	
	public static CartDao getInstance() {
		if(cartDao == null) {
			cartDao = new CartDao();
		}
		return cartDao;
	}
	
	// 회원번호와 일치하는 장바구니 출력
	public Cart selectOne(String reqMemberNum){
		String sql = "SELECT * FROM cart WHERE member_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reqMemberNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int cartNum = rs.getInt("cart_num");
				int memberNum = rs.getInt("member_num");
				
				Cart cart = new Cart(cartNum, memberNum);
				return cart;
			}
			return null;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);;
		}
	}
	
	// 장바구니 추가
	public int insert(String memberNum) {
		String sql = "INSERT INTO cart VALUES(seq_cart.nextval, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			DBPool.setAutoCommitFalse(con);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNum);
			
			int n = pstmt.executeUpdate();
			if(n > 0) DBPool.commit(con);
			return n;
		}catch(SQLException e) {
			e.printStackTrace();
			DBPool.rollback(con);
			return -1;
		}finally {
			DBPool.setAutoCommitTrue(con);
			DBPool.close(pstmt);
		}
	}
}
