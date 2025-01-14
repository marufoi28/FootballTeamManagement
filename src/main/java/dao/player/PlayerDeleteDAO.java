package dao.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.db.DatabaseConnection;
import util.JdbcUtil;
import util.SqlFileLoader;

public class PlayerDeleteDAO {
	public static final String SQL_PLAYER = "sql/player";
	
	public void deletePlayer(int playerId) {
		String sql = SqlFileLoader.getSqlQuery("delete", SQL_PLAYER);
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();){
			conn.setAutoCommit(false);
			
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setInt(1, playerId);
				
				int affectedRows = pStmt.executeUpdate();
				if(affectedRows == 0) {
					throw new SQLException("削除に失敗しました");
				}
				conn.commit();
			} catch(SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("データベース操作中にエラーが発生しました",e);
		}
	}
}
