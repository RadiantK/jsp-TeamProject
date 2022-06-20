package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shop.dto.FAQ;
import com.shop.dto.Notice;
import com.shop.util.DBPool;

public class FAQDao {
	private static FAQDao FAQDao = null;

	private FAQDao() {}

	public static FAQDao getInstance() {
		if(FAQDao == null) {
			FAQDao = new FAQDao();
		}
		return FAQDao;
	}
	public ArrayList<FAQ> FAQList(int startRow,int endRow, String field, String keyword){ // 공지 목록 불러오는 DAO + 페이징 처리
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql=null;
			if(field ==null || field.equals("")) {
				sql="select * from "
						+ "( "
						+ "	select aa.*,rownum rnum from "
						+ "	( "
						+ "	select * from faq "
						+ "	order by faq_num desc "
						+ "	)aa "
						+ ")where rnum between ? and ?";
			}else {
				sql="select * from"
						+ "( "
						+ "select aa.*,rownum rnum from"
						+ "( "
						+ "select * from faq where "+ field +" like '%"+ keyword + "%'"
						+ " order by faq_num desc"
						+ ")"
						+ "aa "
						+ ")where rnum between ? and ?";
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<FAQ> list=new ArrayList<FAQ>();
			while(rs.next()) {
				int faqNum=rs.getInt("faq_Num");
				String memberNum=rs.getString("member_num");
				String nickname=rs.getString("nickname");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Date regdate=rs.getDate("regdate");
				FAQ dto=new FAQ(faqNum, memberNum, nickname, title, content, regdate);
				list.add(dto);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	public int getMaxNum() { // 페이징 처리를 위한 공지번호 최대값 구해오기.
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql="select NVL(max(faq_num),0) as maxnum from faq";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxnum=rs.getInt(1);
			return maxnum;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	public int getCount(String field, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select NVL(count(faq_num),0) as cnt from faq";
		if(field !=null && !field.equals("")) {
			sql += " where " + field + " like '%"+ keyword + "%' ";
		}
		try {
			con=DBPool.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt(1); 
			return cnt;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);	
		}
	}
	public int insertFAQ(FAQ vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="insert into faq values(seq_faq.nextval,1,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getNickname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException s) {
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	public FAQ FAQDetail(int faqNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
			try {
				con=DBPool.getConnection();
				String sql="select * from faq where faq_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, faqNum);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					faqNum=rs.getInt("faq_Num");
					String memberNum=rs.getString("member_num");
					String nickname=rs.getString("nickname");
					String title=rs.getString("title");
					String content=rs.getString("content");
					Date regdate=rs.getDate("regdate");
					FAQ vo=new FAQ(faqNum, memberNum, nickname, title, content, regdate);
					return vo;
					}
				return null;
			}catch(SQLException se) {
				se.printStackTrace();
				return null;
			}finally {
				DBPool.close(con, pstmt, rs);
			}
	}
	public int deleteFAQ(int faqNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="delete from faq where faq_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, faqNum);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	public int updateFAQ(FAQ dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="update faq set title=?,content=? where faq_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getFaqNum());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
}
