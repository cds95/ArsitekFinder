

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.Job;

/**
 * Servlet implementation class RemoveJobController
 */
public class RemoveJobController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
