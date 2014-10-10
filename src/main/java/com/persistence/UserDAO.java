package com.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.User;

public class UserDAO 
{
	private static UserDAO userDAO;
	
	private UserDAO(){}
	
	public static UserDAO getInstance()
	{
		if(userDAO == null)
		{
			userDAO = new UserDAO();
		}
		
		return userDAO;
	}
	
	public User retrieve(String name) throws SQLException
	{
		Connection connection = ConnectionJDBC.getInstance();
		
		String sql = "select id, name from rappel1 where name like ?;";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, name);
		
		ResultSet rs = st.executeQuery();
		User user = null;
		
		if(rs.next())
		{
			user = new User(rs.getLong(1), rs.getString(2));
		}
		
		rs.close();
		st.close();
		ConnectionJDBC.close(connection);
		
		return user;
	}
}
