package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shop.command.LoginCommand;
import com.shop.util.DBPool;

public class OrdersDao {

	private static OrdersDao tempOrdersDao = null;
	
	private OrdersDao() {};
	
	public static OrdersDao getInstance() {
		if(tempOrdersDao == null) {
			tempOrdersDao = new OrdersDao();
		}
		return tempOrdersDao;
	}
	
	public LoginCommand nonMemberLogin(String reqEmail, String orderPwd) {
		String sql = "SELECT * FROM orders WHERE email = ? AND orderpwd = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reqEmail);
			pstmt.setString(1, orderPwd);
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
