package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.shop.dto.Notice;
import com.shop.dto.QNA;
import com.shop.dto.QNAComment;
import com.shop.util.DBPool;

public class QNADao {
	private static QNADao QNADao = null;

	private QNADao() {}

	public static QNADao getInstance() {
		if(QNADao == null) {
			QNADao = new QNADao();
		}
		return QNADao;
	}
	public int getMaxNum() { // 페이징 처리를 위한 공지번호 최대값 구해오기.
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql="select NVL(max(qna_num),0) as maxnum from qna";
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
	public int getCount() { // 페이징 처리를 위한 총 글 갯수 구해오기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select NVL(count(qna_num),0) as cnt from qna";
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
	
	public ArrayList<QNA> QNAList(int startRow,int endRow){ // 전체 1:1문의 목록 불러오는 DAO (관리자, 비회원 전용)
		String sql="select * from "
				+ "( "
				+ "	select aa.*,rownum rnum from "
				+ "	( "
				+ "	select * from qna "
				+ "	order by qna_num desc "
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
			ArrayList<QNA> list=new ArrayList<QNA>();
			while(rs.next()) {
				int qnaNum=rs.getInt("qna_Num");
				String memberNum=rs.getString("member_num");
				String nickname=rs.getString("nickname");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String image=rs.getString("image");
				Date regdate=rs.getDate("regdate");
				String qnastate=rs.getString("qnastate");
				QNA dto=new QNA(qnaNum, memberNum, nickname, title, content, image, regdate, qnastate);
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
	public ArrayList<QNA> memberQNAList(int startRow,int endRow,String nickname){ // 1:1문의 목록 불러오는 DAO + 페이징 처리 - 자신의 글 목록만 보이도록 설정
		String sql="select * from "
				+ "( "
				+ "	select aa.*,rownum rnum from "
				+ "	( "
				+ "	select * from qna where nickname=? or nickname='admin' "
				+ "	order by qna_num desc "
				+ "	)aa "
				+ ")where rnum between ? and ?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			ArrayList<QNA> list=new ArrayList<QNA>();
			while(rs.next()) {
				int qnaNum=rs.getInt("qna_Num");
				String memberNum=rs.getString("member_num");
				nickname=rs.getString("nickname");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String image=rs.getString("image");
				Date regdate=rs.getDate("regdate");
				String qnastate=rs.getString("qnastate");
				QNA dto=new QNA(qnaNum, memberNum, nickname, title, content, image, regdate, qnastate);
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
	
	public QNA QNADetail(int qnaNum) { // 상세내용 보기 + QNA번호로 하나의 전체컬럼 보기(다용도 목적)
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql="select * from qna where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				qnaNum=rs.getInt("qna_num");
				String memberNum=rs.getString("member_Num");
				String nickname=rs.getString("nickname");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String image=rs.getString("image");
				Date regdate=rs.getDate("regdate");
				String qnastate=rs.getString("qnastate");
				QNA dto=new QNA(qnaNum, memberNum, nickname, title, content, image, regdate, qnastate);
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

	public int insertQNA(QNA dto) { // QNA 등록
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="insert into qna values(seq_qna.nextval,?,?,?,?,?,sysdate,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberNum());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getImage());
			pstmt.setString(6, dto.getQnastate());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	
	public int QNAUpdate(QNA dto) { // qna 수정 DAO
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="update qna set title=?,content=?,image=? where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImage());
			pstmt.setInt(4, dto.getQnaNum());
			int n=pstmt.executeUpdate();
			return n;
		}catch (SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	
	public int QNADelete(int qnaNum) { // qna 삭제
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="delete from qna where qna_num=?";
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
	public int isComment(int qnaNum) { // 문의상태 확인을 위한 DAO - 해당 문의번호에 해당하는 답변이 있을경우 count >=1, 없을 경우 count = 0 임을 이용
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBPool.getConnection();
			String sql="select count(*) as count from qnacomment where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int count=rs.getInt("count");
				return count;
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	public int isState(QNA dto) { // 트랜젝션 처리 - 답변 있을 경우 qnastate -> 답변완료로 변경
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="UPDATE QNA SET QNASTATE='답변완료' WHERE QNA_NUM=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getQnaNum());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	public int noState(QNA dto) { // 트랜젝션 처리 - 답변 없을 경우 qnastate -> 답변대기중으로 변경
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBPool.getConnection();
			String sql="UPDATE QNA SET QNASTATE='답변대기중' WHERE QNA_NUM=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getQnaNum());
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
