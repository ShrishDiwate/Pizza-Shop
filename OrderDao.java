package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.ForAllOrders;
import com.dkte.pizzashop.entities.Order;
import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class OrderDao implements AutoCloseable{
	private Connection connection;
	
	public OrderDao() throws SQLException {
		connection = DBUtil.getConnection();
	}
	
	
	public void placeOrder(int cid, int mid) throws SQLException {
		String sql = "INSERT INTO orders (cid,mid) VALUES (?,?)";
		try (PreparedStatement insertstatement = connection.prepareCall(sql)) {
			insertstatement.setInt(1, cid);
			insertstatement.setInt(2, mid);
			
			insertstatement.executeUpdate();
		}
	}
	
	public List<Pizza> getAllOrder(int cid) throws SQLException {
		List<Pizza> pizzalist = new ArrayList<Pizza>();
		String sql = "SELECT m.* FROM menu m INNER JOIN orders o ON m.mid=o.mid WHERE o.cid=?";
		try(PreparedStatement selectStatement = connection.prepareCall(sql)){
			selectStatement.setInt(1,cid );
			ResultSet rs= selectStatement.executeQuery();
			while(rs.next()) {
				Pizza pizza = new Pizza();
				pizza.setMid(rs.getInt(1));
				pizza.setName(rs.getString(2));
				pizza.setDescription(rs.getString(3));
				pizza.setPrice(rs.getDouble(4));
				pizzalist.add(pizza);
			}
		}
		return pizzalist;
		
	}
	
	public List<ForAllOrders> getOverAllOrders() throws SQLException{
		List<ForAllOrders> orderList = new ArrayList<ForAllOrders>();
		String sql="SELECT orders.oid AS OrderID, " + "customer.name AS CustomerName, " + "customer.email AS CustomerEmail, " + "menu.name AS MenuItem, " + "menu.price AS ItemPrice " + "FROM orders " + "JOIN customer ON orders.cid = customer.cid " + "JOIN menu ON orders.mid = menu.mid";
		try(PreparedStatement getAllOrders = connection.prepareStatement(sql)){
					ResultSet rs = getAllOrders.executeQuery();
					while(rs.next()) {
//						int orderId = rs.getInt("OrderId");
//						String CustomerName = rs.getString("Customername");
//						String CustomerEmail = rs.getString("CustomerEmail");
//						String menuItem = rs.getString("manuItem");
//						double price = rs.getDouble("price");
						ForAllOrders allOrders = new ForAllOrders();
						allOrders.setOrderId(rs.getInt(1));
						allOrders.setCustomerName(rs.getString(2));
						allOrders.setCustomerEmail(rs.getString(3));
						allOrders.setMenuItem(rs.getString(4));
						allOrders.setPrice(rs.getDouble(5));
						orderList.add(allOrders);
					}
				}
				return orderList;
	}

	@Override
	public void close() throws SQLException {
		if(connection!=null) {
			connection.close();
		}
		
	}
	
	
}
