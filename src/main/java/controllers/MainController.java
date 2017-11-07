package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Data.DatabaseManager;
import Data.EmailManager;
import Data.Security;
import Entities.Applicant;
import Entities.Job;
import Entities.Tags;
import Entities.User;

/**
 * Controller for home page
 * @author christopherdimitrisastropranoto
 *
 */
@Controller
@RequestMapping("/")
public class MainController {
	
	public static final String WEBSITEEMAIL = System.getenv("WEBSITE_EMAIL");
	public static final String TARGETEMAIL = System.getenv("TARGET_EMAIL");
	private User sessionUser; //Current session user object
	
	@RequestMapping("login")
	public String goToLogin() {
		return "login.jsp";
	}
	
	@RequestMapping("edit")
	public String goToEdit() {
		return "edit.jsp";
	}
	
	@RequestMapping("contact")
	public String goToContact() {
		return "contact.jsp";
	}
	
	@RequestMapping("jobs")
	public ModelAndView goToJobs(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Job> jobs;
		DatabaseManager manager = DatabaseManager.getInstance();
		if(user == null) {
			jobs = manager.getOpenJobs();
		} else {
			String name = user.getHandle();
			if(name.equals("admin")) {
				jobs = manager.getJobs();
			} else {
				jobs = manager.getOpenJobs();
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jobs.jsp");
		mv.addObject("jobs", jobs);
		return mv;
	}
	
	@RequestMapping("post")
	public ModelAndView goToPost() {
		DatabaseManager manager = DatabaseManager.getInstance();
		List<Tags> tags = manager.getTags();
		ModelAndView mv = new ModelAndView();
		mv.addObject("tags", tags);
		mv.setViewName("post.jsp");
		return mv;
	}
	
	/**
	 * Attempts to log the user in by comparing the username and password filled in by the user and compares
	 * it against the database.  Returns "pass" if successful, and "fail" otherwise
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="logging", method = RequestMethod.POST)
	@ResponseBody
	public String logIn(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
		DatabaseManager manager = DatabaseManager.getInstance();
		String correctPassword = manager.getUserPassword(username);
		String res;
		if(correctPassword != null && Security.compare(password, correctPassword)) {
			User user = manager.getUser(username);
			this.setUser(user);
			session.setAttribute("user", this.sessionUser);
			session.setAttribute("manager", manager);
			res = "pass";
		} else {
			res = "fail";
		}
		return res;
	}
	
	@RequestMapping("profile")
	public ModelAndView showProfile(HttpSession session, @RequestParam("handle") String handle) {
		DatabaseManager manager = DatabaseManager.getInstance();
		List<Tags> tags = manager.getTags();
		User user = manager.getUser(handle);
		if(user == null) {
			user = (User) session.getAttribute("user");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("tags", tags);
		mv.addObject("selectedUser", user);
		mv.setViewName("profile.jsp");
		return mv;
	}
	
	@RequestMapping("job")
	public ModelAndView getJobInfo(@RequestParam("jid") String jid) {
		DatabaseManager manager = DatabaseManager.getInstance();
		List<Tags> tags = manager.getTags();
		int id = Integer.parseInt(jid);
		Job job = manager.getJob(id);
		List<Applicant> applicants = manager.getJobApplications(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("job", job);
		mv.addObject("tags", tags);
		mv.addObject("applicants", applicants);
		mv.setViewName("job.jsp");
		return mv;
	}
	
	@RequestMapping(value = "contactus", method = RequestMethod.POST)
	public void sendContact(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("subject") String subject, 
					@RequestParam("message") String message, HttpServletResponse response) {
		String msg = "From: " + email +  ": " + message;
		EmailManager.sendEmail(WEBSITEEMAIL, TARGETEMAIL, name + ": " + subject, msg);
		try {
			response.getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets a user
	 * @param user
	 */
	public void setUser(User user) {
		this.sessionUser = user;
	}
	
}
