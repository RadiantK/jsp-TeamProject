package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.Bcategory;
import com.shop.dto.Scategory;
import com.shop.util.DBPool;

public class CategoryDao {

	private static CategoryDao categoryDao = null;
	
	private CategoryDao() {}
	
	public static CategoryDao getInstance() {
		if(categoryDao == null) {
			categoryDao = new CategoryDao();
		}
		return categoryDao;
	}
	
	/* 대분류 카테고리 데이터 전체조회 */
	public List<Bcategory> selectBcgAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Bcategory> list = new ArrayList<Bcategory>();
		try {
			String sql = "select * from bcategory";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bcategoryNum = rs.getInt("bcategory_num");
				String btype = rs.getString("btype");
				
				Bcategory bcategory = new Bcategory(bcategoryNum, btype);
				list.add(bcategory);
			}
			return list;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 대분류 카테고리 번호로 카테고리 객체 리턴 */
	public Bcategory selectBcg(int bcategoryNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from bcategory where bcategory_num = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bcategoryNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String btype = rs.getString("btype");
				
				Bcategory bcategory = new Bcategory(bcategoryNum, btype);
				return bcategory;
			}else {
				return null;
			}
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 파라미터로 받은 대분류 카테고리명에 해당하는 소분류 카테고리 데이터 전체조회 */
	public List<Scategory> selectScg(String btype) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Scategory> list = new ArrayList<Scategory>();
		try {
			String sql = "select * from scategory where btype=?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, btype);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int scategoryNum = rs.getInt("scategory_num");
				String stype = rs.getString("stype");
				
				Scategory scategory = new Scategory(scategoryNum, btype, stype);
				list.add(scategory);
			}
			return list;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 소분류카테고리번호로 카테고리명 조회 */
	public Scategory selectCgName(int snum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scategory scg = null;
		try {
			String sql = "select * from scategory where scategory_num = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, snum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String stype = rs.getString("stype");
				String btype = rs.getString("btype");
				
				scg = new Scategory(snum, btype, stype);
			}
			return scg;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 소분류카테고리명으로 카테고리번호 조회 */
	public Scategory selectCgNum(String stype) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scategory scg = null;
		try {
			String sql = "select * from scategory where stype = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stype);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int snum = rs.getInt("scategory_num");
				String btype = rs.getString("btype");
				
				scg = new Scategory(snum, btype, stype);
			}
			return scg;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
}
