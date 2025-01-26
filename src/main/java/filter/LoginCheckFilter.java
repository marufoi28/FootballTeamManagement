package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.account.Login;
import service.account.LoginService;

public class LoginCheckFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		
		LoginService loginLogic = new LoginService();
		boolean isLoggedIn = false;
		
		try {
		    if (session != null) {
		        if (session.getAttribute("login") != null) {
		            Login login = (Login) session.getAttribute("login");
		            isLoggedIn = loginLogic.execute(login); 
		        }

		        String path = httpRequest.getRequestURI();

		        if (isLoggedIn || path.startsWith(httpRequest.getContextPath() + "/LoginServlet") || path.startsWith(httpRequest.getContextPath() + "/AddAccountServlet") ) {
		            chain.doFilter(request, response); 
		            return; 
		        }

		        if (!path.startsWith(httpRequest.getContextPath() + "/LoginServlet")) {
	            	request.setAttribute("errorMessage", "ログインされていません");
	            	httpResponse.sendRedirect(httpRequest.getContextPath() + "/LoginServlet");
	        	}
		    }
		} catch (Exception e) {
		    e.printStackTrace(); 
		    httpResponse.sendRedirect("LoginServlet");
		}
	}
	
	@Override
	public void destroy() {
		//クリーンアップ処理が必要であればここに記述
	}

}
