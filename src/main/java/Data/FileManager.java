package Data;

/**
 * Sets the file settings, and destinations of the various files to be saved in the system
 * @author christopherdimitrisastropranoto
 *
 */
public class FileManager {
	public static final String WORKSAMPLEFILEPATH = "/Users/christopherdimitrisastropranoto/Documents/workspace/Freelance/src/main/webapp/workSamples/";
	public static final String SAVEDPORTPATH = "http://localhost:8080/Freelance/workSamples/";
	public static final String RESUMEFILEPATH = "/Users/christopherdimitrisastropranoto/Documents/workspace/Freelance/src/main/webapp/resume/";
	public static final String SAVEDRESUMEPATH = "http://localhost:8080/Freelance/resume/";
	
	/**
	 * Checks to see whether or not file name contains a space.  Returns true if there is a space
	 * and false otherwise
	 * @param fileName
	 * @return
	 */
	public static boolean containsSpace(String fileName) {
		for(int i = 0; i < fileName.length(); i++) {
			char let = fileName.charAt(i);
			if(let == ' ') {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Formats a string with a gap to be suitable for URL
	 * @param fileName
	 * @return
	 */
	public static String formatter(String fileName) {
		String res = "";
		for(int i = 0; i < fileName.length(); i++) {
			char letter = fileName.charAt(i);
			if(letter == ' ') {
				res += "%20";
			} else {
				res += letter;
			}	
		}
		return res;
	}
	
}
