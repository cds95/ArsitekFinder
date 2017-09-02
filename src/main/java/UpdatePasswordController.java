

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Data.Security;
import Entities.User;

/**
 * Servlet implementation class UpdatePasswordController
 */
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		String oldPassword = request.getParameter("oldPassword");
		User user = (User) request.getSession().getAttribute("user");
		String hashedPassword = user.getPassword();
		if(Security.compare(oldPassword, hashedPassword)) {
			String newPass = request.getParameter("newPassword");
			String newHashed = Security.generateHash(newPass);
			manager.saveUserPassword(user, newHashed);
			writer.write("success");
		} else {
			writer.write("fail");
		}
	}

}
