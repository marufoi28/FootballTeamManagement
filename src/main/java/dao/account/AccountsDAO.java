package dao.account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.db.DatabaseConnection;
import model.account.Account;
import model.account.Login;
import util.JdbcUtil;
import util.SqlFileLoader;

public class AccountsDAO {
	public static final String SQL_ACCOUNT = "sql/account";
	public Account findByLogin(Login login) {
		Account account = null;
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();){

			String sql = SqlFileLoader.getSqlQuery("select", SQL_ACCOUNT);
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			String hashedPassword = hashPassword(login.getPass());
			
			pStmt.setString(1, login.getUserId());
			pStmt.setString(2, hashedPassword);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				account = new Account(userId, pass, mail, name, age);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
	
	public void resisterUser(Account account) {
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();){
			String sql = SqlFileLoader.getSqlQuery("insert", SQL_ACCOUNT);
			
			conn.setAutoCommit(false);
			
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				
				String hashedPassword = hashPassword(account.getPass());
				
				pStmt.setString(1, account.getUserId());
				pStmt.setString(2, hashedPassword);
				pStmt.setString(3, account.getMail());
				pStmt.setString(4, account.getName());
				pStmt.setInt(5, account.getAge());
				
				int affectedRows = pStmt.executeUpdate();
				if(affectedRows == 0) {
					throw new SQLException("登録に失敗しました" + account.toString());
				}
				conn.commit();
			} catch(SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch(SQLException e) {
	        e.printStackTrace();
			throw new RuntimeException("データベース操作中にエラーが発生しました", e);
		}
	}
	
	private String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes());
			StringBuilder hexString = new StringBuilder();
			for(byte b : hash) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch(NoSuchAlgorithmException e) {
			throw new RuntimeException("パスワードのハッシュ化時にエラーが発生しました", e);
		}
	}
}