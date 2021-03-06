package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shop.dto.QNA;
import com.shop.dto.QNAComment;
import com.shop.util.DBPool;

public class QNACommentDao {
	private static QNACommentDao QNACommentDao = null;

	private QNACommentDao() {}

	public static QNACommentDao getInstance() {
		if(QNACommentDao == null) {
			QNACommentDao = new QNACommentDao();
		}
		return QNACommentDao;
	}
	public int insertComment(QNAComment dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="insert into qnacomment values(seq_qnacomment.nextval,1,?,?,?,?,sysdate)";			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getQnaNum());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	public ArrayList<QNAComment> CommentList(int qnaNum ){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql="select * from qnacomment where qna_Num=?";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			pstmt.setInt(1, qnaNum);
			ArrayList<QNAComment> list=new ArrayList<QNAComment>();
			while(rs.next()) {
				int qnaCommentNum=rs.getInt("qnaCommentNum");
				String memberNum=rs.getString("memberNum");
				qnaNum=rs.getInt("qnaNum");
				String nickname=rs.getString("nickname");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Date regdate=rs.getDate("regdate");
				QNAComment vo=new QNAComment(qnaCommentNum, memberNum, qnaNum, nickname, title, content, regdate);
				list.add(vo);
			}
			return list;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	public QNAComment QNACommentDetail(int qnaNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql="select * from qnacomment where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int qnaCommentNum=rs.getInt("qnacomment_num");
				String memberNum=rs.getString("member_Num");
				qnaNum=rs.getInt("qna_num");
				String nickname=rs.getString("nickname");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Date regdate=rs.getDate("regdate");
				QNAComment dto=new QNAComment(qnaCommentNum, memberNum, qnaNum, nickname, title, content, regdate);
				return dto;
			}
			return null;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	public int updateQNAComment(QNAComment dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="update qnacomment set title=?,content=? where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getQnaNum());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	public int QNACommentDelete(int qnaNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="delete from qnacomment where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
}
