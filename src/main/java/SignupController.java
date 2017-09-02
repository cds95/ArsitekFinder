

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Data.DatabaseManager;
import Data.FileManager;
import Entities.Job;
import Entities.User;

/**
 * Servlet implementation class SignupController
 */
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseManager manager = (DatabaseManager) request.getSession().getAttribute("manager");
		if(manager == null) {
			manager = new DatabaseManager(); // Takes care of case when this is first page user visits
		}
		String fileServerLoc = FileManager.WORKSAMPLEFILEPATH;
		String fileFolderPath = FileManager.SAVEDPORTPATH;
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		User user = (User) request.getSession().getAttribute("user");
		try {
			List<FileItem> files = sfu.parseRequest(request);
			FileItem f1 = files.get(0);
			int jid = Integer.parseInt(f1.getFieldName());
			FileItem file = files.get(1);
			String fileName = file.getName();
			if(FileManager.containsSpace(fileName)) {
				fileName = FileManager.formatter(fileName);
			}
			fileFolderPath += fileName;
			fileServerLoc += fileName;
			file.write(new File(fileServerLoc));
			Job job = manager.getJob(jid);
			manager.registerUser(user, job, fileFolderPath);
			response.sendRedirect("applyConfirm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
}

