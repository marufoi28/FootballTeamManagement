package servlet.match;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.field.FieldDAO;
import dao.opponent.OpponentDAO;
import model.field.Field;
import model.opponent.Opponent;

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INDEX_JSP_PATH = "/jsp/match/index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = this.getServletContext();
		
		OpponentDAO opponentDao = new OpponentDAO();
		List<Opponent> opponents = opponentDao.getOpponents();
		application.setAttribute("opponents", opponents);
		
		FieldDAO fieldDao = new FieldDAO();
		List<Field> fields = fieldDao.getFields();
		application.setAttribute("fields", fields);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX_JSP_PATH);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
