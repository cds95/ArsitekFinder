

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Data.DatabaseManager;
import Entities.Tags;
import Entities.User;

/**
 * Servlet implementation class UpdateUserSkillsController
 */
public class UpdateUserSkillsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		PrintWriter writer = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		String skill = request.getParameter("skill");
		Tags skills = manager.getTag(skill);
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		if(user.getSkills().size() <= 5) {
			manager.addUserSkill(user, skills);
			json.put("status", "success");
			json.put("tid", skills.getTid());
		} else if(user.containsTag(skills)){
			json.put("status", "contains");
		} else {
			json.put("status", "fail");
		}
		writer.print(json);
		writer.flush();
	}
}
