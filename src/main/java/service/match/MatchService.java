package service.match;

import java.util.List;

import dao.match.MatchAddDAO;
import dao.match.MatchEditDAO;
import dao.match.MatchSearchDAO;
import model.match.Match;

public class MatchService {
	private MatchSearchDAO matchSearchDAO;
	private MatchAddDAO matchAddDAO;
	private MatchEditDAO matchEditDAO;
	
	public MatchService() {
		this.matchSearchDAO = new MatchSearchDAO();
		this.matchAddDAO = new MatchAddDAO();
		this.matchEditDAO = new MatchEditDAO();
	}
	
	public List<Match> searchMatches() throws Exception{
		return matchSearchDAO.getMatches();
	}
	
	public void addMatch(Match match) {
		matchAddDAO.matchAddDAO(match);
	}
	
	public Match searchMatch(int matchId) throws Exception {
		return matchSearchDAO.getMatch(matchId);
	}
	
	public void editMatch(Match match) {
		matchEditDAO.matchEditDAO(match);
	}
}
