package com.dkte.pizzashop.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class PizzaDao implements AutoCloseable {
private Connection connection;
	
	public PizzaDao() throws SQLException {
		 connection = DBUtil.getConnection();
	}
	
	public List<Pizza> getAllPizza()throws SQLException{
		List<Pizza> pizzalist = new ArrayList<Pizza>();
		String sql = "SELECT * FROM  menu";
		try(PreparedStatement selectStatement = connection.prepareCall(sql)){
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
	
	
	public void insertPizza(Pizza pizza) throws SQLException {
		String sql = "INSERT INTO menu (name ,description ,price) VALUES (?,?,?)";
		try(PreparedStatement insertmenu = connection.prepareCall(sql)) {
			insertmenu.setString(1, pizza.getName());
			insertmenu.setString(2, pizza.getDescription());
			insertmenu.setDouble(3, pizza.getPrice());
			insertmenu.executeUpdate();
		}
		
	}
	
	public void updatePricePizza(double price,int mid)throws SQLException{
		String sql = "UPDATE menu SET price = ? WHERE mid = ? ";
		try(PreparedStatement updatePrice = connection.prepareCall(sql)){
			updatePrice.setDouble(1,price);
			updatePrice.setInt(1,mid);
			updatePrice.executeUpdate();
		}
	}
	
	public void deleteMenu(int mid) throws SQLException {
		String sql="DELETE FROM menu WHERE mid = ?";
		try(PreparedStatement deletePizzaMenu = connection.prepareCall(sql)){
			deletePizzaMenu.setInt(1, mid);
			deletePizzaMenu.executeUpdate();
		}
	}
	
	@Override
	public void close() throws SQLException {
		if(connection!=null) {
			connection.close();
		}
	}
}
