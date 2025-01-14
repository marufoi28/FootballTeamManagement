package util;

import config.DatabaseConfig;

public class JdbcUtil {
	
	private JdbcUtil() {
		//インスタンス化を防止
	}
	
	public static void loadJDBCDriver() {
		try {
			Class.forName(DatabaseConfig.getDriver());
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
	}
}
