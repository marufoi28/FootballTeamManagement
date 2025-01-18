package servlet.file;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.player.Player;
import model.player.SearchPlayer;
import service.file.FileService;
import service.player.PlayerService;
import servlet.player.SearchPlayerServlet;

public class ExportCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/csv; charset=UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename=\"players.csv\"");
	    
	    HttpSession session = request.getSession();
	    request.setCharacterEncoding("UTF-8");
	    
	    try {
	    	
			/* 検索条件を作成 */
			SearchPlayer searchPlayer = SearchPlayerServlet.createSearchPlayer(request, session);
			
			/* 検索条件をセッションに保存 */
			SearchPlayerServlet.saveToSession(session, searchPlayer);
			
			/* 検索処理を実行 */
			PlayerService service = new PlayerService();
			List<Player> players = service.searchPlayers(searchPlayer);
			
			if(players.size() > 0) {
				FileService fileService = new FileService();
				fileService.exportCSVPlayers(players, response.getWriter());
			} else {
				String errorMessage = "選手は取得できませんでした。";
				session.setAttribute("errorMessage", errorMessage);
			}
	    } catch(Exception e) {
	    	throw new ServletException("エラーが発生しました", e);
	    }
	}
}
