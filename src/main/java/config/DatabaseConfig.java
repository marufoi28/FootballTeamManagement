package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
	private static Properties properties = new Properties();
	
	static {
		try(InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")){
			if(input == null) {
				throw new RuntimeException("db.properties file not found in classpath");
			}
			properties.load(input);
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load database configuration", e);
		}
	}
	
	public static String getUrl() {
		return properties.getProperty("db.url");
	}
	
	public static String getUsername() {
		return properties.getProperty("db.username");
	}
	
	public static String getPassword() {
		return properties.getProperty("db.password");
	}
	public static String getDriver() {
		return properties.getProperty("db.driver");
	}
}
