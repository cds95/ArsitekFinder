

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.Location;
import Entities.University;
import Entities.User;

/**
 * Servlet implementation class SaveInfoController
 */
public class SaveInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String email = request.getParameter("email");
		int eid = Integer.parseInt(request.getParameter("university"));
		String phone = request.getParameter("phone");
		User user = (User) request.getSession().getAttribute("user");
		University uni = manager.getUniversity(eid);
		try {
			int gradYear = Integer.parseInt(request.getParameter("graduation"));
			if(gradYear > 3000 || gradYear < 1950) {
				response.getWriter().write("fail");
			}
			manager.updateUserInformation(user, first, last, email, phone);
			manager.setUserUniversity(user, uni, gradYear);
		} catch(NumberFormatException e) {
			response.getWriter().write("fail");
		}
		int locationId = Integer.parseInt(request.getParameter("location"));
		Location location = manager.getLocation(locationId);
		manager.setUserLocation(user, location);
		response.getWriter().write("success");
	}

}
