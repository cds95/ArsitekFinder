import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Applicant;
import Entities.Job;
import Entities.User;
import Data.DatabaseManager;

/**
 * Servlet implementation class JobController
 */
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager(); // Takes care of case when this is first page user visits
		}
		int jid = Integer.parseInt(request.getParameter("jid"));
		Job job = manager.getJob(jid);
		request.getSession().setAttribute("job", job);
		
		List<Applicant> applicants = manager.getJobApplications(jid);
		request.getSession().setAttribute("applicants", applicants);
		
		response.sendRedirect("job.jsp");
	}

}
