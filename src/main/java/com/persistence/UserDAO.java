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
	
	public User retrieve(String name)
	{
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		User user = null;
		
		try 
		{
			connection = ConnectionJDBC.getInstance();
			String sql = "select id, name from rappel1 where name like ?;";
			st = connection.prepareStatement(sql);
			st.setString(1, name);
			
			rs = st.executeQuery();
			
			if(rs.next())
			{
				user = new User(rs.getLong(1), rs.getString(2));
			}
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try
			{
				rs.close();
				st.close();

				if(connection != null)
				{
					ConnectionJDBC.close(connection);
				}
			}

			catch (SQLException e)
			{
				e.printStackTrace();
			}	
		}
		
		return user;
	}
}
