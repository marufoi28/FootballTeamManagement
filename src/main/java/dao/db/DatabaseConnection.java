package dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.DatabaseConfig;

public class DatabaseConnection {
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(
					DatabaseConfig.getUrl(),
					DatabaseConfig.getUsername(),
					DatabaseConfig.getPassword()
		     );		
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to connect to the database", e);
		}
		return connection;
	}
}
