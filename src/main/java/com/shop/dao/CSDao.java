package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shop.dto.Notice;
import com.shop.util.DBPool;

public class CSDao {
	private static CSDao CSDao = null;
	
	private CSDao() {}
	
	public static CSDao getInstance() {
		if(CSDao == null) {
			CSDao = new CSDao();
		}
		return CSDao;
	}
	public int getMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql="select NVL(max(notice_num),0) as maxnum from notice";
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
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select NVL(count(notice_num),0) as cnt from notice"; // NVL (x,y) : x의 값이 null이면 0으로 바꿔라!
		try {
			con=DBPool.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
//			int cnt=rs.getInt("cnt"); ( Alias를 줘서 얻어오기 )
			int cnt=rs.getInt(1); // 1번째 컬럼의 값 얻어오기. ( 함수는 컬럼명이 없기 때문에)
			return cnt;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);	
			}
	  }
	public int insertNotice(Notice dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="insert into notice values(seq_notice.nextval,1,?,?,?,sysdate,0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getNickname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			int n=pstmt.executeUpdate();
			return n;
			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}

	public ArrayList<Notice> NoticeList(int startRow,int endRow){
	String sql="select * from "
			+ "( "
			+ "	select aa.*,rownum rnum from "
			+ "	( "
			+ "	select * from notice "
			+ "	order by notice_num desc "
			+ "	)aa "
			+ ")where rnum between ? and ?";
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		con=DBPool.getConnection();
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		rs=pstmt.executeQuery();
		ArrayList<Notice> list=new ArrayList<Notice>();
		while(rs.next()) {
			int noticeNum=rs.getInt("notice_Num");
			String memberNum=rs.getString("member_num");
			String nickname=rs.getString("nickname");
			String title=rs.getString("title");
			String content=rs.getString("content");
			Date regdate=rs.getDate("regdate");
			int hit=rs.getInt("hit");
			Notice dto=new Notice(noticeNum,memberNum, nickname, title, content, regdate, hit);
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
	public Notice NoticeDetail(int noticeNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql="select * from notice where notice_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, noticeNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				noticeNum=rs.getInt("notice_Num");
				String memberNum=rs.getString("member_Num");
				String nickname=rs.getString("nickname");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Date regdate=rs.getDate("regdate");
				int hit=rs.getInt("hit");
				Notice dto=new Notice(noticeNum, memberNum, nickname, title, content, regdate, hit);
				return dto;
				
			}
			return null;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	public int NoticeUpdate(Notice dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="update notice set title=?,content=? where notice_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNoticeNum());
			int n=pstmt.executeUpdate();
			return n;
		}catch (SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
}
	
	


