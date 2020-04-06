package name.christophperrins.swaglabtesting.inventory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

	public static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";

	@FindBy(className = "fa-layers-counter")
	private WebElement cartIconNumber;
	
	@FindBy(className = "inventory_item_name")
	private List<WebElement> listOfItemNames;
	
	@FindBy(className = "btn_inventory")
	private List<WebElement> listOfInventoryButtons;
	
	@FindBy(className = "inventory_item_price")
	private List<WebElement> listOfPrices;
	
	
	public InventoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnFirstInstanceOf(String text) {
		for (WebElement button: listOfInventoryButtons) {
			if (button.getText().equals(text)) {
				button.click();
				break;
			}
		}
	}
	
	public String getNumberOfItemsNextToCart() {
		return cartIconNumber.getText();
	}
	
	public String clickInventoryItem() {
		String name = listOfItemNames.get(0).getText();
		listOfItemNames.get(0).click();
		return name;
	}
	
	
	
}
