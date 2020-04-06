package name.christophperrins.swaglabtesting.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	public static final String CHECKOUT_URL = "https://www.saucedemo.com/checkout-step-one.html";
	
	@FindBy(id = "first-name")
	private WebElement firstnameElement;
	
	@FindBy(id = "last-name")
	private WebElement lastnameElement;
	
	@FindBy(id = "postal-code")
	private WebElement postalnameElement;
	
	@FindBy(className = "cart_button")
	private WebElement continueButton;
	
	@FindBy(className = "cart_cancel_link ")
	private WebElement cancelButton;
	
	@FindBy(className = "error-button")
	private WebElement errorButton;
	
	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void addFirstName(String firstname) {
		firstnameElement.sendKeys(firstname);
	}
	
	public void addLastName(String lastname) {
		lastnameElement.sendKeys(lastname);
	}
	
	public void addPostalName(String postalName) {
		postalnameElement.sendKeys(postalName);
	}
	
	public void continueToOverview() {
		continueButton.click();
	}
	
	public boolean isErrorDisplayed() {
		return errorButton.isDisplayed();
	}

}
