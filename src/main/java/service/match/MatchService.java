package service.match;

import java.util.List;

import dao.match.MatchSearchDAO;
import model.match.Match;

public class MatchService {
	private MatchSearchDAO matchSearchDAO;
	
	public MatchService() {
		this.matchSearchDAO = new MatchSearchDAO();
	}
	
	public List<Match> searchMatches() throws Exception{
		return matchSearchDAO.getMatches();
	}
}
