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
		return productDao;
	}
	
	/* 테스트 */
	public Product selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String sql = "select * from product";
			con = DBPool.getConnection();
			System.out.println("1");
			pstmt = con.prepareStatement(sql);
			System.out.println("2");
			rs = pstmt.executeQuery();
			System.out.println("3");
			
			while(rs.next()) {
				System.out.println("4");
				int productNum = rs.getInt("product_num");
				int scategoryNum = rs.getInt("category_num");
				String code = rs.getString("code");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				
				Product product = new Product(productNum, scategoryNum, code, pname, price, 0, 0, null, null, 0);
				return product;
			}
			return null;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
//	/* 전체상품 셀렉트 */
//	public ArrayList<Product> selectAll() {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ArrayList<Product> list = new ArrayList<Product>();
//		try {
//			String sql = "select p.*, b.bcategory_num\r\n"
//					+ "from product p join scategory s\r\n"
//					+ "on p.category_num = s.scategory_num\r\n"
//					+ "join bcategory b on s.btype = b.btype";
//			con = DBPool.getConnection();
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				int productNum = rs.getInt("product_num");
//				int scategoryNum = rs.getInt("category_num");
//				String code = rs.getString("code");
//				String pname = rs.getString("pname");
//				int price = rs.getInt("price");
//				int discount = rs.getInt("discount");
//				int cnt = rs.getInt("cnt");
//				Date regdate = rs.getDate("regdate");
//				String image = rs.getString("image");
//				int bcategoryNum = rs.getInt("bcategory_num");
//				
//				Product product = new Product(productNum, scategoryNum, code, pname, price, discount, cnt, regdate, image, bcategoryNum);
//				list.add(product);
//			}
//			return list;
//			
//		}catch (SQLException s) {
//			s.printStackTrace();
//			return null;
//		}finally {
//			DBPool.close(con, pstmt, rs);
//		}
//	}
	
	/* 소분류별 */
	public List<Product> selectScg(int scategoryNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			String sql = "select p.*, b.bcategory_num\r\n"
					+ "from product p join scategory s\r\n"
					+ "on p.category_num = s.scategory_num\r\n"
					+ "join bcategory b on s.btype = b.btype\r\n"
					+ "where category_num = ?";
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
	
	/* 대분류별 */
	public List<Product> selectBcg(int bcategoryNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			String sql = "select p.*, b.bcategory_num\r\n"
					+ "from product p join scategory s\r\n"
					+ "on p.category_num = s.scategory_num\r\n"
					+ "join bcategory b on s.btype = b.btype\r\n"
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
}
