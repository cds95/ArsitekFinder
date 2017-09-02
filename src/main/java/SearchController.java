

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.Job;
import Entities.User;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jobTitle = request.getParameter("title");
		String desc = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		String type = request.getParameter("type");
		Job job = createJob(jobTitle, desc, price, type);
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager(); // Takes care of case when this is first page user visits
		}
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
