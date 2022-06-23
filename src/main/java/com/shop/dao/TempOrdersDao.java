package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.command.ChartCommand;
import com.shop.command.SalesCommand;
import com.shop.util.DBPool;

public class TempOrdersDao {

	private static TempOrdersDao tempOrdersDao = null;
	
	private TempOrdersDao() {}
	
	public static TempOrdersDao getInstance() {
		if(tempOrdersDao == null) {
			tempOrdersDao = new TempOrdersDao();
		}
		return tempOrdersDao;
	}
	
	// 결제내역 리스트
	public List<SalesCommand> selectList(int page, String date1, String date2){
		String sql = "SELECT * FROM ( "
				+ "SELECT ROWNUM RN, OD.* FROM ( "
				+ "SELECT O.email, O.name, D.pnum, D.pname, D.piece, D.total, O.orderstate, O.regdate "
				+ "FROM orders O JOIN orderdetail D ON O.order_num = D.order_num "
				+ "WHERE O.regdate >= ? AND O.regdate < ? ) OD ) "
				+ "WHERE RN BETWEEN ? AND ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SalesCommand> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			pstmt.setInt(3, 1 + (page-1) * 10);
			pstmt.setInt(4, page * 10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("RN");
				String email = rs.getString("email");
				String name = rs.getString("name");
				int pnum = rs.getInt("pnum");
				int piece = rs.getInt("piece");
				int total = rs.getInt("total");
				String state = rs.getString("orderstate");
				Date regDate = rs.getDate("regDate");
				
				SalesCommand command = new SalesCommand(
						num, email, name, pnum, piece, total, state, regDate);
				list.add(command);
			}
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 페이징 처리를 위한 리스트 카운트
	public int getCount(String date1, String date2){
		String sql = "SELECT NVL(count(*), 0) cnt "
				+ "FROM orders O JOIN orderdetail D ON O.order_num = D.order_num "
				+ "WHERE O.regdate >= ? AND O.regdate < ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			return count;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 년도별 매출 얻기
	public List<ChartCommand> yearList(String year){
		String sql = "SELECT TO_CHAR(regdate, 'YYYYMM') as day, sum(amount) as price "
				+ "FROM (SELECT * FROM orders WHERE orderstate = '결제완료') "
				+ "WHERE regdate >= ? and regdate < ? "
				+ "GROUP BY to_char(regdate, 'YYYYMM') "
				+ "ORDER BY to_char(regdate, 'YYYYMM')";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ChartCommand> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			if(year.equals("all")) {
				pstmt.setString(1, "20170101");
				pstmt.setString(2, "20230101");
			}else {
				// ex) 2020년도가 들어오면 20200101 ~ 20210101로 만들어줌
				pstmt.setString(1, year+"0101");
				pstmt.setString(2, (Integer.parseInt(year)+1)+"0101");
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String day = rs.getString("day");
				int price = rs.getInt("price");
				
				ChartCommand command = new ChartCommand(day, price);
				list.add(command);
			}
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 월별 매출 얻기
	public List<ChartCommand> monthList(String date1, String date2){
		String sql = "SELECT TO_CHAR(regdate, 'YYYYMMDD') as day, sum(amount) as price "
				+ "FROM (SELECT * FROM orders WHERE orderstate = '결제완료') "
				+ "WHERE regdate >= ? and regdate < ? "
				+ "GROUP BY to_char(regdate, 'YYYYMMDD') "
				+ "ORDER BY to_char(regdate, 'YYYYMMDD')";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ChartCommand> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String day = rs.getString("day");
				int price = rs.getInt("price");
				
				ChartCommand command = new ChartCommand(day, price);
				list.add(command);
			}
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 결제완료된 금액
	public int completedPayment(String date1, String date2){
		String sql = "SELECT NVL(SUM(amount), 0) amount FROM orders "
				+ "WHERE regdate >= ? AND regdate < ? AND orderstate = '결제완료'";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum = 0;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sum = rs.getInt("amount");
			}
			return sum;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}

	// 결제취소된 금액
	public int cancelPayment(String date1, String date2){
		String sql = "SELECT NVL(SUM(amount), 0) amount FROM orders "
				+ "WHERE regdate >= ? AND regdate < ? AND orderstate = '결제취소'";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum = 0;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sum = rs.getInt("amount");
			}
			return sum;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 전체금액
	public int totalSales(String date1, String date2){
		String sql = "SELECT NVL(SUM(amount), 0) amount FROM orders "
				+ "WHERE regdate >= ? AND regdate < ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum = 0;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sum = rs.getInt("amount");
			}
			return sum;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
}
