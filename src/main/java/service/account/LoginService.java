package service.account;

import dao.account.AccountsDAO;
import model.account.Account;
import model.account.Login;

public class LoginService {
	public boolean execute(Login login) {
		AccountsDAO dao = new AccountsDAO();
		Account account = dao.findByLogin(login);
		return account != null;
	}
	
	public void AddAccount(Account account) {
		AccountsDAO dao = new AccountsDAO();
		dao.resisterUser(account);
	}
}
