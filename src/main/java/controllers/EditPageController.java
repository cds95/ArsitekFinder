package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Data.DatabaseManager;
import Data.Security;
import Entities.Location;
import Entities.University;
import Entities.User;

@Controller
@RequestMapping("/edit")
public class EditPageController {
	
	/**
	 * Updates user location when location is still not set
	 * @param session
	 * @param loc
	 */
	@RequestMapping(value="/updateloc", method = RequestMethod.POST)
	public void updateLocation(HttpSession session, @RequestParam("location") String loc) {
		String[] info = loc.split(",", 2);
		String city = info[0];
		DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
		Location location = manager.getLocation(city);
		User user = (User) session.getAttribute("user");
		manager.setUserLocation(user, location);
	}
	
	/**
	 * Saves the newly edited user information
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	public void saveInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
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
	
	/**
	 * Changes the user's password
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="change-password", method = RequestMethod.POST)
	public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
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
