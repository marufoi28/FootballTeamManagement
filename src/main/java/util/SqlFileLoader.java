package util;

import java.io.IOException;
import java.io.InputStream;

public class SqlFileLoader {
	
	private SqlFileLoader() {
		//インスタンス化を防止
	}
	
	public static String getSqlQuery(String operation, String folder) {
		String filePath = folder + "/" + operation + ".sql";	
		try {
			return loadSQLFromFile(filePath);
		} catch(IOException e) {
			throw new IllegalStateException("SQLファイルの読み込みエラー: " + filePath, e);
		}
	}
	
	public static String loadSQLFromFile(String filePath) throws IOException {
		InputStream inputStream = SqlFileLoader.class.getClassLoader().getResourceAsStream(filePath);
		
		if(inputStream == null) {
			throw new IllegalStateException("SQLファイルが見つかりません: " + filePath);
		}
		return new String(inputStream.readAllBytes());
	}
}
