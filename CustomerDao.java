package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.utils.DBUtil;
import com.mysql.cj.protocol.Resultset;

public class CustomerDao implements AutoCloseable{
	private Connection connection;
	
	public CustomerDao() throws SQLException {
		connection = DBUtil.getConnection();
	}
	
	
	public void insertCustomer(Customer customer) throws SQLException{
		String sql="INSERT INTO customer (name,email,password,mobile) VALUES (?,?,?,?)";
		try(PreparedStatement insertstatement = connection.prepareCall(sql)){
			insertstatement.setString(1, customer.getName());
			insertstatement.setString(2, customer.getEmail());
			insertstatement.setString(3, customer.getPassword());
			insertstatement.setString(4, customer.getMobile());
			insertstatement.executeUpdate();
		}
	}
	
	public Customer getCustomer(String email ,String password) throws SQLException{
		String sql = "SELECT * FROM Customer WHERE email = ? AND password = ?";
		try(PreparedStatement selectstatement = connection.prepareStatement(sql)){
			selectstatement.setString(1, email);
			selectstatement.setString(2, password);
			ResultSet rs = selectstatement.executeQuery();
			if(rs.next()) {
				Customer customer = new Customer();
				customer.setCid(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setPassword(rs.getString(4));
				customer.setMobile(rs.getString(5));
				return customer;
			}

		}
		return null;
	}
	
	public List<Customer> getAllCustomer()throws SQLException{
		List<Customer> customerlist = new ArrayList<Customer>();
		String sql = "SELECT * FROM  customer";
		try(PreparedStatement selectStatement = connection.prepareCall(sql)){
			ResultSet rs= selectStatement.executeQuery();
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCid(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setPassword(rs.getString(4));
				customer.setMobile(rs.getString(5));
				customerlist.add(customer);
			}
		}
		return customerlist;
	}
	@Override
	public void close() throws SQLException {
		if(connection!=null) {
			connection.close();
		}
	}
}
