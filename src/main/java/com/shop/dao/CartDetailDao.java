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
		String sql = "SELECT * FROM cartdetail WHERE cart_num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CartDetail> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqCartNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int cartDetailNum = rs.getInt("cartdetail_nuk");
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
	
	// 장바구니 추가
	public int insert(Connection con, CartDetail cart) {
		String sql = "INSERT INTO cartdetail VALUES("
				+ "seq_cartdatail.nextval, seq_cart.currval, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.getProductNum());
			pstmt.setString(2, cart.getPname());
			pstmt.setInt(3, cart.getCnt());
			pstmt.setInt(4, cart.getPrice());
			
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
