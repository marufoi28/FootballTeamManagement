package dao.match;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import dao.db.DatabaseConnection;
import model.match.Match;
import util.JdbcUtil;
import util.SqlFileLoader;

public class MatchAddDAO {
	public static final String SQL_MATCH = "sql/match";
	
	public void matchAddDAO(Match match) {
		String sql = SqlFileLoader.getSqlQuery("insert", SQL_MATCH);
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();){
			
			conn.setAutoCommit(false);
			
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setDate(1,Date.valueOf(match.getEventDate()));
				pStmt.setInt(2, match.getField().getFieldId());
				pStmt.setInt(3, match.getOpponent().getOpponentId());
				pStmt.setTime(4, Time.valueOf(match.getEventStartTime()));
				
				int affectedRows = pStmt.executeUpdate();
				if(affectedRows == 0) {
					throw new SQLException("登録に失敗しました" + match.toString());
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
}
