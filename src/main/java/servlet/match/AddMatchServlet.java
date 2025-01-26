package servlet.match;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.field.Field;
import model.match.Match;
import model.opponent.Opponent;
import service.match.MatchService;

public class AddMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MATCH_FORM_JSP_PATH = "/jsp/match/matchForm.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("currentDate", LocalDate.now());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(MATCH_FORM_JSP_PATH);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Match match = createMatch(request);
		request.setAttribute("match", match);
		List<String> errorMessages = checkValidationErrors(match);
		
		if(!errorMessages.isEmpty()) {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("errorMessages", errorMessages);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(MATCH_FORM_JSP_PATH);
			dispatcher.forward(request, response);
			return;
		}
		
		MatchService service = new MatchService();
		service.addMatch(match);
		
		response.sendRedirect(request.getContextPath() + "/SearchMatchServlet");
	}
	
	private static List<String> checkValidationErrors(Match match){
		List<String> errorMessages = new ArrayList<>();
		return errorMessages;
		
	}
	
	public static Match createMatch(HttpServletRequest request) {
		Match match = new Match();
		
		Integer matchId = request.getParameter("matchId") != null && !request.getParameter("matchId").isEmpty() ? Integer.parseInt(request.getParameter("matchId")) : null;
		LocalDate eventDate = request.getParameter("eventDate") != null && !request.getParameter("eventDate").isEmpty() ? LocalDate.parse(request.getParameter("eventDate")) : null;
		Integer opponentId = request.getParameter("opponentId") != null && !request.getParameter("opponentId").isEmpty() ? Integer.parseInt(request.getParameter("opponentId")) : null;
		Integer fieldId = request.getParameter("fieldId") != null && !request.getParameter("fieldId").isEmpty() ? Integer.parseInt(request.getParameter("fieldId")) : null;
		LocalTime eventStartTime = request.getParameter("eventStartTime") != null && !request.getParameter("eventStartTime").isEmpty() ? LocalTime.parse(request.getParameter("eventStartTime")) : null;
		Integer ourScore = request.getParameter("ourScore") != null && !request.getParameter("ourScore").isEmpty() ? Integer.parseInt(request.getParameter("ourScore")) : 0;
		Integer opponentScore = request.getParameter("opponentScore") != null && !request.getParameter("opponentScore").isEmpty() ? Integer.parseInt(request.getParameter("opponentScore")) : 0;
		
		match.setMatchId(matchId);
		match.setEventDate(eventDate);
		match.setOpponent(new Opponent(opponentId));
		match.setField(new Field(fieldId));
		match.setEventStartTime(eventStartTime);
		match.setOurScore(ourScore);
		match.setOpponentScore(opponentScore);
		
		return match;
	}

}
