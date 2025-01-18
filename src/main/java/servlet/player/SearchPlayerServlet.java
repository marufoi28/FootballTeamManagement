package servlet.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.player.Player;
import model.player.SearchPlayer;
import service.player.PlayerService;

public class SearchPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_PLAYERS_PER_PAGE = 15;
	private static final String INDEX_JSP_PATH = "/jsp/player/index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		/* 検索条件を作成 */
		SearchPlayer searchPlayer = createSearchPlayer(request, session);
		
		/* 検索条件をセッションに保存 */
		saveToSession(session, searchPlayer);
		
		/* 検索処理を実行 */
		PlayerService service = new PlayerService();
		List<Player> players = service.searchPlayers(searchPlayer);
		
		if(players.size() > 0) {
			request.setAttribute("totalPages", (int)Math.ceil(players.size() / MAX_PLAYERS_PER_PAGE) + 1);
			session.setAttribute("players", players);
		} else {
			session.setAttribute("players", null);
			String errorMessage = "選手は取得できませんでした。";
			request.setAttribute("errorMessage", errorMessage);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX_JSP_PATH);
		dispatcher.forward(request, response);
	}
	
	public static void saveToSession(HttpSession session,SearchPlayer searchPlayer) {
		
		session.setAttribute("searchPlayerId", searchPlayer.getPlayerId());
		session.setAttribute("searchName", searchPlayer.getSearchName());
		session.setAttribute("searchPositionIdList", searchPlayer.getPositionIdList());
		session.setAttribute("searchAgeAbove", searchPlayer.getAgeAbove());
		session.setAttribute("searchAgeBelow", searchPlayer.getAgeBelow());
		session.setAttribute("searchHasLicense", searchPlayer.isHasLicense());
		session.setAttribute("searchIsStudent", searchPlayer.isStudent());
		session.setAttribute("sortColumn", searchPlayer.getSortColumn());
		session.setAttribute("sortOrder", searchPlayer.getSortOrder());
		session.setAttribute("currentPage", searchPlayer.getCurrentPage());
	}
	
	public static SearchPlayer createSearchPlayer(HttpServletRequest request,HttpSession session) {
		
		/* リクエストパラメータの取得 */
		String requestPlayerId = request.getParameter("searchPlayerId");
		String requestSearchName = request.getParameter("searchName");
		String[] requestPositionIdArray = request.getParameterValues("searchPositionIdList");
		List<String> requestPositionIdList = requestPositionIdArray != null ? Arrays.asList(requestPositionIdArray) : new ArrayList<>();
		String requestAgeAbove = request.getParameter("searchAgeAbove");
		String requestAgeBelow = request.getParameter("searchAgeBelow");
		String requestHasLicenseTemp = request.getParameter("searchHasLicense");
		String requestIsStudentTemp = request.getParameter("searchIsStudent");

		//NULLの場合はそのまま、それ以外の場合はBoolean型にキャスト
		Boolean requestHasLicense = requestHasLicenseTemp != null && !requestHasLicenseTemp.isEmpty() ? "1".equals(requestHasLicenseTemp) : null;
		Boolean requestIsStudent = requestIsStudentTemp != null && !requestIsStudentTemp.isEmpty() ? "1".equals(requestIsStudentTemp) : null;
		
		if(requestHasLicense == null) session.removeAttribute("searchHasLicense");
		if(requestIsStudent == null) session.removeAttribute("searchIsStudent");
		
		String requestSortColumn = request.getParameter("sortColumn");
		String requestSortOrder = request.getParameter("sortOrder");
		String requestCurrentPage = request.getParameter("currentPage");
		
		/* セッション変数から値を取得 */
		Integer sessionPlayerId = (Integer)session.getAttribute("searchPlayerId");
		String sessionSearchName = (String)session.getAttribute("searchPlayerName");
		Object attribute = session.getAttribute("searchPositionIdList");
		String[] sessionPositionIdArray = attribute instanceof String[] ? (String[])attribute : new String[0];
		List<String> sessionPositionIdList = sessionPositionIdArray != null ? Arrays.asList(sessionPositionIdArray) : new ArrayList<>();
		Integer sessionAgeAbove = (Integer)session.getAttribute("searchAgeAbove");
		Integer sessionAgeBelow = (Integer)session.getAttribute("searchAgeBelow");
		Boolean sessionHasLicense = session.getAttribute("searchHasLicense") != null ? (Boolean)session.getAttribute("searchHasLicense") : null;
		Boolean sessionIsStudent = session.getAttribute("searchIsStudent") != null ? (Boolean)session.getAttribute("searchIsStudent") : null;
		
		String sessionSortColumn = (String)session.getAttribute("sortColumn");
		String sessionSortOrder = (String)session.getAttribute("sortOrder");
		Integer sessionCurrentPage = (Integer)session.getAttribute("currentPage");
		
		/* 
		 * リクエストパラメータがNULLだった場合、セッション変数の値を代入。
		 * リクエストパラメータの値が入っていた場合、リクエストパラメータの値を代入。
		 */
		Integer playerId = requestPlayerId != null ? Integer.parseInt(requestPlayerId) : Optional.ofNullable(sessionPlayerId).orElse(0);
		String searchName = requestSearchName != null ? requestSearchName : sessionSearchName;
		List<Integer> searchPositionIdList = !requestPositionIdList.isEmpty() ? getPositionIdList(requestPositionIdList) : getPositionIdList(sessionPositionIdList);
		Integer searchAgeAbove = requestAgeAbove != null && !requestAgeAbove.isEmpty() ? Integer.parseInt(requestAgeAbove) : Optional.ofNullable(sessionAgeAbove).orElse(0);
		Integer searchAgeBelow = requestAgeBelow != null && !requestAgeBelow.isEmpty() ? Integer.parseInt(requestAgeBelow) : Optional.ofNullable(sessionAgeBelow).orElse(100);
		Boolean searchHasLicense = requestHasLicense != null ? requestHasLicense : sessionHasLicense;
		Boolean searchIsStudent = requestIsStudent != null ? requestIsStudent : sessionIsStudent;
		String sortColumn = requestSortColumn != null ? requestSortColumn : sessionSortColumn;
		String sortOrder = requestSortOrder != null ? requestSortOrder : sessionSortOrder;
		Integer currentPage = requestCurrentPage != null ? Integer.parseInt(requestCurrentPage) : Optional.ofNullable(sessionCurrentPage).orElse(0);
		
		SearchPlayer searchPlayer = new SearchPlayer(playerId, searchName, searchPositionIdList,searchAgeAbove, searchAgeBelow, searchHasLicense, searchIsStudent, sortColumn, sortOrder, currentPage);
		
		return searchPlayer;
	}
	
	private static List<Integer> getPositionIdList(List<String> positionIdListStr){
		List<Integer> positionIdList = new ArrayList<>();
		for(String positionId : positionIdListStr) {
			positionIdList.add(Integer.parseInt(positionId));
		}
		return positionIdList;
	}
}
