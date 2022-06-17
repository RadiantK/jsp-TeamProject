package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public int newOrderNum(String mnum, int amount) {
		String sql = "select order_num from orders where member_num=? and amount=? "
				   + "and to_char(regdate)=to_char(sysdate,'yy/mm/dd')";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mnum);
			pstmt.setInt(2, amount);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int newOrderNum = rs.getInt("order_num");
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
