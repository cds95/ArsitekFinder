package tests;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Home;

public class TestSignup {
	public static final String CHROME = "C:\\Users\\Demario\\Desktop\\WebDrivers\\chromedriver.exe";
	private WebDriver driver;
	private Home page;
	private Map<String, String> userInfo;
	public final String[] INPUTS = {"first", "last", "handle", "password", "email"};
	
	@BeforeTest
	public void launchapp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", CHROME);
		this.driver = new ChromeDriver();
		this.page = new Home(this.driver);
		this.userInfo = new HashMap<String, String>();
		this.userInfo.put("first", "John");
		this.userInfo.put("last", "Doe");
		this.userInfo.put("handle", "jdoe");
		this.userInfo.put("password", "jdoe11");
		this.userInfo.put("email", "jdoe@gmail.com");
		Thread.sleep(1000);
	}
	
	/**
	 * Tests case when first name field is left empty
	 * @throws InterruptedException
	 */
	@Test(priority=1, description="Test with empty first name")
	public void testEmptyFirst() throws InterruptedException {
		String error = this.testEmptyField("first");
		String expected = "Please enter your first name";
		assertEquals(error, expected);
	}
	
	/**
	 * Tests case when first name field is left empty
	 * @throws InterruptedException
	 */
	@Test(priority=2, description="Test with empty first name")
	public void testEmptyLast() throws InterruptedException {
		String error = this.testEmptyField("last");
		String expected = "Please enter your last name";
		assertEquals(error, expected);
	}
	
	/**
	 * Tests case when username field is left empty
	 * @throws InterruptedException
	 */
	@Test(priority=3, description="Test with empty username")
	public void testEmptyUsername() throws InterruptedException {
		String error = this.testEmptyField("handle");
		String expected = "Please enter a username";
		assertEquals(error, expected);
	}
	
	/**
	 * Tests case when username field is left empty
	 * @throws InterruptedException
	 */
	@Test(priority=4, description="Test with empty password")
	public void testEmptyPassword() throws InterruptedException {
		String error = this.testEmptyField("password");
		String expected = "Please enter a password";
		assertEquals(error, expected);
	}
	
	/**
	 * Tests case when username field is left empty
	 * @throws InterruptedException
	 */
	@Test(priority=5, description="Test with empty username")
	public void testEmptyEmail() throws InterruptedException {
		String error = this.testEmptyField("email");
		String expected = "Please enter a valid email";
		assertEquals(error, expected);
	}
	
	/**
	 * Tests case when the inputted username has already been taken by another
	 * account
	 * @throws InterruptedException
	 */
	@Test(priority=6, description="Test with an already taken username")
	public void testDuplicateUserName() throws InterruptedException {
		this.page.signUp("John", "Doe", "cds95", "dimitri1995", "cds95@uw.edu");
		Thread.sleep(5000); //Give time for ajax request to be completed
		String error = this.page.getDuplicateUserError();
		String expected = "Username already taken";
		assertEquals(error, expected);
	}
	
	/**
	 * Tests an empty field by making the given argument's field empty and returns the 
	 * provided error message
	 * @param test
	 * @return
	 * @throws InterruptedException
	 */
	private String testEmptyField(String test) throws InterruptedException {
		String[] userInformation = new String[INPUTS.length];
		for(int i = 0; i < INPUTS.length; i++) {
			String label = INPUTS[i];
			if(test.equals(label)) {
				userInformation[i] = "";
			} else {
				userInformation[i] = this.userInfo.get(label);
			}
		}
		this.page.signUp(userInformation[0], userInformation[1], userInformation[2], userInformation[3], userInformation[4]);
		Thread.sleep(1000);
		return this.page.getErrorText(test);
	}
	

	@AfterTest
	public void terminatetest() {
		this.driver.close();
	}
}