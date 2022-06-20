package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shop.dto.OrderDetail;
import com.shop.util.DBPool;

public class OrderDetailDao {
	
	private static OrderDetailDao orderDetailDao = null;

	private OrderDetailDao() {}
	
	public static OrderDetailDao getInstance() {
		if (orderDetailDao == null ) {
			orderDetailDao = new OrderDetailDao();
		}
		return orderDetailDao;
	}
	
	// 상세페이지 주문제품 조회 
	public ArrayList<OrderDetail> selecDetail(int orderNum) {
		
		String sql = "select * from orderdetail where order_num=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDetail> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int orderDetailNum = rs.getInt("orderdetail_num");
				int pnum = rs.getInt("pnum");
				String pname = rs.getString("pname");
				int piece = rs.getInt("piece");
				int total = rs.getInt("total");
				String image = rs.getString("image");
				
				OrderDetail od = new OrderDetail(orderDetailNum,orderNum,pnum,pname,piece,total,image);
				list.add(od);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 주문내역의 주문제품 조회 
	public ArrayList<OrderDetail> selectList(int startRow,int endRow,String email,String orderPwd) {
		
		String sql = "select od.* from "
				+ "(select * from "
				+ "(select o1.*, rownum rnum from "
				+ "(select * from orders where email=? and orderpwd=?"
				+ " order by order_num desc) o1) "
				+ "where rnum>=? and rnum<=?) o2 "
				+ "join orderdetail od on o2.order_num=od.order_num";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDetail> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, orderPwd);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int orderDetailNum = rs.getInt("orderdetail_num");
				int orderNum = rs.getInt("order_num");
				int pnum = rs.getInt("pnum");
				String pname = rs.getString("pname");
				int piece = rs.getInt("piece");
				int total = rs.getInt("total");
				String image = rs.getString("image");
				
				OrderDetail od = new OrderDetail(orderDetailNum,orderNum,pnum,pname,piece,total,image);
				list.add(od);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 검색한 주문내역의 주문제품 조회
	public ArrayList<OrderDetail> searchList(int startRow,int endRow,String email,String orderPwd,String orderState) {
		
		String sql = "select od.* from "
				+ "(select * from "
				+ "(select o1.*, rownum rnum from "
				+ "(select * from orders where email=? and orderpwd=?"
				+ " and orderstate=? order by order_num desc) o1)"
				+ "where rnum>=? and rnum<=?) o2"
				+ "join orderdetail od on o2.order_num=od.order_num";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDetail> list = new ArrayList<>();
		
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
				int orderDetailNum = rs.getInt("orderdetail_num");
				int orderNum = rs.getInt("order_num");
				int pnum = rs.getInt("pnum");
				String pname = rs.getString("pname");
				int piece = rs.getInt("piece");
				int total = rs.getInt("total");
				String image = rs.getString("image");
				
				OrderDetail od = new OrderDetail(orderDetailNum,orderNum,pnum,pname,piece,total,image);
				list.add(od);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	// 신규주문 제품 추가 
	public int newOrderDetail(OrderDetail orderDetail) {
		String sql = "insert into orderdetail values(seq_orderdetail.nextval,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderDetail.getOrderNum());
			pstmt.setInt(2, orderDetail.getPnum());
			pstmt.setString(3, orderDetail.getPname());
			pstmt.setInt(4, orderDetail.getPiece());
			pstmt.setInt(5, orderDetail.getTotal());
			pstmt.setString(6, orderDetail.getImage());
			int n = pstmt.executeUpdate();
			
			return n;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
