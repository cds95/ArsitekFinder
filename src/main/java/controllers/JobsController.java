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

@Controller
@RequestMapping("jobs")
public class JobsController {
	
	@RequestMapping("filter")
	public void filter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager(); // Takes care of case when this is first page user visits
		}
		String type = request.getParameter("type");
		List<Job> jobs;
		if(type.equals("All Jobs")) {
			jobs = manager.getOpenJobs();
		} else if(type.equals("My Jobs")) {
			User user = (User) request.getSession().getAttribute("user");
			String handle = user.getHandle();
			jobs = manager.getAllUserJobs(handle);
		} else {
			jobs = manager.getJobByType(type, false);
		}
		request.getSession().setAttribute("jobs", jobs);
	}
}
