package servlet.account;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.position.PositionDAO;
import model.account.Login;
import model.position.Position;
import service.account.LoginService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* アプリケーションスコープにポジションを設定 */
		PositionDAO positionDao = new PositionDAO();
		List<Position> positions = positionDao.getPositions();
		ServletContext application = this.getServletContext();
		application.setAttribute("positions", positions);
		
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/account/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		Login login = new Login(userId, pass);
		LoginService bo = new LoginService();
		boolean result = bo.execute(login);
		
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("login", login);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/player/index.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("LoginServlet");
		}
	}
}
