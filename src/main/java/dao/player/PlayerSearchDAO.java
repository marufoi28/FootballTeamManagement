package dao.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.db.DatabaseConnection;
import model.player.Player;
import model.player.SearchPlayer;
import model.position.Position;
import util.JdbcUtil;
import util.SqlFileLoader;

public class PlayerSearchDAO {
	private static final int MAX_PLAYERS_PER_PAGE = 15;
	public static final String SQL_PLAYER = "sql/player";
	
	public List<Player> getPlayers(SearchPlayer searchPlayer){
		List<Player> players = new ArrayList<>();
		
		String sqlQuery = SqlFileLoader.getSqlQuery("select", SQL_PLAYER);
		String sqlQueryConditions = SqlFileLoader.getSqlQuery("selectConditions", SQL_PLAYER);
		StringBuilder sql = new StringBuilder(sqlQuery);
		sql.append(sqlQueryConditions);
		
		List<Object> params = buildWhereClause(sql, searchPlayer);
		buildOrderByClause(sql, params, searchPlayer);
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(sql.toString())){
			
			setParameters(pStmt, params);
			
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Player player = mapPlayer(rs);
				players.add(player);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	}
	
	public Player getPlayer(Integer playerId) {
		Player player = new Player();
		
		String sqlQuery = SqlFileLoader.getSqlQuery("select", SQL_PLAYER);
		String sqlQueryConditions = SqlFileLoader.getSqlQuery("selectConditions", SQL_PLAYER);
		StringBuilder sql = new StringBuilder(sqlQuery);
		sql.append(sqlQueryConditions);
		
		List<Object> params = Arrays.asList(playerId);
		sql.append(" AND player_id = ?");
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql.toString())){
				
				setParameters(pStmt, params);
				
				ResultSet rs = pStmt.executeQuery();
				if(rs.next()) player = mapPlayer(rs);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
		return player;
	}
	
	private Player mapPlayer(ResultSet rs) throws SQLException {
		Player player = new Player();
		player.setPlayerId(rs.getInt("player_id"));
		player.setFirstName(rs.getString("first_name"));
		player.setLastName(rs.getString("last_name"));
		player.setFirstNameKana(rs.getString("first_name_kana"));
		player.setLastNameKana(rs.getString("last_name_kana"));
		player.setBirthDate(rs.getDate("birth_date").toLocalDate());
		player.setPosition(new Position(rs.getInt("position_id"), rs.getString("position_name"), rs.getString("position_classification")));
		player.setUniformNumber(rs.getInt("uniform_number"));
		player.setHasLicense(rs.getBoolean("has_license"));
		player.setStudent(rs.getBoolean("is_student"));
		player.setRegisterDate(rs.getDate("created_at").toLocalDate());
		return player;
	}
	
	/*
	 * List<Object>を使用してパラメータをPreparedStatementに設定する場合、インデックスは1から始まる。
	 */
	private void setParameters(PreparedStatement pStmt, List<Object> params) throws SQLException {
		for(int i = 0; i < params.size(); i++) {
			Object param = params.get(i);
			if(param instanceof String) {
				pStmt.setString(i+1, (String) param);
			} else if (param instanceof Integer) {
				pStmt.setInt(i+1, (Integer) param);
			}
		}
	}
	
	private void buildOrderByClause(StringBuilder sql, List<Object> params, SearchPlayer searchPlayer) {
		if(searchPlayer.getSortColumn() != null && searchPlayer.getSortOrder() != null) {
			appendOrderBy(sql, searchPlayer);
			appendLimitOffset(sql, params, searchPlayer);
		}
	}
	
	private void appendLimitOffset(StringBuilder sql, List<Object> params, SearchPlayer searchPlayer) {
		Integer offset = (searchPlayer.getCurrentPage() - 1) * MAX_PLAYERS_PER_PAGE;
		sql.append(" LIMIT ? OFFSET ?");
		params.add(MAX_PLAYERS_PER_PAGE);
		params.add(offset);
	}
	
	private void appendOrderBy(StringBuilder sql, SearchPlayer searchPlayer) {
		sql.append(" ORDER BY ").append(searchPlayer.getSortColumn()).append(" ").append(searchPlayer.getSortOrder());
	}
	
	private List<Object> buildWhereClause(StringBuilder sql, SearchPlayer searchPlayer){
		List<Object> params = new ArrayList<>();
		
		if(searchPlayer.getPlayerId() > 0) {
			sql.append(" AND p.player_id = ?");
		}
		
		if(searchPlayer.getSearchName() != null && !searchPlayer.getSearchName().isEmpty()) {
			sql.append(" AND (first_name LIKE ? OR last_name LIKE ?)");
			params.add("%" + searchPlayer.getSearchName() + "%");
			params.add("%" + searchPlayer.getSearchName()+ "%");
		}
		
		if(searchPlayer.getPositionIdList().size() > 0) {
			sql.append(" AND p.position_id IN (");
			
			for(int i = 0; i < searchPlayer.getPositionIdList().size(); i++) {
				sql.append("?");
				if(i < searchPlayer.getPositionIdList().size() - 1) {
					sql.append(", ");
				}
				params.add(searchPlayer.getPositionIdList().get(i));
			}
			sql.append(")");
		}
		
		sql.append(" AND TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) BETWEEN ? AND ?");
		params.add(searchPlayer.getAgeAbove());
		params.add(searchPlayer.getAgeBelow());
		
		if(searchPlayer.isHasLicense() != null) {
			sql.append(" AND has_license = ?");
			Integer hasLicense = searchPlayer.isHasLicense() ? 1 : 0;
			params.add(hasLicense);
		}
		
		if(searchPlayer.isStudent() != null) {
			sql.append(" AND is_student = ?");
			Integer isStudent = searchPlayer.isStudent() ? 1 : 0;
			params.add(isStudent);
		}
		return params;
	}
}
