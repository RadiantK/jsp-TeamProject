package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.command.LoginCommand;
import com.shop.dto.Member;
import com.shop.util.DBPool;

public class MemberDao {

	private static MemberDao memberDao = null;
	
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		if(memberDao == null) {
			memberDao = new MemberDao();
		}
		return memberDao;
	}
	
	// 회원목록
	public List<Member> selectList() {
		String sql = "SELECT * FROM member";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memberNum = rs.getString("member_num");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String nickName = rs.getString("nickName");
				String phone = rs.getString("phone");
				String gender = rs.getString("gender");
				Date regDate = rs.getDate("regDate");
				String orderPwd = rs.getString("orderPwd");
				int available = rs.getInt("available");
				int role = rs.getInt("role");
				
				Member member = new Member(
						memberNum, email, password, name, nickName, phone,
						gender, regDate, orderPwd, available, role);
				list.add(member);
			}
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 하나의 회원 목록
	public Member selectOne(String reqEmail) {
		String sql = "SELECT * FROM member WHERE email = ? AND available = 1";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reqEmail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memberNum = rs.getString("member_num");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String nickName = rs.getString("nickName");
				String phone = rs.getString("phone");
				String gender = rs.getString("gender");
				Date regDate = rs.getDate("regDate");
				String orderPwd = rs.getString("orderPwd");
				int available = rs.getInt("available");
				int role = rs.getInt("role");
				
				member = new Member(
						memberNum, email, password, name, nickName, phone,
						gender, regDate, orderPwd, available, role);
			}
			return member;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 회원 로그인
	public LoginCommand memberLogin(String reqEmail, String reqPwd) {
		String sql = "SELECT * FROM member "
				+ "WHERE email = ? AND password = ? AND available = 1";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reqEmail);
			pstmt.setString(2, reqPwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString("email");
				String pwd = rs.getString("password");
				int role = rs.getInt("role");
				
				LoginCommand login = new LoginCommand(email, pwd, role);
				return login;
			}
			
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}

}
