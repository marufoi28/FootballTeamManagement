package dao.position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.db.DatabaseConnection;
import model.position.Position;
import util.JdbcUtil;
import util.SqlFileLoader;

public class PositionDAO {
	public List<Position> getPositions(){
		List<Position> positions = new ArrayList<>();
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection()){
			String sql = SqlFileLoader.getSqlQuery("select", "sql/position");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Position position = new Position();
				position.setPositionId(rs.getInt("position_id"));
				position.setPositionName(rs.getString("position_name"));
				position.setPositionClassification(rs.getString("position_classification"));
				positions.add(position);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return positions;
	}
}
