

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DatabaseManager;
import Entities.Job;
import Entities.Tags;
import Entities.User;

/**
 * Servlet implementation class RemoveTagController
 */
public class RemoveTagController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		String target = request.getParameter("target");
		int tid = Integer.parseInt(request.getParameter("id"));
		Tags tag = manager.getTag(tid);
		if(target.equals("user")) {
			User user = (User) request.getSession().getAttribute("user");
			manager.deleteUserTag(user, tag);
		} else {
			int jid = Integer.parseInt(request.getParameter("jid"));
			Job job = manager.getJob(jid);
			manager.deleteJobTag(job, tag);
		}
	}

}
