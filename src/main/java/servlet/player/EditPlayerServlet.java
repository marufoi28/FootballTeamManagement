package servlet.player;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.player.AddPlayer;
import model.player.Player;
import service.player.PlayerService;

public class EditPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PLAYER_FORM_JSP_PATH = "/jsp/player/playerForm.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Integer playerId = Integer.parseInt((String) request.getParameter("playerId"));
		
		PlayerService service = new PlayerService();
		Player player = service.searchPlayer(playerId);
		request.setAttribute("player", player);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PLAYER_FORM_JSP_PATH);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		AddPlayer editPlayer = AddPlayerServlet.createAddPlayer(request);
		AddPlayerServlet.saveRequest(request, editPlayer);
		List<String> errorMessages = AddPlayerServlet.checkValidationErrors(editPlayer);
		
		if (!errorMessages.isEmpty()) {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("errorMessages", errorMessages);
			
			/* デバッグ */
			for(String errorMessage : errorMessages) {
				System.out.println(errorMessage);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(PLAYER_FORM_JSP_PATH);
			dispatcher.forward(request, response);
			return;
		}
		PlayerService service = new PlayerService();
		service.editPlayer(editPlayer);
		
		response.sendRedirect(request.getContextPath() + "/SearchPlayerServlet");
	}
}
