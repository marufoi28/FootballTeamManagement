package dao.opponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.db.DatabaseConnection;
import model.opponent.Opponent;
import util.JdbcUtil;
import util.SqlFileLoader;

public class OpponentDAO {
	public List<Opponent> getOpponents(){
		List<Opponent> opponents = new ArrayList<>();
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection()){
			String sql = SqlFileLoader.getSqlQuery("select", "sql/opponent");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Opponent opponent = new Opponent();
				opponent.setOpponentId(rs.getInt("opponent_id"));
				opponent.setOpponentName(rs.getString("opponent_name"));
				opponents.add(opponent);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return opponents;
	}
}
