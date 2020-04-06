package name.christophperrins.swaglabtesting.cart;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	public static final String CART_URL = "https://www.saucedemo.com/cart.html";
	
	@FindBy(className = "cart_item")
	private List<WebElement> cartItems;
	
	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public int numberOfItemsInBasket() {
		return cartItems.size();
	}
}
