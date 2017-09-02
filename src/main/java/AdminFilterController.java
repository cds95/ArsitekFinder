

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.Applicant;
import Entities.Job;

/**
 * Servlet implementation class AdminFilterController
 */
public class AdminFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager();
		}
		int jid = Integer.parseInt(request.getParameter("jid"));
		Job job = manager.getJob(jid);
		List<Applicant> apps = manager.getJobApplications(jid);
		request.getSession().setAttribute("apps", apps);
		request.getSession().setAttribute("job", job);
		response.sendRedirect("admin.jsp");
	}

}
