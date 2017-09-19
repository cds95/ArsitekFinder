package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
	public static final String CHROME = "C:\\Users\\Demario\\Desktop\\WebDrivers\\chromedriver.exe";
	
    public static void main( String[] args ) throws InterruptedException {
    	System.setProperty("webdriver.chrome.driver", CHROME);
		WebDriver driver = new ChromeDriver();
		String first = "John";
		String last = "Does";
		String handle = "test";
		String password = "test";
		String email = "test@gmail.com";
		testSignup(driver, first, last, handle, password, email);
		driver.quit();
    }
    
    
    /**
     * Tests signing up a new user
     * @param driver
     * @param first
     * @param last
     * @param handle
     * @param password
     * @param email
     * @return
     * @throws InterruptedException
     */
    public static boolean testSignup(WebDriver driver, String first, String last, String handle, String password, String email) throws InterruptedException {
    	Home home = new Home(driver);
    	home.signUp(first, last, handle, password, email);
    	Thread.sleep(2000);
    	return true;
    }
}
