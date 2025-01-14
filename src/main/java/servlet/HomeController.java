package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.position.PositionDAO;
import model.position.Position;

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INDEX_JSP_PATH = "/jsp/index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* アプリケーションスコープにポジションを設定 */
		/* TODO 適切な場所に移動 */
		PositionDAO positionDao = new PositionDAO();
		List<Position> positions = positionDao.getPositions();
		ServletContext application = this.getServletContext();
		application.setAttribute("positions", positions);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX_JSP_PATH);
		dispatcher.forward(request, response);
	}
}
