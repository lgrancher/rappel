package com.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class ConnectionJDBC 
{
	private static ConnectionJDBC connectionJDBC;
	private static String url = "jdbc:mysql://localhost:3306/rappel";
	private static String user = "root";
	private static String passwd = "root";
	private static BoneCP connectionPool = null;
	private static ThreadLocal<Connection> threadConnect;

	private ConnectionJDBC()
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

	public static Connection getInstance() throws SQLException
	{
		if(connectionJDBC==null)
		{
			connectionJDBC = new ConnectionJDBC();
		}
		
		Connection connect = connectionPool.getConnection();
		connect.setAutoCommit(false);
		threadConnect.set(connect);
	
		return threadConnect.get();	
	}
	
	public static void close(Connection connection)
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
