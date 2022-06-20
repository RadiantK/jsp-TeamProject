package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shop.command.LoginCommand;
import com.shop.dto.Orders;
import com.shop.util.DBPool;

public class OrdersDao {

	private static OrdersDao OrdersDao = null;
	
	private OrdersDao() {};
	
	public static OrdersDao getInstance() {
		if(OrdersDao == null) {
			OrdersDao = new OrdersDao();
		}
		return OrdersDao;
	}
	
	// 관리자페이지 주문내역 검색 결과수
	public int adminSearchCnt(String col, String keyword) {
		String sql = "select NVL(count(*),0) as cnt from orders where " + col + "=?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,keyword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}
			return -1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 관리자페이지 주문내역 결과수
	public int adminCnt() {
		String sql = "select NVL(count(*),0) as cnt from orders";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}
			return -1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 관리자 주문내역 검색
	public ArrayList<Orders> adminSearch(int startRow,int endRow,String col,String keyword) {
		
		String sql = "select * from "
				   + "(select o.*, ROWNUM rnum from "
				   + "(select * from orders where " + col + "=? order by regdate desc, order_num desc) o)"
				   + "where rnum>=? and rnum<=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Orders> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,keyword);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int orderNum = rs.getInt("order_num");
				String memberNum = rs.getString("member_num");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				int amount = rs.getInt("amount");
				String orderPwd = rs.getString("orderpwd");
				String orderState = rs.getString("orderstate");
				Date regdate = rs.getDate("regdate");
				
				Orders orders = new Orders(orderNum,memberNum,email,name,phone,amount,orderPwd,orderState,regdate);
				list.add(orders);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 관리자 주문내역 조회 
	public ArrayList<Orders> adminList(int startRow,int endRow) {
		
		String sql = "select * from "
				   + "(select o.*, ROWNUM rnum from "
				   + "(select * from orders order by regdate desc, order_num desc) o)"
				   + "where rnum>=? and rnum<=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Orders> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int orderNum = rs.getInt("order_num");
				String memberNum = rs.getString("member_num");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				int amount = rs.getInt("amount");
				String orderPwd = rs.getString("orderpwd");
				String orderState = rs.getString("orderstate");
				Date regdate = rs.getDate("regdate");
				
				Orders orders = new Orders(orderNum,memberNum,email,name,phone,amount,orderPwd,orderState,regdate);
				list.add(orders);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 주문취소
	public int orderCancle(int orderNum) {
		String sql = "update orders set orderstate='주문취소' where order_num=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNum);

			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	
	// 상세페이지 조회
	public Orders selectDetail(int orderNum) {
		
		String sql = "select * from orders where order_num=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memberNum = rs.getString("member_num");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				int amount = rs.getInt("amount");
				String orderPwd = rs.getString("orderpwd");
				String orderState = rs.getString("orderstate");
				Date regdate = rs.getDate("regdate");
				
				return new Orders(orderNum,memberNum,email,name,phone,amount,orderPwd,orderState,regdate);

			}
			return null;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 마이페이지 주문내역 결과수
	public int getCnt(String email, String orderPwd) {
		String sql = "select NVL(count(*),0) as cnt from orders where email=? and orderpwd=?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,email);
			pstmt.setString(2, orderPwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}
			return -1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 마이페이지 주문내역 
	public ArrayList<Orders> selectList(int startRow,int endRow,String email,String orderPwd) {
		
		String sql = "select * from"
				   + "(select a.*, rownum rnum from"
				   + "(select * from orders where email=? and orderpwd=?"
				   + " order by order_num desc) a)"
				   + " where rnum>=? and rnum<=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Orders> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, orderPwd);	
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int orderNum = rs.getInt("order_num");
				String memberNum = rs.getString("member_num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				int amount = rs.getInt("amount");
				String orderState = rs.getString("orderstate");
				Date regdate = rs.getDate("regdate");
				
				Orders orders = new Orders(orderNum,memberNum,email,name,phone,amount,orderPwd,orderState,regdate);
				list.add(orders);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 마이페이지 주문상태 검색결과수
	public int getSearchCnt(String email, String orderPwd, String orderState) {
		String sql = "select NVL(count(*),0) as cnt from orders where email=? and orderpwd=? and orderstate=?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,email);
			pstmt.setString(2, orderPwd);
			pstmt.setString(3, orderState);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}
			return -1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 마이페이지 주문상태 검색 
	public ArrayList<Orders> searchList(int startRow,int endRow,String email,String orderPwd,String orderState) {
		
		String sql = "select * from"
				   + "(select a.*, rownum rnum from"
				   + "(select * from orders where email=? and orderpwd=? and orderstate=?"
				   + " order by order_num desc) a)"
				   + " where rnum>=? and rnum<=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Orders> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, orderPwd);
			pstmt.setString(3, orderState);			
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int orderNum = rs.getInt("order_num");
				String memberNum = rs.getString("member_num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				int amount = rs.getInt("amount");
				Date regdate = rs.getDate("regdate");
				
				Orders orders = new Orders(orderNum,memberNum,email,name,phone,amount,orderPwd,orderState,regdate);
				list.add(orders);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 신규 주문 
	public int newOrder(Orders orders) {
		String sql = "insert into orders values"
				   + "(seq_orders.nextval,?,?,?,?,?,?,'결제완료',sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
	try {
		con = DBPool.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, orders.getMemberNum());
		pstmt.setString(2, orders.getEmail());
		pstmt.setString(3, orders.getName());
		pstmt.setString(4, orders.getPhone());
		pstmt.setInt(5, orders.getAmount());
		pstmt.setString(6, orders.getOrderPwd());
		return pstmt.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
		return -1;
	}
	}
	
	// 신규 주문번호 가져오기 
	public int newOrderNum() {
		String sql = "select * from "
				   + "(select last_value(order_num) over() as newordernum from orders) "
				   + "where rownum=1";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int newOrderNum = rs.getInt("newordernum");
				return newOrderNum;
			}
			return -1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 비회원 주문내역 조회 
	public LoginCommand nonMemberLogin(String reqEmail, String orderPwd) {
		String sql = "SELECT * FROM orders WHERE email = ? AND orderpwd = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reqEmail);
			pstmt.setString(2, orderPwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString("email");
				String pwd = rs.getString("orderpwd");
				
				LoginCommand login = new LoginCommand(email, pwd, 0);
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
