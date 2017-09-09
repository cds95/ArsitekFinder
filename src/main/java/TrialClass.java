import java.util.LinkedList;
import java.util.List;

import Data.DatabaseManager;
import Entities.Job;
import Entities.Tags;
import Entities.User;

// File Name SendEmail.java
public class TrialClass {
	
	public static void main(String[] args) {
		DatabaseManager manager = new DatabaseManager();
		List<Tags> tags = manager.getTags();
		for(Tags tag : tags) {
			System.out.println(tag.getSkill());
		}
	}
	
}