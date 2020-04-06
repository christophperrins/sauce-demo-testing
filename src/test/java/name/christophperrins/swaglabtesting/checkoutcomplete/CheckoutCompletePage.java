package name.christophperrins.swaglabtesting.checkoutcomplete;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {

	public static final String CHECKOUT_COMPLETE_URL = "https://www.saucedemo.com/checkout-complete.html";

	public CheckoutCompletePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
