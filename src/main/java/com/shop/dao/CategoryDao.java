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
		return categoryDao;
	}
	
	/* 대분류 카테고리 조회 */
	public List<Bcategory> selectBcg() {
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
	
	/* 파라미터로 받은 대분류 카테고리넘버에 해당하는 소분류 카테고리 전체조회 */
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
}
