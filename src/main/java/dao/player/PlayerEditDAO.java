package dao.player;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.db.DatabaseConnection;
import model.player.AddPlayer;
import util.JdbcUtil;
import util.SqlFileLoader;

public class PlayerEditDAO {
	public static final String SQL_PLAYER = "sql/player";

	public void editPlayer(AddPlayer addPlayer) {
		String sql = SqlFileLoader.getSqlQuery("update", SQL_PLAYER);

		JdbcUtil.loadJDBCDriver();

		try (Connection conn = DatabaseConnection.getConnection();) {

			conn.setAutoCommit(false);

			try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
				pStmt.setString(1, addPlayer.getFirstName());
				pStmt.setString(2, addPlayer.getLastName());
				pStmt.setString(3, addPlayer.getFirstNameKana());
				pStmt.setString(4, addPlayer.getLastNameKana());
				pStmt.setDate(5, Date.valueOf(addPlayer.getBirthDate()));
				pStmt.setInt(6, addPlayer.getPositionId());
				pStmt.setInt(7, addPlayer.getUniformNumber());
				pStmt.setBoolean(8, addPlayer.getHasLicense());
				pStmt.setBoolean(9, addPlayer.getIsStudent());
				pStmt.setInt(10, addPlayer.getPlayerId());

				int afftectedRows = pStmt.executeUpdate();
				if (afftectedRows == 0) {
					throw new SQLException("編集に失敗しました" + addPlayer.toString());
				}

				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("データベース操作中にエラーが発生しました", e);
		}
	}
}
