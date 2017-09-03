package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import Entities.User;

/**
 * Controller for home page
 * @author christopherdimitrisastropranoto
 *
 */
@Controller
@RequestMapping("/")
public class MainController {
	
	private DatabaseManager manager;
	private User sessionUser; //Current session user object
	
	public MainController() {
		this.manager = new DatabaseManager();
	}
	
	@RequestMapping("login")
	public String goToLogin() {
		return "login.jsp";
	}
	
	@RequestMapping("edit")
	public String goToEdit() {
		return "edit.jsp";
	}
	
	@RequestMapping("jobs")
	public ModelAndView goToJobs(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Job> jobs;
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
	public String goToPost() {
		return "post.jsp";
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
		String correctPassword = manager.getUserPassword(username);
		String res;
		if(correctPassword != null && Security.compare(password, correctPassword)) {
			User user = this.manager.getUser(username);
			this.setUser(user);
			session.setAttribute("user", this.sessionUser);
			session.setAttribute("manager", this.manager);
			res = "pass";
		} else {
			res = "fail";
		}
		return res;
	}
	
	@RequestMapping("profile")
	public ModelAndView showProfile(@RequestParam("handle") String handle) {
		User user = manager.getUser(handle);
		ModelAndView mv = new ModelAndView();
		mv.addObject("selectedUser", user);
		mv.setViewName("profile.jsp");
		return mv;
	}
	
	@RequestMapping("job")
	public ModelAndView getJobInfo(@RequestParam("jid") String jid) {
		int id = Integer.parseInt(jid);
		Job job = this.manager.getJob(id);
		List<Applicant> applicants = manager.getJobApplications(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("job", job);
		mv.addObject("applicants", applicants);
		mv.setViewName("job.jsp");
		return mv;
	}
	
	/**
	 * Sets a user
	 * @param user
	 */
	public void setUser(User user) {
		this.sessionUser = user;
	}
	
}
