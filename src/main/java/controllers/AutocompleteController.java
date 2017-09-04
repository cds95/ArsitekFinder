package controllers;

import java.io.IOException;
import java.util.LinkedList;
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
public class AutocompleteController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping("gettags")
	public void getTags(HttpSession session, HttpServletResponse response) throws IOException {
		DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager();
		}
		List<Tags> tags = manager.getTags();
		List<String> tagNames = new LinkedList<String>();
		for(Tags tag : tags) {
			tagNames.add(tag.getSkill());
		}
		response.setContentType("application/json");
		JSONObject obj = new JSONObject();
		obj.put("tags", tagNames);
		response.getWriter().print(obj);
		
	}
}
