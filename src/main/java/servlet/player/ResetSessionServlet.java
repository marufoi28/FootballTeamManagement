package servlet.player;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ResetSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.removeAttribute("searchPlayerId");
		session.removeAttribute("searchName");
		session.removeAttribute("searchPositionIdList");
		session.removeAttribute("searchAgeAbove");
		session.removeAttribute("searchAgeBelow");
		session.removeAttribute("searchHasLicense");
		session.removeAttribute("searchIsStudent");
		session.removeAttribute("sortColumn");
		session.removeAttribute("sortOrder");
		session.removeAttribute("currentPage");
		
		// JSONレスポンスを準備
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        out.print("{\"success\": true, \"message\": \"検索条件がリセットされました\"}");
        out.flush();
	}
}
