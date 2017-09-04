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

@Controller
public class JobsController {
	
	/**
	 * Filters the searched jobs according to the category picked
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("jobs/filter")
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
	
	/**
	 * Adds a tag to a job
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="job/add", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void addJobSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		JSONObject obj = new JSONObject();
		response.setContentType("application/json");
		String res = request.getParameter("jid");
		System.out.println(res);
		int jid = Integer.parseInt(request.getParameter("jid"));
		Job job = manager.getJob(jid);
		PrintWriter writer = response.getWriter();
		String skill = request.getParameter("skill");
		Tags tag = manager.getTag(skill);
		if(job.getTags().size() < 5) {
			manager.addJobSkill(job, tag);
			obj.put("status", "success");
			obj.put("tid", tag.getTid());
		} else if(job.containsSkill(tag)){
			obj.put("status", "contains");
		} else {
			obj.put("status", "fail");
		}
		writer.print(obj);
	}
	
	/**
	 * Removes a job's tag
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public void removeJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		int jid = Integer.parseInt(request.getParameter("jid"));
		String type = request.getParameter("type");
		Job job = manager.getJob(jid);
		if(type.equals("open")) {
			manager.openJob(job);
		} else {
			manager.closeJob(job);
		}
	}
	
	/**
	 * Removes a tag from the job
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="job/remove", method=RequestMethod.POST)
	public void removeJobTag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		int tid = Integer.parseInt(request.getParameter("id"));
		Tags tag = manager.getTag(tid);
		int jid = Integer.parseInt(request.getParameter("jid"));
		Job job = manager.getJob(jid);
		PrintWriter writer = response.getWriter();
		if(job.getTags().size() > 1) {
			manager.deleteJobTag(job, tag);
			writer.write("success");
		} else {
			writer.write("fail");
		}
	}
	
	/**
	 * Posts a job into the database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/postjob")
	public void postJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jobTitle = request.getParameter("title");
		String desc = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		String type = request.getParameter("type");
		Job job = createJob(jobTitle, desc, price, type);
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		User user = (User) request.getSession().getAttribute("user");
		manager.addJob(job, user);
		String skills = request.getParameter("skills");
		String[] skillSet = skills.split(",");
		if(skillSet.length > 0) {
			manager.setSkills(job, skillSet);
		}
	}
	
	
	/**
	 * Creates a new job entity
	 * @param jobTitle
	 * @param desc
	 * @param price
	 * @param type
	 * @return
	 */
	public static Job createJob(String jobTitle, String desc, int price, String type) {
		Job job = new Job();
		job.setJobTitle(jobTitle);
		job.setDescription(desc);
		job.setPrice(price);
		job.setType(type);
		job.setComplete(false);
		return job;
	}
}
