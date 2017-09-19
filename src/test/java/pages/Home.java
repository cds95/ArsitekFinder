package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	
	public static final String URL = "https://jh-studio.herokuapp.com/";
	private WebDriver driver;
	@FindBy(id="first")
	private WebElement firstName;
	@FindBy(id="last")
	private WebElement last;
	@FindBy(id="handle")
	private WebElement handle;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="email")
	private WebElement email;
	
	@FindBy(linkText="Login")
	private WebElement login;
	
	@FindBy(id="first-error")
	private WebElement firstError;
	@FindBy(id="last-error")
	private WebElement lastError;
	@FindBy(id="handle-error")
	private WebElement handleError;
	@FindBy(id="password-error")
	private WebElement passwordError;
	@FindBy(id="email-error")
	private WebElement emailError;
	@FindBy(id="same-handle-error")
	private WebElement sameHandleError;
	@FindBy(id="submit")
	private WebElement submit;
	public Home(WebDriver driver) {
		this.driver = driver;
		this.driver.get(URL);
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Recreates signing up a user
	 * @param first
	 * @param last
	 * @param handle
	 * @param password
	 * @param email
	 * @throws InterruptedException 
	 */
	public void signUp(String first, String last, String handle, String password, String email) throws InterruptedException {
		this.clear();
		this.fillInInfo(first, last, handle, password, email);
		this.submit.click();
		Thread.sleep(1000);
	}
	
	/**
	 * Goes to login page
	 * @throws InterruptedException
	 */
	public Login goToLogin() throws InterruptedException {
		this.login.click();
		return new Login(this.driver);
	}
	
	/**
	 * Fills in the required information to sign up for an account
	 * @param first
	 * @param last
	 * @param handle
	 * @param password
	 * @param email
	 * @throws InterruptedException 
	 */
	private void fillInInfo(String first, String last, String handle, String password, String email) throws InterruptedException {
		this.firstName.sendKeys(first);
		Thread.sleep(1000);
		this.last.sendKeys(last);
		Thread.sleep(1000);
		this.handle.sendKeys(handle);
		Thread.sleep(1000);
		this.password.sendKeys(password);
		Thread.sleep(1000);
		this.email.sendKeys(email);
		Thread.sleep(1000);
	}
	
	/**
	 * Clears all text fields
	 */
	private void clear() {
		this.firstName.clear();
		this.last.clear();
		this.handle.clear();
		this.password.clear();
		this.email.clear();
	}
	
	/**
	 * Gets the username at the navbar
	 */
	public String getHandleText() {
		return this.handle.getText();
	}
	
	/**
	 * Gets the error text according to the input argument
	 * @param error
	 * @return
	 * first - Returns text error when first name input is empty
	 * last - Returns text error when last name input is empty
	 * email - Returns text error when email input is empty
	 * password - Returns text error when password field is empty
	 * handle - Returns text error when handle field is left empty
	 */
	public String getErrorText(String error) {
		if(error.equals("first")) {
			return this.firstError.getText();
		} else if(error.equals("last")) {
			return this.lastError.getText();
		} else if(error.equals("email")) {
			return this.emailError.getText();
		} else if(error.equals("password")) {
			return this.passwordError.getText();
		} else if(error.equals("handle")) {
			return this.handleError.getText();
		} else {
			return this.sameHandleError.getText();
		}
	}
	
	/**
	 * Returns text error when handle field is left empty
	 * @return
	 */
	public String getDuplicateUserError() {
		return this.getErrorText("");
	}
}
