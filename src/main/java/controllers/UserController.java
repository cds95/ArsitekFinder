package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Data.DatabaseManager;
import Data.FileManager;
import Data.Security;
import Entities.Tags;
import Entities.User;

/**
 * Defines all the user login, logout, and signup behavior
 * 
 * @author christopherdimitrisastropranoto
 *
 */
@Controller
public class UserController {

	/**
	 * Logs user out
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "index.jsp";
	}

	/**
	 * Registers a new user into the database
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseManager manager = DatabaseManager.getInstance();
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String handle = request.getParameter("handle");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		if (!this.userExists(handle, manager)) {
			String hashedPassword = Security.generateHash(password);
			User user = this.createNewUser(first, last, handle, hashedPassword, email);
			manager.addUser(user);
			request.getSession().setAttribute("user", user);
			response.getWriter().write("success");
		} else {
			response.getWriter().write("fail");
		}
	}
	
	/**
	 * Creates a new user given the input information and returns the user object
	 * @param first
	 * @param last
	 * @param handle
	 * @param hashedPassword
	 * @param email
	 * @return
	 */
	private User createNewUser(String first, String last, String handle, String hashedPassword, String email) {
		User user = new User();
		user.setFirst(first);
		user.setLast(last);
		user.setHandle(handle);
		user.setPassword(hashedPassword);
		user.setEmail(email);user.setFirst(first);
		return user;
	}
	
	/**
	 * Checks if a user with the given handle exists in the system.  Returns true if
	 * user is present, and false otherwise
	 * @param handle
	 * @param manager
	 * @return
	 */
	private boolean userExists(String handle, DatabaseManager manager) {
		return manager.getUser(handle) != null;
	}

	/**
	 * Removes a user's skill
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "removeusertag", method = RequestMethod.POST)
	public void removeUserTag(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		int tid = Integer.parseInt(request.getParameter("id"));
		Tags tag = manager.getTag(tid);
		User user = (User) request.getSession().getAttribute("user");
		manager.deleteUserTag(user, tag);
	}

	/**
	 * Adds user tags
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("addusertag")
	public void updateUserSkills(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		PrintWriter writer = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		String skill = request.getParameter("skill");
		Tags skills = manager.getTag(skill);
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		if (user.getSkills().size() <= 5) {
			manager.addUserSkill(user, skills);
			json.put("status", "success");
			json.put("tid", skills.getTid());
		} else if (user.containsTag(skills)) {
			json.put("status", "contains");
		} else {
			json.put("status", "fail");
		}
		writer.print(json);
		writer.flush();
	}

	/**
	 * Takes care of Post request to upload user resume
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadresume", method = RequestMethod.POST)
	public ModelAndView updateUserResume(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		User user = (User) request.getSession().getAttribute("user");
		FileManager fileManager = new FileManager();
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> files = sfu.parseRequest(request);
		FileItem file = files.get(0);
		String name = fileManager.uploadToAzure(file);
		manager.setUserResume(user, name);
		List<Tags> tags = manager.getTags();
		ModelAndView mv = new ModelAndView();
		mv.addObject("tags", tags);
		mv.addObject("selectedUser", user);
		mv.setViewName("profile.jsp");
		return mv;
	}
}
