

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.User;

/**
 * Servlet implementation class ProfilLoaderController
 */
public class ProfilLoaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		int uid = Integer.parseInt(request.getParameter("userId"));
		User user = manager.getUser(uid);
		request.getSession().setAttribute("selectedUser", user);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
		response.sendRedirect("profile.jsp");
	}

}
