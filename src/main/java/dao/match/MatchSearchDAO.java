package dao.match;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dao.db.DatabaseConnection;
import model.field.Field;
import model.match.Match;
import model.opponent.Opponent;
import service.match.WeatherService;
import util.JdbcUtil;
import util.SqlFileLoader;

public class MatchSearchDAO {
	public static final String SQL_MATCH = "sql/match";
	
	public List<Match> getMatches(Boolean isPastMatches) throws Exception{
		List<Match> matches = new ArrayList<>();
		
		String sqlQuery;
		if(isPastMatches) {
			sqlQuery = SqlFileLoader.getSqlQuery("selectContainPastMatches", SQL_MATCH);
		} else {
			sqlQuery = SqlFileLoader.getSqlQuery("select", SQL_MATCH);
		}
		
		StringBuilder sql = new StringBuilder(sqlQuery);
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql.toString())){
				
				ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Match match = mapMatch(rs);
				matches.add(match);
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
			return matches;
	}
	
	private Match mapMatch(ResultSet rs) throws Exception {
		Match match = new Match();
		WeatherService service = new WeatherService();
		match.setMatchId(rs.getInt("match_id"));
		match.setField(new Field(rs.getInt("field_id"), rs.getString("field_name")));
		match.setEventDate(LocalDate.of(rs.getInt("year"),rs.getInt("month"),rs.getInt("day")));
		match.setEventStartTime(LocalTime.of(rs.getInt("hour"), rs.getInt("minute")));
		match.setRegisterDate(LocalDate.now());
		match.setWeather(service.getWeather(rs.getString("prefecture_en") + "," + rs.getString("locale"), LocalDate.parse(rs.getString("event_date"))));
		match.setOpponent(new Opponent(rs.getInt("opponent_id"), rs.getString("opponent_name")));
		match.setOurScore(rs.getInt("our_score"));
		match.setOpponentScore(rs.getInt("opponent_score"));
		return match;
	}
	
	public Match getMatch(int matchId) throws Exception {
		Match match = new Match();
		String sqlQuery = SqlFileLoader.getSqlQuery("selectMatch", SQL_MATCH);
		StringBuilder sql = new StringBuilder(sqlQuery);
		
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(sql.toString())){	
			pStmt.setInt(1, matchId);
				
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				match = mapMatch(rs);
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return match;
	}
}
