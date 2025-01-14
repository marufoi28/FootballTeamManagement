package service.player;

import java.util.List;

import dao.player.PlayerAddDAO;
import dao.player.PlayerDeleteDAO;
import dao.player.PlayerEditDAO;
import dao.player.PlayerSearchDAO;
import model.player.AddPlayer;
import model.player.Player;
import model.player.SearchPlayer;

public class PlayerService {
	private PlayerSearchDAO playerSearchDAO;
	private PlayerAddDAO playerAddDAO;
	private PlayerEditDAO playerEditDAO;
	private PlayerDeleteDAO playerDeleteDAO;
	
	public PlayerService() {
		this.playerSearchDAO = new PlayerSearchDAO();
		this.playerAddDAO = new PlayerAddDAO();
		this.playerEditDAO = new PlayerEditDAO();
		this.playerDeleteDAO = new PlayerDeleteDAO();
	}
	
	public List<Player> searchPlayers(SearchPlayer searchPlayer){
		return playerSearchDAO.getPlayers(searchPlayer);
	}
	
	public Player searchPlayer(int playerId) {
		return playerSearchDAO.getPlayer(playerId);
	}
	
	public void addPlayer(AddPlayer addPlayer) {
		playerAddDAO.addPlayer(addPlayer);
	}
	
	public void editPlayer(AddPlayer editPlayer) {
		playerEditDAO.editPlayer(editPlayer);
	}
	
	public void deletePlayer(int playerId) {
		playerDeleteDAO.deletePlayer(playerId);
	}
}
