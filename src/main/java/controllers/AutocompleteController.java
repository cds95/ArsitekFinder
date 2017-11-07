package controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import Data.DatabaseManager;
import Entities.Tags;

@Controller
public class AutocompleteController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping("gettags")
	public void getTags(HttpSession session, HttpServletResponse response) throws IOException {
		DatabaseManager manager = DatabaseManager.getInstance();
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
