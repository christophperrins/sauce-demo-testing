package name.christophperrins.swaglabtesting.login;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public static final String LOGIN_URL = "https://www.saucedemo.com/index.html";
	
	@FindBy(id = "user-name")
	private WebElement usernameInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput;
	
	@FindBys( {
		   @FindBy(className = "login-box"),
		   @FindBy(className = "btn_action")
		} )	
	private WebElement loginButton;
	
	@FindBys( {
		@FindBy(className = "login-box"),
		@FindBy(className = "error-button")
	})
	private WebElement errorButton;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		usernameInput.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}
	
	public void pressEnterOnPasswordInput() {
		passwordInput.sendKeys(Keys.ENTER);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public boolean isErrorButtonDisplayed() {
		return errorButton.isDisplayed();
	}

}
