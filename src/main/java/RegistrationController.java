import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Data.Security;
import Entities.User;

/**
 * Servlet implementation class RegistrationController
 */
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SALT = "arsitek";
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager(); // Takes care of case when this is first page user visits
		}
		User user = new User();
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String handle = request.getParameter("handle");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		String hashedPassword = Security.generateHash(password);
		
		user.setFirst(first);
		user.setLast(last);
		user.setHandle(handle);
		user.setPassword(hashedPassword);
		user.setEmail(email);
		manager.addUser(user);
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("selectedUser", user);
		response.sendRedirect("profile.jsp");
	}
	
	

}
