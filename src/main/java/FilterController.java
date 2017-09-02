

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.Job;
import Entities.User;

/**
 * Servlet implementation class FilterController
 * Servlet responsible for filtering the jobs according to their type
 */
public class FilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
