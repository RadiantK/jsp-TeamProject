package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.Bcategory;
import com.shop.dto.Product;
import com.shop.util.DBPool;

public class ProductDao {
	
	private static ProductDao productDao = null;
	
	private ProductDao() {}
	
	public static ProductDao getInstance() {
		if(productDao == null) {
			productDao = new ProductDao();
		}
		return productDao;
	}
	
	/* 전체상품 조회 */
	public List<Product> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			String sql = "select p.*, b.bcategory_num "
					+ "from product p "
					+ "join scategory s on p.category_num = s.scategory_num "
					+ "join bcategory b on s.btype = b.btype";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int productNum = rs.getInt("product_num");
				int scategoryNum = rs.getInt("category_num");
				String code = rs.getString("code");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				int bcategoryNum = rs.getInt("bcategory_num");
				
				Product product = new Product(productNum, scategoryNum, code, pname, price, discount, cnt, regdate, image, bcategoryNum);
				list.add(product);
			}
			return list;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 소분류별 상품 조회 */
	public List<Product> selectScg(int scategoryNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			String sql = "select p.*, b.bcategory_num "
					+ "from product p "
					+ "join scategory s on p.category_num = s.scategory_num "
					+ "join bcategory b on s.btype = b.btype "
					+ "and category_num = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scategoryNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNum = rs.getInt("product_num");
				String code = rs.getString("code");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				int bcategoryNum = rs.getInt("bcategory_num");
				
				Product product = new Product(productNum, scategoryNum, code, pname, price, discount, cnt, regdate, image, bcategoryNum);
				list.add(product);
			}
			return list;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 대분류별 상품 조회 */
	public List<Product> selectBcg(int bcategoryNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			String sql = "select p.*, b.bcategory_num "
					+ "from product p "
					+ "join scategory s on p.category_num = s.scategory_num "
					+ "join bcategory b on s.btype = b.btype "
					+ "and b.bcategory_num = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bcategoryNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNum = rs.getInt("product_num");
				int scategoryNum = rs.getInt("category_num");
				String code = rs.getString("code");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				
				Product product = new Product(productNum, scategoryNum, code, pname, price, discount, cnt, regdate, image, bcategoryNum);
				list.add(product);
			}
			return list;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 전체상품 카운트 */
	public int getCountAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select NVL(count(product_num),0) as cnt from product";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			return cnt;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 대분류별 전체상품 카운트 */
	public int getCountBcg(int bcategoryNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select NVL(count(product_num),0) as cnt from "
					+ "( "
					+ "    select p.*, b.bcategory_num "
					+ "    from product p "
					+ "    join scategory s on p.category_num = s.scategory_num "
					+ "    join bcategory b on s.btype = b.btype "
					+ "    and b.bcategory_num = ? "
					+ ")";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bcategoryNum);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			return cnt;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	/* 소분류별 전체상품 카운트 */
	public int getCountScg(int scategoryNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select NVL(count(product_num),0) as cnt from "
					+ "( "
					+ "    select p.*, b.bcategory_num "
					+ "    from product p "
					+ "    join scategory s on p.category_num = s.scategory_num "
					+ "    join bcategory b on s.btype = b.btype "
					+ "    and category_num = ? "
					+ ")";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scategoryNum);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			return cnt;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
}
