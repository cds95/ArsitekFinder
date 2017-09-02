package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("login")
	public String goToLogin() {
		return "login.jsp";
	}
	
	@RequestMapping("jobs")
	public String goToJobs() {
		return "jobs.jsp";
	}
	
	@RequestMapping("post")
	public String goToPost() {
		return "post.jsp";
	}
	
}
