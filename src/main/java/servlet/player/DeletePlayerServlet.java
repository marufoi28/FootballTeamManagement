package servlet.player;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.player.PlayerService;

public class DeletePlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Integer playerId = Integer.parseInt((String) request.getParameter("playerId"));
		
		PlayerService service = new PlayerService();
		service.deletePlayer(playerId);
		
		response.sendRedirect(request.getContextPath() + "/SearchPlayerServlet");
	}

}
