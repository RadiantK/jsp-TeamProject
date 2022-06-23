package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.command.ProductCommand;
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
			
			while(rs.next()) {
				int productNum = rs.getInt("product_num");
				int scategoryNum = rs.getInt("category_num");
				String pname = rs.getString("pname");
				String pdesc = rs.getString("pdesc");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				int bcategoryNum = rs.getInt("bcategory_num");
				
				Product product = new Product(productNum, scategoryNum, pname, pdesc, price, discount, cnt, regdate, image, bcategoryNum);
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
	
	/* 전체상품 조회 (페이징+소트) */
	public List<Product> selectAll(int startRow, int endRow, String sort) {
		String sql = null;
		if(sort==null || sort.equals("") || sort.equals("date")) {
			// sort 초기값 date(신제품순)
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        order by regdate desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("lowPrice")) {
			// 낮은가격순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        order by price asc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("highPrice")) {
			// 높은가격순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        order by price desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("review")){
			// 리뷰순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.product_num, p.category_num, p.pname, p.pdesc, p.price, p.discount, p.cnt, p.regdate, p.image, b.bcategory_num, NVL(count(review_num),0) as rcnt "
					+ "        from product p "
					+ "        left outer join review r on r.product_num = p.product_num "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        group by p.product_num, p.category_num, p.pname, p.pdesc, p.price, p.discount, p.cnt, p.regdate, p.image, b.bcategory_num "
					+ "        order by rcnt desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNum = rs.getInt("product_num");
				int scategoryNum = rs.getInt("category_num");
				String pname = rs.getString("pname");
				String pdesc = rs.getString("pdesc");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				int bcategoryNum = rs.getInt("bcategory_num");
				
				Product product = new Product(productNum, scategoryNum, pname, pdesc, price, discount, cnt, regdate, image, bcategoryNum);
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
	public List<Product> selectScg(int scategoryNum, int startRow, int endRow, String sort) {
		String sql = null;
		if(sort==null || sort.equals("") || sort.equals("date")) {
			// sort 초기값 date(신제품순)
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        and category_num = ? "
					+ "        order by regdate desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("lowPrice")) {
			// 낮은가격순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        and category_num = ? "
					+ "        order by price asc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("highPrice")) {
			// 높은가격순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        and category_num = ? "
					+ "        order by price desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("review")){
			// 리뷰순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.product_num, p.category_num, p.pname, p.pdesc, p.price, p.discount, p.cnt, p.regdate, p.image, b.bcategory_num, NVL(count(review_num),0) as rcnt "
					+ "        from product p "
					+ "        left outer join review r on r.product_num = p.product_num "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        and category_num = ? "
					+ "        group by p.product_num, p.category_num, p.pname, p.pdesc, p.price, p.discount, p.cnt, p.regdate, p.image, b.bcategory_num "
					+ "        order by rcnt desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scategoryNum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNum = rs.getInt("product_num");
				String pname = rs.getString("pname");
				String pdesc = rs.getString("pdesc");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				int bcategoryNum = rs.getInt("bcategory_num");
				
				Product product = new Product(productNum, scategoryNum, pname, pdesc, price, discount, cnt, regdate, image, bcategoryNum);
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
	public List<Product> selectBcg(int bcategoryNum, int startRow, int endRow, String sort) {
		String sql = null;
		if(sort==null || sort.equals("") || sort.equals("date")) {
			// sort 초기값 date(신제품순)
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        and b.bcategory_num = ? "
					+ "        order by regdate desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("lowPrice")) {
			// 낮은가격순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        and b.bcategory_num = ? "
					+ "        order by price asc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("highPrice")) {
			// 높은가격순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.*, b.bcategory_num "
					+ "        from product p "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        and b.bcategory_num = ? "
					+ "        order by price desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}else if(sort.equals("review")){
			// 리뷰순
			sql = "select * from "
					+ "( "
					+ "    select aa.*,rownum rnum from "
					+ "    ( "
					+ "        select p.product_num, p.category_num, p.pname, p.pdesc, p.price, p.discount, p.cnt, p.regdate, p.image, b.bcategory_num, NVL(count(review_num),0) as rcnt "
					+ "        from product p "
					+ "        left outer join review r on r.product_num = p.product_num "
					+ "        join scategory s on p.category_num = s.scategory_num "
					+ "        join bcategory b on s.btype = b.btype "
					+ "        and b.bcategory_num = ? "
					+ "        group by p.product_num, p.category_num, p.pname, p.pdesc, p.price, p.discount, p.cnt, p.regdate, p.image, b.bcategory_num "
					+ "        order by rcnt desc "
					+ "    ) aa "
					+ ")where rnum>=? and rnum<=?";
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bcategoryNum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNum = rs.getInt("product_num");
				int scategoryNum = rs.getInt("category_num");
				String pname = rs.getString("pname");
				String pdesc = rs.getString("pdesc");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				
				Product product = new Product(productNum, scategoryNum, pname, pdesc, price, discount, cnt, regdate, image, bcategoryNum);
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
	
	// 개별 상품 조회 
	public Product selectOne(int productNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product product = null;
		
		try {
			String sql = "select * from product where product_num=?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int scategoryNum = rs.getInt("category_num");
				String pname = rs.getString("pname");
				String pdesc = rs.getString("pdesc");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				
				product = new Product(productNum, scategoryNum, pname, pdesc, price, discount, cnt, regdate, image, 0);
			}
			return product;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 개별 상품 정보 + 상품 상세정보 동시 조회
	public ProductCommand selectDesc(int productNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductCommand prd = null;
		
		try {
			String sql = "SELECT * "
					+ "FROM product p "
					+ "JOIN productdetail d "
					+ "ON p.product_num = d.product_num "
					+ "AND p.product_num = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int scategoryNum = rs.getInt("category_num");
				String pname = rs.getString("pname");
				String pdesc = rs.getString("pdesc");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				int pdescNum = rs.getInt("productdetail_num");
				String descImg = rs.getString("images");
				
				prd = new ProductCommand(productNum, scategoryNum, pname, pdesc, price, discount, cnt, regdate, image, pdescNum, descImg);
			}
			return prd;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 상품 등록
	public int insert(ProductCommand prdComm) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			String sql1 = "INSERT INTO product "
					+ "VALUES(seq_product.nextval, ?, ?, ?, ?, ?, ?, sysdate, ?)";
			con = DBPool.getConnection();
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, prdComm.getScategoryNum());
			pstmt1.setString(2, prdComm.getPname());
			pstmt1.setString(3, prdComm.getPdesc());
			pstmt1.setInt(4, prdComm.getPrice());
			pstmt1.setInt(5, prdComm.getDiscount());
			pstmt1.setInt(6, prdComm.getCnt());
			pstmt1.setString(7, prdComm.getImage());
			int n1 = pstmt1.executeUpdate();
			int n2 = 0;
			if(n1>0) {
				String sql2 = "INSERT INTO productdetail "
						+ "VALUES(seq_productdetail.nextval, seq_product.currval, ?)";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, prdComm.getDescImg());
				n2 = pstmt2.executeUpdate();
			}
			return n2;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(pstmt2);
			DBPool.close(pstmt1);
			DBPool.close(con);
		}
	}
	
	// 개별 상품 정보 + 상품 상세정보 동시 조회 + 대분류명,소분류명
	public ProductCommand selectDescPlus(int productNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductCommand prd = null;
		
		try {
			String sql = "SELECT * "
					+ "FROM product p "
					+ "JOIN productdetail d "
					+ "ON p.product_num = d.product_num "
					+ "JOIN scategory s "
					+ "ON p.category_num = s.scategory_num "
					+ "AND p.product_num = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int scategoryNum = rs.getInt("category_num");
				String pname = rs.getString("pname");
				String pdesc = rs.getString("pdesc");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				int cnt = rs.getInt("cnt");
				Date regdate = rs.getDate("regdate");
				String image = rs.getString("image");
				int pdescNum = rs.getInt("productdetail_num");
				String descImg = rs.getString("images");
				String btype = rs.getString("btype");
				String stype = rs.getString("stype");
				
				prd = new ProductCommand(productNum, scategoryNum, pname, pdesc, price, discount, cnt, regdate, image, pdescNum, descImg, btype, stype);
			}
			return prd;
			
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 상품 update
	public int update(int pnum, ProductCommand prdComm) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			String sql1 = "UPDATE product "
					+ "SET category_num=?,pname=?,pdesc=?,price=?,discount=?,cnt=?,image=? "
					+ "WHERE product_num = ?";
			con = DBPool.getConnection();
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, prdComm.getScategoryNum());
			pstmt1.setString(2, prdComm.getPname());
			pstmt1.setString(3, prdComm.getPdesc());
			pstmt1.setInt(4, prdComm.getPrice());
			pstmt1.setInt(5, prdComm.getDiscount());
			pstmt1.setInt(6, prdComm.getCnt());
			pstmt1.setString(7, prdComm.getImage());
			pstmt1.setInt(8, pnum);
			int n1 = pstmt1.executeUpdate();
			int n2 = 0;
			if(n1>0) {
				String sql2 = "UPDATE productdetail "
						+ "SET images=? "
						+ "WHERE product_num = ?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, prdComm.getDescImg());
				pstmt2.setInt(2, pnum);
				n2 = pstmt2.executeUpdate();
			}
			return n2;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(pstmt2);
			DBPool.close(pstmt1);
			DBPool.close(con);
		}
	}
	
	// 상품 update (-썸네일이미지)
	public int updateNoThum(int pnum, ProductCommand prdComm) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			String sql1 = "UPDATE product "
					+ "SET category_num=?,pname=?,pdesc=?,price=?,discount=?,cnt=? "
					+ "WHERE product_num = ?";
			con = DBPool.getConnection();
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, prdComm.getScategoryNum());
			pstmt1.setString(2, prdComm.getPname());
			pstmt1.setString(3, prdComm.getPdesc());
			pstmt1.setInt(4, prdComm.getPrice());
			pstmt1.setInt(5, prdComm.getDiscount());
			pstmt1.setInt(6, prdComm.getCnt());
			pstmt1.setInt(7, pnum);
			int n1 = pstmt1.executeUpdate();
			int n2 = 0;
			if(n1>0) {
				String sql2 = "UPDATE productdetail "
						+ "SET images=? "
						+ "WHERE product_num = ?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, prdComm.getDescImg());
				pstmt2.setInt(2, pnum);
				n2 = pstmt2.executeUpdate();
			}
			return n2;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(pstmt2);
			DBPool.close(pstmt1);
			DBPool.close(con);
		}
	}
	
	// 상품 update (-상세이미지)
	public int updateNoDescImg(int pnum, ProductCommand prdComm) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE product "
					+ "SET category_num=?,pname=?,pdesc=?,price=?,discount=?,cnt=?,image=? "
					+ "WHERE product_num = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prdComm.getScategoryNum());
			pstmt.setString(2, prdComm.getPname());
			pstmt.setString(3, prdComm.getPdesc());
			pstmt.setInt(4, prdComm.getPrice());
			pstmt.setInt(5, prdComm.getDiscount());
			pstmt.setInt(6, prdComm.getCnt());
			pstmt.setString(7, prdComm.getImage());
			pstmt.setInt(8, pnum);
			int n = pstmt.executeUpdate();
			return n;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(pstmt);
			DBPool.close(con);
		}
	}
	
	// 상품 update (-썸네일,상세이미지)
	public int updateNoImg(int pnum, ProductCommand prdComm) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE product "
					+ "SET category_num=?,pname=?,pdesc=?,price=?,discount=?,cnt=? "
					+ "WHERE product_num = ?";
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prdComm.getScategoryNum());
			pstmt.setString(2, prdComm.getPname());
			pstmt.setString(3, prdComm.getPdesc());
			pstmt.setInt(4, prdComm.getPrice());
			pstmt.setInt(5, prdComm.getDiscount());
			pstmt.setInt(6, prdComm.getCnt());
			pstmt.setInt(7, pnum);
			int n = pstmt.executeUpdate();
			return n;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(pstmt);
			DBPool.close(con);
		}
	}
	
	// 상품 delete (product, productdetail, review
	public int delete(int pnum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		try {
			sql1 = "DELETE FROM review WHERE product_num = ?";
			con = DBPool.getConnection();
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, pnum);
			n1 = pstmt1.executeUpdate();
			System.out.println("삭제 리뷰 개수:"+n1);
			
				sql2 = "DELETE FROM productdetail WHERE product_num = ?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, pnum);
				n2 = pstmt2.executeUpdate();
				System.out.println("삭제 상세페이지 개수:"+n2);
				
					sql3 = "DELETE FROM product WHERE product_num = ?";
					pstmt3 = con.prepareStatement(sql3);
					pstmt3.setInt(1, pnum);
					n3 = pstmt3.executeUpdate();
					System.out.println("삭제 상품 개수:"+n3);

			return n3;
		}catch (SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			DBPool.close(pstmt3);
			DBPool.close(pstmt2);
			DBPool.close(pstmt1);
			DBPool.close(con);
		}
	}
}
