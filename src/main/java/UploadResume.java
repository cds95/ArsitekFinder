
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import Data.DatabaseManager;
import Data.FileManager;
import Entities.Job;
import Entities.User;

/**
 * Servlet implementation class UploadResume
 */
public class UploadResume extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager();
		}
		User user = (User) request.getSession().getAttribute("user");
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		String filePath = FileManager.RESUMEFILEPATH;
		String savedFilePath = FileManager.SAVEDRESUMEPATH;
		PrintWriter writer = response.getWriter();
		JSONObject res = new JSONObject();
		String result;
		try {
			List<FileItem> files = sfu.parseRequest(request);
			FileItem file = files.get(0);
			String fileName = file.getName();
			if(FileManager.containsSpace(fileName)) {
				fileName = FileManager.formatter(fileName);
			}
			filePath += fileName;
			savedFilePath += fileName;
			file.write(new File(filePath));
			manager.updateUserResume(user, savedFilePath);
			response.sendRedirect("profile.jsp");
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		res.put("status", "fail");
	}

}
