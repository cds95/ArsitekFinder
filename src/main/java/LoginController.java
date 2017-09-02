import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Data.Security;
import Entities.User;

import org.json.simple.JSONObject;
/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager(); // Takes care of case when this is first page user visits
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		String correctPassword = manager.getUserPassword(username);
		if(correctPassword != null && Security.compare(password, correctPassword)) {
			User user = manager.getUser(username);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("manager", manager);
			response.getWriter().write("SUCCESS");
		} else {
			response.getWriter().write("FAIL");
		}
	}

}
