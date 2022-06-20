package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shop.util.DBPool;

public class ReviewDao {
	
	private static ReviewDao reviewDao = null;
	
	private ReviewDao() {}
	
	public static ReviewDao getInstance() {
		if(reviewDao == null) {
			reviewDao = new ReviewDao();
		}
		return reviewDao;
	}
	
	/* 상품번호로 상품별 리뷰개수 조회 */
	public int getCnt(int productNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			String sql = "select p.product_num, NVL(count(review_num),0) as cnt "
					+ "from product p "
					+ "join review r "
					+ "on r.product_num = p.product_num "
					+ "and r.product_num = ? "
					+ "group by p.product_num";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			return cnt;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
}
