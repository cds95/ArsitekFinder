import java.io.IOException;
import java.util.List;

import Entities.Job;
import Entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
/**
 * Servlet implementation class FinderController
 */
public class FinderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager(); // Takes care of case when this is first page user visits
		}
		User user = (User) request.getSession().getAttribute("user");
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
		request.getSession().setAttribute("jobs", jobs);
		response.sendRedirect("jobs.jsp");
	}
}
