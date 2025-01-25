package servlet.match;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.match.Match;
import service.match.MatchService;

public class EditMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MATCH_FORM_JSP_PATH = "/jsp/match/matchForm.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer matchId = Integer.parseInt((String) request.getParameter("matchId"));
		
		MatchService service = new MatchService();
		try {
			Match match = service.searchMatch(matchId);
			request.setAttribute("match", match);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(MATCH_FORM_JSP_PATH);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Match editMatch = AddMatchServlet.createMatch(request);
		request.setAttribute("match", editMatch);
		List<String> errorMessages = new ArrayList<>();
//				AddMatchServlet.checkValidationErrors(editMatch);
		
		if (!errorMessages.isEmpty()) {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("errorMessages", errorMessages);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(MATCH_FORM_JSP_PATH);
			dispatcher.forward(request, response);
			return;
		}
		MatchService service = new MatchService();
		service.editMatch(editMatch);
		
		response.sendRedirect(request.getContextPath() + "/SearchMatchServlet");
	}
}
