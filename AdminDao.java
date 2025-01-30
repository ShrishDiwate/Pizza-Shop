package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkte.pizzashop.entities.Admin;

import com.dkte.pizzashop.utils.DBUtil;

public class AdminDao implements AutoCloseable{

	private Connection connection;
	
	public AdminDao() throws SQLException {
		connection = DBUtil.getConnection();
	}
	
	public Admin getAdmin(String email ,String password) throws SQLException{
		String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
		try(PreparedStatement selectstatement = connection.prepareStatement(sql)){
			selectstatement.setString(1, email);
			selectstatement.setString(2, password);
			ResultSet rs = selectstatement.executeQuery();
			if(rs.next()) {
				Admin admin = new Admin();
				admin.setAid(rs.getInt(1));
				admin.setName(rs.getString(2));
				admin.setEmail(rs.getString(3));
				admin.setPassword(rs.getString(4));
				admin.setMobile(rs.getString(5));
				return admin;
			}

		}
		return null;
	}
	
	@Override
	public void close() throws SQLException {
		if(connection!=null) {
			connection.close();
		}
		
	}
}
