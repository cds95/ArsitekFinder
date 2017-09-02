

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.User;

/**
 * Servlet implementation class PhoneController
 */
public class PhoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		User user = (User) request.getSession().getAttribute("user");
		String phoneNumber = request.getParameter("number");
		System.out.println(phoneNumber);
		manager.editUserPhoneNumber(user, phoneNumber);
	}

}
