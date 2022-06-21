package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.CartDetail;
import com.shop.util.DBPool;

public class CartDetailDao {

	private static CartDetailDao cartDetailDao = null;
	
	private CartDetailDao() {}
	
	public static CartDetailDao getInstance() {
		if(cartDetailDao == null) {
			cartDetailDao = new CartDetailDao();
		}
		return cartDetailDao;
	}
	
	// 회원별 장바구니상세목록 얻어오기
	public List<CartDetail> selectList(int reqCartNum){
//		String sql = "SELECT * FROM ( "
//				+ "SELECT ROWNUM RN, C.* FROM ( "
//				+ "SELECT * FROM cartdetail WHERE cart_num = ?) C ) "
//				+ "WHERE RN BETWEEN ? AND ?";
		String sql = "SELECT * FROM cartdetail WHERE cart_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CartDetail> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqCartNum);
//			pstmt.setInt(2, 1 + (page -1) * 5);
//			pstmt.setInt(3, page * 5);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int cartDetailNum = rs.getInt("cartdetail_num");
				int cartNum = rs.getInt("cart_num");
				int productNum = rs.getInt("product_num");
				String pname = rs.getString("pname");
				int cnt = rs.getInt("cnt");
				int price = rs.getInt("price");
				
				CartDetail cartDetail = new CartDetail
						(cartDetailNum, cartNum, productNum, pname, cnt, price);
				list.add(cartDetail);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);;
		}
	}
	
	public int getCount(int reqCartNum) {
		String sql = "SELECT NVL(COUNT(cartdetail_num), 0) cnt "
				+ "FROM cartdetail WHERE cart_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqCartNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
				
			}
			return cnt;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);;
		}
	}
	
	// 장바구니 추가
	public int insert(CartDetail cart) {
		String sql = "INSERT INTO cartdetail VALUES("
				+ "seq_cartdetail.nextval, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			DBPool.setAutoCommitFalse(con);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.getCartNum());
			pstmt.setInt(2, cart.getProductNum());
			pstmt.setString(3, cart.getPname());
			pstmt.setInt(4, cart.getCnt());
			pstmt.setInt(5, cart.getPrice());
			
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
	
	// 장바구니의 아이템 제거
	public int delete(int cdNum) {
		String sql = "DELETE FROM cartdetail WHERE cartdetail_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cdNum);
			
			int n = pstmt.executeUpdate();
			
			return n;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(pstmt);
		}
	}
}
