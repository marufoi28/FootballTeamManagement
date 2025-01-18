package service.file;

import java.io.PrintWriter;
import java.util.List;

import dao.player.PlayerAddDAO;
import file.PlayerExportLogic;
import model.player.AddPlayer;
import model.player.Player;

public class FileService {
	PlayerAddDAO playerAddDAO = new PlayerAddDAO();
	PlayerExportLogic playerExportLogic = new PlayerExportLogic();
	
	public FileService() {
		this.playerAddDAO = new PlayerAddDAO();
		this.playerExportLogic = new PlayerExportLogic();
	}
	
	public void importCSVPlayers(List<AddPlayer> players) {
		
		for(AddPlayer player : players) {
			playerAddDAO.addPlayer(player);
		}
	}
	
	public void exportCSVPlayers(List<Player> players, PrintWriter writer) {
		playerExportLogic.exportCSVPlayers(players, writer);
	}
}
