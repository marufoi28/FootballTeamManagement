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

public class MatchEditDAO {
	public static final String SQL_MATCH = "sql/match";
	public void matchEditDAO(Match match) {
		String sql = SqlFileLoader.getSqlQuery("update", SQL_MATCH);
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();){
			conn.setAutoCommit(false);
			
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setInt(1, match.getField().getFieldId());
				pStmt.setDate(2, Date.valueOf(match.getEventDate()));
				pStmt.setTime(3, Time.valueOf(match.getEventStartTime()));
				pStmt.setInt(4, match.getOpponent().getOpponentId());
				pStmt.setInt(5, match.getMatchId());
				
				int affectedRows = pStmt.executeUpdate();
				if(affectedRows == 0) {
					throw new SQLException("編集に失敗しました" + match.toString());
				}
				
				conn.commit();
			}catch(SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("データベース操作中にエラーが発生しました", e);
		}
	}
}
