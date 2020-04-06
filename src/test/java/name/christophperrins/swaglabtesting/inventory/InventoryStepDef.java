package name.christophperrins.swaglabtesting.inventory;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import name.christophperrins.swaglabtesting.cart.CartPage;
import name.christophperrins.swaglabtesting.inventoryitem.InventoryItemPage;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class InventoryStepDef {

	Scenario scenario;
	WebDriver driver;
	ExtentTest extentTest;
	String tempValue;

	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
		System.setProperty("browser", "chrome");
		extentTest = InventoryTest.extentTest.createNode(scenario.getName());
		String browser = System.getProperty("browser");
		driver = TestUtils.initialiseDriver(browser);
		driver.manage().window().setSize(new Dimension(1536, 756));
	}

	@After
	public void tearDown() {
		TestUtils.endTest(extentTest);
		driver.quit();
	}

	@Given("^I am on the inventory webpage$")
	public void i_am_on_the_inventory_webpage() {
		driver.get(InventoryPage.INVENTORY_URL);
		extentTest.createNode("GIVEN").info("Navigated to inventory screen");
	}

	@When("^I click \"([^\"]*)\" on an item$")
	public void i_click_on_an_item(String arg1) throws IOException {
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.clickOnFirstInstanceOf(arg1);
		extentTest.createNode("WHEN").info("Clicked on Add to cart");
	}

	@Then("^it should display (\\d+) next to my cart$")
	public void it_should_display_next_to_my_cart(int arg1) throws Throwable {
		InventoryPage inventoryPage = new InventoryPage(driver);
		String outputDestination = TestUtils.takeScreenshot(driver, scenario);
		if (String.valueOf(arg1).equals(inventoryPage.getNumberOfItemsNextToCart())) {
			extentTest.createNode("Then").pass("Clicked on Add to cart",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.createNode("Then").fail("Clicked on Add to cart",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
			fail();
		}
		;
	}

	@Then("^the session storage key \"([^\"]*)\" should be of length (\\d+)$")
	public void the_session_storage_key_should_be_of_length(String arg1, int arg2) throws Throwable {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String number = js.executeScript("return JSON.parse(window.sessionStorage.getItem('" + arg1 + "')).length;")
				.toString();
		String outputDestination = TestUtils.takeScreenshot(driver, scenario);
		if (String.valueOf(arg2).equals(number)) {
			extentTest.createNode("Then").pass("Clicked on Add to cart",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.createNode("Then").fail("Clicked on Add to cart",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
			fail();
		}
		;
	}

	@When("^i click on an inventory item$")
	public void i_click_on_an_inventory_item() throws Throwable {
		InventoryPage inventoryPage = new InventoryPage(driver);
		String clickedItem = inventoryPage.clickInventoryItem();
		tempValue = clickedItem;
		extentTest.createNode("WHEN").info("Clicked on " + clickedItem);

	}

	@Then("^i am brought to an inventory-item page with the same title$")
	public void i_am_brought_to_an_inventory_item_page_with_the_same_title() throws Throwable {
		ExtentTest extentTest = this.extentTest.createNode("Then");
		String itemUrl = InventoryItemPage.INVENTORY_ITEM_URL;
		if (itemUrl.equals(driver.getCurrentUrl().substring(0, itemUrl.length()))) {
			extentTest.pass("Taken to the correct page");
		} else {
			extentTest.fail("Taken to incorrect page");
		}
		;

		InventoryItemPage inventoryItemPage = new InventoryItemPage(driver);

		String outputDestination = TestUtils.takeScreenshot(driver, scenario);
		if (inventoryItemPage.nameOfItem().equals(tempValue)) {
			extentTest.pass("Brought to item page",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.fail("Brought to item page",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		}

	}

	@When("^I click on the cart icon$")
	public void i_click_on_the_cart_icon() throws Throwable {
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.clickShoppingCart();
		extentTest.createNode("WHEN").info("Cart clicked");
	}

	@Then("^I am taken to the cart webpage$")
	public void i_am_taken_to_the_cart_webpage() throws Throwable {
		if (driver.getCurrentUrl().equals(CartPage.CART_URL)) {
			extentTest.createNode("THEN").pass("taken to cart page");
		} else {
			extentTest.createNode("THEN").fail("not taken to cart page");
		}
	}

	@Then("^there are three items inside$")
	public void there_are_three_items_inside() throws Throwable {
		CartPage cartPage = new CartPage(driver);
		String outputDestination = TestUtils.takeScreenshot(driver, scenario);

		if (cartPage.numberOfItemsInBasket() == 3) {
			extentTest.createNode("THEN").pass("Correct number of items in basket",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.createNode("THEN").fail("Incorrect number of items in basket",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		}
	}

//	@When("^I sort A to Z$")
//	public void i_sort_A_to_Z() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@Then("^i the inventory items should appear in alphabetical order$")
//	public void i_the_inventory_items_should_appear_in_alphabetical_order() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@When("^I sort Z to A$")
//	public void i_sort_Z_to_A() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@Then("^i the inventory items should appear in reverse alphabetical order$")
//	public void i_the_inventory_items_should_appear_in_reverse_alphabetical_order() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@When("^I sort low to high$")
//	public void i_sort_low_to_high() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@Then("^i the inventory items should appear in order from least expensive to most expensive$")
//	public void i_the_inventory_items_should_appear_in_order_from_least_expensive_to_most_expensive() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@When("^I sort high to low$")
//	public void i_sort_high_to_low() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@Then("^I the inventory items should appear in order from most expensive to least expensive$")
//	public void i_the_inventory_items_should_appear_in_order_from_most_expensive_to_least_expensive() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@When("^I am on a screen less than (\\d+) px in width$")
//	public void i_am_on_a_screen_less_than_px_in_width(int arg1) throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@Then("^each item should appear below one another$")
//	public void each_item_should_appear_below_one_another() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@When("^I am on a screen more than (\\d+) px in width$")
//	public void i_am_on_a_screen_more_than_px_in_width(int arg1) throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}
//
//	@Then("^each item should be next to another item$")
//	public void each_item_should_be_next_to_another_item() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	}

}
