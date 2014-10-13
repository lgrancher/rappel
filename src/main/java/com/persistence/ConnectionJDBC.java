package com.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionJDBC
{
	INSTANCE;
	private ConnectionJDBC connectionJDBC;
	private String url = "jdbc:mysql://localhost:3306/rappel";
	private String user = "root";
	private String passwd = "root";
	private BoneCP connectionPool = null;
	private ThreadLocal<Connection> threadConnect;

	ConnectionJDBC()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(user);
			config.setPassword(passwd);
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			connectionPool = new BoneCP(config);
			threadConnect = new ThreadLocal<Connection>();
		} 
		
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException
	{	
		if(threadConnect.get() == null)
		{
			Connection connection = connectionPool.getConnection();
			connection.setAutoCommit(false);
			threadConnect.set(connection);	
		}
		
		return threadConnect.get();
	}
	
	public void close(Connection connection)
	{
		try 
		{
			connection.close();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		finally
		{
			threadConnect.remove();
		}
	}
}
