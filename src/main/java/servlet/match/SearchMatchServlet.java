package servlet.match;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.match.Match;
import service.match.MatchService;

public class SearchMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INDEX_JSP_PATH = "/jsp/match/index.jsp";
	private static final int MAX_MATCHES_PER_PAGE = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Boolean isPastMatches = "true".equals(request.getParameter("showPastMatches"));
		request.setAttribute("showPastMatches", isPastMatches);
		MatchService service = new MatchService();
		List<Match> matches = new ArrayList<>();
		
		try {
			matches = service.searchMatches(isPastMatches);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		
		if(matches.size() > 0) {
			request.setAttribute("totalPages", (int)Math.ceil(matches.size() / MAX_MATCHES_PER_PAGE) + 1);
			session.setAttribute("matches", matches);
		} else {
			session.setAttribute("matches", null);
			String errorMessage = "試合予定は取得できませんでした。";
			request.setAttribute("errorMessage", errorMessage);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX_JSP_PATH);
		dispatcher.forward(request, response);
	}

}
