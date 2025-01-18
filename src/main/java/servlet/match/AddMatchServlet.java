package servlet.match;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MATCH_FORM_JSP_PATH = "/jsp/match/matchForm.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("currentDate", LocalDate.now());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(MATCH_FORM_JSP_PATH);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
