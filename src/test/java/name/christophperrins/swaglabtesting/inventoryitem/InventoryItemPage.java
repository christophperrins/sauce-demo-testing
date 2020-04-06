package name.christophperrins.swaglabtesting.inventoryitem;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage {

	public static final String INVENTORY_ITEM_URL = "https://www.saucedemo.com/inventory-item.html";

	@FindBy(className= "inventory_details_name")
	private WebElement title;
	
	public InventoryItemPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String nameOfItem() {
		return title.getText();
	}
}
