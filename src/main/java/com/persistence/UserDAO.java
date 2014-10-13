package com.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public class UserDAO 
{		
	public User retrieve(String name)
	{
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		User user = null;
		
		try 
		{
			connection = ConnectionJDBC.INSTANCE.getConnection();
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
			}
			
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
		}
		
		return user;
	}	
	
	public void create(User user)
	{
		Connection connection = null;
		PreparedStatement st = null;
		
		try 
		{
			connection = ConnectionJDBC.INSTANCE.getConnection();
			String sql = "insert into rappel1 values(default,?)";
			st = connection.prepareStatement(sql);
			System.out.println(user.getNom());
			st.setString(1, user.getNom());
			
			st.executeUpdate();
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try
			{
				st.close();
				
			}
			
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
		}
	}
}
