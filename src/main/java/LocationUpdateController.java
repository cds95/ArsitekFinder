

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.Location;
import Entities.User;

/**
 * Servlet implementation class LocationUpdateController
 */
public class LocationUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loc = request.getParameter("location");
		String[] info = loc.split(",", 2);
		String city = info[0];
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		Location location = manager.getLocation(city);
		User user = (User) request.getSession().getAttribute("user");
		manager.setUserLocation(user, location);
	}

}
