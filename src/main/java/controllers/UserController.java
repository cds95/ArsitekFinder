package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Data.DatabaseManager;
import Data.Security;
import Entities.Applicant;
import Entities.Job;
import Entities.Tags;
import Entities.User;

/**
 * Defines all the user login, logout, and signup behavior
 * @author christopherdimitrisastropranoto
 *
 */
@Controller
public class UserController {
	
	/**
	 * Logs user out
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session)  {
		session.removeAttribute("user");
		return "index.jsp";
	}
	
	/**
	 * Registers a new user into the database
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="register", method = RequestMethod.POST)
	public void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
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
		response.getWriter().write("success");
	}
	/**
	 * Removes a user's skill
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="removeusertag", method = RequestMethod.POST)
	public void removeUserTag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		int tid = Integer.parseInt(request.getParameter("id"));
		Tags tag = manager.getTag(tid);
		User user = (User) request.getSession().getAttribute("user");
		manager.deleteUserTag(user, tag);
	}
	
	/**
	 * Adds user tags
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("addusertag")
	public void updateUserSkills(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		PrintWriter writer = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		String skill = request.getParameter("skill");
		Tags skills = manager.getTag(skill);
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		if(user.getSkills().size() <= 5) {
			manager.addUserSkill(user, skills);
			json.put("status", "success");
			json.put("tid", skills.getTid());
		} else if(user.containsTag(skills)){
			json.put("status", "contains");
		} else {
			json.put("status", "fail");
		}
		writer.print(json);
		writer.flush();
	}
}
