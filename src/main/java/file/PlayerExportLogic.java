package file;

import java.io.PrintWriter;
import java.util.List;

import model.player.Player;

public class PlayerExportLogic {
    public void exportCSVPlayers(List<Player> players, PrintWriter writer) {
    	writer.println("first_name,last_name,first_name_kana,last_name_kana,birth_date,position_id,uniform_number,has_license,is_student");
    	
    	for(Player player : players) {
    		writer.printf("%s,%s,%s,%s,%s,%d,%d,%d,%d%n",
    			    player.getFirstName(),
    			    player.getLastName(),
    			    player.getFirstNameKana(),
    			    player.getLastNameKana(),
    			    player.getBirthDate(),
    			    player.getPosition().getPositionId(),
    			    player.getUniformNumber(),
    			    player.isHasLicense() ? 1 : 0,
    			    player.isStudent() ? 1 : 0
    			);
    	}
    }
}
