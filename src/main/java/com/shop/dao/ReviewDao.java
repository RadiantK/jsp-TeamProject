package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.Review;
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
	
	/* 상품별 리뷰리스트 조회 (페이징) */
	public List<Review> selectReview(int productNum, int startRow, int endRow){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> list = new ArrayList<Review>();
		try {
			String sql = "select * from "
					+ "( "
					+ "select aa.*,rownum rnum from "
					+ "( "
					+ "select * from review "
					+ "where product_num = ? "
					+ "order by regdate desc "
					+ ") aa "
					+ ")where rnum>=? and rnum<=?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int reviewNum = rs.getInt("review_num");
				String memberNum = rs.getString("member_num");
				String nickname = rs.getString("nickname");
				String image = rs.getString("image");
				String content = rs.getString("content");
				int score = rs.getInt("score");
				Date regdate = rs.getDate("regdate");
				
				Review review = new Review(reviewNum, memberNum, productNum, nickname, image, content, score, regdate);
				list.add(review);
			}
			return list;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* score로 별 문자열 리턴 */
	public String getStar(int score) {
		switch (score) {
		case 5:
			return "★★★★★";
		case 4:
			return "★★★★☆";
		case 3:
			return "★★★☆☆";
		case 2:
			return "★★☆☆☆";
		case 1:
			return "★☆☆☆☆";
		default:
			return null;
		}
	}
	
	/* 리뷰등록 */
	public int insert(Review vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO review "
						+ "VALUES(SEQ_REVIEW.nextval, ?, ?, ?, ?, ?, ?, sysdate)";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberNum());
			pstmt.setInt(2, vo.getProductNum());
			pstmt.setString(3, vo.getNickname());
			pstmt.setString(4, vo.getImage());
			pstmt.setString(5, vo.getContent());
			pstmt.setInt(6, vo.getScore());
			int n = pstmt.executeUpdate();
			return n;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
}
