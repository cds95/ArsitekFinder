package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	public static final String URL = "https://jh-studio.herokuapp.com/login";
	private WebDriver driver;
	@FindBy(id="username")
	private WebElement username;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="log")
	private WebElement submit;
	@FindBy(id="invalid")
	private WebElement invalid;
	
	/**
	 * Initializes the login page as an object
	 * @param driver
	 */
	public Login(WebDriver driver) {
		this.driver = driver;
		this.driver.get(URL);
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Logs in using the given credentials
	 * @param username
	 * @param password
	 * @throws InterruptedException 
	 */
	public Home correctLogin(String username, String password) throws InterruptedException {
		this.clear();
		this.setCredentials(username, password);
		return new Home(this.driver);
	}
	
	/**
	 * Does an incorrect login
	 * @param username
	 * @param password
	 * @param correct
	 * @throws InterruptedException
	 */
	public void incorrectLogin(String username, String password) throws InterruptedException {
		this.clear();
		this.setCredentials(username, password);
	}
	
	/**
	 * Clears text fields
	 */
	private void clear() {
		this.username.clear();
		this.password.clear();
	}
	
	/**
	 * Sets username, and password credentials in their respective textboxes
	 * @param username
	 * @param password
	 * @throws InterruptedException
	 */
	private void setCredentials(String username, String password) throws InterruptedException {
		this.username.sendKeys(username);
		Thread.sleep(1000);
		this.password.sendKeys(password);
		Thread.sleep(1000);
		this.submit.click();
		Thread.sleep(5000);
	}
	
	/**
	 * Returns the text present in the invalid div that appears after an
	 * invalid login
	 * @return
	 */
	public String getInvalidText() {
		return this.invalid.getText();
	}
	
	
}
