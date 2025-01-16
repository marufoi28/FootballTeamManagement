package servlet.player;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.player.AddPlayer;
import model.player.Player;
import model.position.Position;
import service.player.PlayerService;

public class AddPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PLAYER_FORM_JSP_PATH = "/jsp/player/playerForm.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PLAYER_FORM_JSP_PATH);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			AddPlayer addPlayer = createAddPlayer(request);
			saveRequest(request, addPlayer);
			List<String> errorMessages = checkValidationErrors(addPlayer);
			
			if(!errorMessages.isEmpty()) {
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("errorMessages", errorMessages);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(PLAYER_FORM_JSP_PATH);
				dispatcher.forward(request, response);
				return;
			}
			
			PlayerService service = new PlayerService();
			service.addPlayer(addPlayer);
			
			response.sendRedirect(request.getContextPath() + "/SearchPlayerServlet");
	}
	
	public static void saveRequest(HttpServletRequest request, AddPlayer addPlayer) {
		Player player = new Player();
		player.setPlayerId(addPlayer.getPlayerId());
		player.setFirstName(addPlayer.getFirstName());
		player.setFirstNameKana(addPlayer.getFirstNameKana());
		player.setLastName(addPlayer.getLastName());
		player.setLastNameKana(addPlayer.getLastNameKana());
		player.setBirthDate(addPlayer.getBirthDate());
		player.setPosition(new Position(addPlayer.getPositionId()));
		player.setUniformNumber(addPlayer.getUniformNumber());
		player.setHasLicense(addPlayer.getIsStudent());
		player.setStudent(addPlayer.getIsStudent());
		request.setAttribute("player", player);
	}
	
	public static List<String> checkValidationErrors(AddPlayer addPlayer) {
		
		List<String> errorMessages = new ArrayList<>();
		
		if(addPlayer.getFirstName() == null || addPlayer.getFirstName().trim().isEmpty()) {
			errorMessages.add("名前（姓）を入力してください。");
		} else if (addPlayer.getFirstName().length() > 50) {
			errorMessages.add("名前（姓）は50文字以内で入力してください。");
		}
		
	    if (addPlayer.getLastName() == null || addPlayer.getLastName().trim().isEmpty()) {
	        errorMessages.add("名前（名）を入力してください。");
	    } else if (addPlayer.getLastName().length() > 50) {
	        errorMessages.add("名前（名）は50文字以内で入力してください。");
	    }

	    if (addPlayer.getFirstNameKana() == null || addPlayer.getFirstNameKana().trim().isEmpty()) {
	        errorMessages.add("名前（姓カナ）を入力してください。");
	    } else if (!addPlayer.getFirstNameKana().matches("^[ァ-ヶー]+$")) {
	        errorMessages.add("名前（姓カナ）はカタカナで入力してください。");
	    }

	    if (addPlayer.getLastNameKana() == null || addPlayer.getLastNameKana().trim().isEmpty()) {
	        errorMessages.add("名前（名カナ）を入力してください。");
	    } else if (!addPlayer.getLastNameKana().matches("^[ァ-ヶー]+$")) {
	        errorMessages.add("名前（名カナ）はカタカナで入力してください。");
	    }

	    if (addPlayer.getBirthDate() == null) {
	        errorMessages.add("生年月日を入力してください。");
	    } else {
	        try {
	            if (addPlayer.getBirthDate().isAfter(LocalDate.now())) {
	                errorMessages.add("生年月日は現在の日付より前の日付を入力してください。");
	            }
	        } catch (DateTimeParseException e) {
	            errorMessages.add("生年月日を正しい形式（YYYY-MM-DD）で入力してください。");
	        }
	    }

	    try {
		    if (addPlayer.getPositionId() < 1 || addPlayer.getPositionId() > 12) {
		        errorMessages.add("ポジションは1から12の範囲で指定してください。");
		    }
		} catch (NumberFormatException e) {
		    errorMessages.add("ポジションは数値で入力してください。");
		}

	    try {
		    if (addPlayer.getUniformNumber() < 0 || addPlayer.getUniformNumber() > 99) {
		        errorMessages.add("背番号は0から99の範囲で指定してください。");
		    }
		} catch (NumberFormatException e) {
		    errorMessages.add("背番号は数値で入力してください。");
		}

	    try {
		    if (addPlayer.getHasLicense() == null) {
		        errorMessages.add("審判免許の有無を選択してください。");
		    }
		} catch (Exception e) {
		    errorMessages.add("予期しないエラーが発生しました。");
		}
	    
	    try {
		    if (addPlayer.getIsStudent() == null) {
		        errorMessages.add("学生である場合は「対象」、そうでない場合は「非対象」を選択してください。");
		    }
		} catch (Exception e) {
		    errorMessages.add("予期しないエラーが発生しました。");
		}
		return errorMessages;
	}
	
	public static AddPlayer createAddPlayer(HttpServletRequest request) {
		AddPlayer addPlayer = new AddPlayer();
		
		Integer playerId = request.getParameter("playerId") != null && !request.getParameter("playerId").isEmpty() ? Integer.parseInt(request.getParameter("playerId")) : null;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String firstNameKana = request.getParameter("firstNameKana");
		String lastNameKana = request.getParameter("lastNameKana");
		LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
		Integer positionId = Integer.parseInt(request.getParameter("positionId"));
		Integer uniformNumber = Integer.parseInt(request.getParameter("uniformNumber"));
		
		String requestHasLicense = request.getParameter("hasLicense");
		String requestIsStudent = request.getParameter("isStudent");
		Boolean hasLicense = requestHasLicense != null && !requestHasLicense.isEmpty() ? "1".equals(requestHasLicense) : null;
		Boolean IsStudent = requestIsStudent != null && !requestIsStudent.isEmpty() ? "1".equals(requestIsStudent) : null;
		
		addPlayer.setPlayerId(playerId);
		addPlayer.setFirstName(firstName);
		addPlayer.setLastName(lastName);
		addPlayer.setFirstNameKana(firstNameKana);
		addPlayer.setLastNameKana(lastNameKana);
		addPlayer.setBirthDate(birthDate);
		addPlayer.setPositionId(positionId);
		addPlayer.setUniformNumber(uniformNumber);
		addPlayer.setHasLicense(hasLicense);
		addPlayer.setIsStudent(IsStudent);
		
		return addPlayer;
	}
}
