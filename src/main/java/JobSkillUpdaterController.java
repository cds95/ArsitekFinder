

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Data.DatabaseManager;
import Entities.Job;
import Entities.Tags;

/**
 * Servlet implementation class JobSkillUpdaterController
 */
public class JobSkillUpdaterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
