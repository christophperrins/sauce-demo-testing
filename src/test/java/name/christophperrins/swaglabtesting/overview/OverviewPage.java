package name.christophperrins.swaglabtesting.overview;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewPage {

	public static final String CHECKOUT_URL = "https://www.saucedemo.com/checkout-step-two.html";
	
	@FindBy(className = "cart_button ")
	private WebElement finish;
	
	public OverviewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickFinish() {
		finish.click();
	}
}
