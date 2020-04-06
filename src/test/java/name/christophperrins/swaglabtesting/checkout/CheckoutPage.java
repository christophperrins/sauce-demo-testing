package name.christophperrins.swaglabtesting.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {


	public static final String CHECKOUT_URL = "https://www.saucedemo.com/checkout-step-one.html";
	
	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
}
