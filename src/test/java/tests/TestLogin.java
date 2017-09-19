package tests;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Home;
import pages.Login;

public class TestLogin {
	public static final String CHROME = "C:\\Users\\Demario\\Desktop\\WebDrivers\\chromedriver.exe";
	private WebDriver driver;
	private Login page;
	
	@BeforeTest
	public void launchapp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", CHROME);
		this.driver = new ChromeDriver();
		this.page = new Login(this.driver);
		Thread.sleep(1000);
	}
	
	/**
	 * Tests case when username field is left empty
	 * @throws InterruptedException
	 */
	@Test(priority=1, description="Test with empty username")
	public void testEmptyUsername() throws InterruptedException {
		String username = "";
		this.page.incorrectLogin(username, "dimitri1995");
		this.checkForInvalid();
	}
	
	/**
	 * Tests case when password field is left empty
	 * @throws InterruptedException
	 */
	@Test(priority=2, description="Test with empty password field")
	public void testEmptyPassword() throws InterruptedException {
		this.page.incorrectLogin("cds95", "");
		this.checkForInvalid();
	}
	
	/**
	 * Tests case when password field is left empty
	 * @throws InterruptedException
	 */
	@Test(priority=3, description="Test with wrong credentials")
	public void testWrongCredentials() throws InterruptedException {
		this.page.incorrectLogin("cds95", "dsfdsf");
		this.checkForInvalid();
	}
	
	/**
	 * Tests login function using normal and correct credentials
	 * @throws InterruptedException
	 */
	@Test(priority=4, description="Tests with proper and correct credentials")
	public void testLogin() throws InterruptedException {
		String username = "cds95";
		Home home = this.page.correctLogin(username, "dimitri1995");
		String user = home.getHandleText();
		assertEquals(username, user);
	}
	

	@AfterTest
	public void terminatetest() {
		this.driver.close();
	}
	
	/**
	 * Checks whether or not invalid text appears with correct text
	 */
	private void checkForInvalid() {
		String invalid = this.page.getInvalidText();
		assertEquals("Invalid username or password", invalid);
	}
}
