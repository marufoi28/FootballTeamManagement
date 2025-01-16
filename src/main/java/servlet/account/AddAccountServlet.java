package servlet.account;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.account.Account;
import service.account.LoginService;

public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/account/create.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String mail = request.getParameter("mail");
		Integer age = Integer.parseInt(request.getParameter("age"));
		String userId = request.getParameter("userId");
		
		request.setAttribute("name",name );
		request.setAttribute("pass", pass);
		request.setAttribute("mail",mail);
		request.setAttribute("age", age);
		request.setAttribute("createUserId", userId);
		
		LoginService LoginService = new LoginService();
		Account account = new Account(userId, pass, mail,name, age);
		LoginService.AddAccount(account);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/account/index.jsp");
		dispatcher.forward(request, response);
	}
}
