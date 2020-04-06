package name.christophperrins.swaglabtesting.cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import name.christophperrins.swaglabtesting.checkout.CheckoutPage;
import name.christophperrins.swaglabtesting.inventory.InventoryPage;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class CartStepDef {

	Scenario scenario;
	WebDriver driver;
	ExtentTest extentTest;
	String tempValue;

	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
		extentTest = CartTest.extentTest.createNode(scenario.getName());
		String browser = System.getProperty("browser");
		driver = TestUtils.initialiseDriver(browser);
		driver.manage().window().setSize(new Dimension(1536, 756));
	}

	@After
	public void tearDown() {
		TestUtils.endTest(extentTest);
		driver.quit();
	}

	@Given("^there are items in my basket$")
	public void there_are_items_in_my_basket() throws Throwable {
		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.clickOnFirstInstanceOf("ADD TO CART");
		inventoryPage.clickOnFirstInstanceOf("ADD TO CART");
		inventoryPage.clickOnFirstInstanceOf("ADD TO CART");
		extentTest.createNode("GIVEN").info("Three items added to cart");
	}

	@Given("^I am on the cart page$")
	public void i_am_on_the_cart_page() throws Throwable {
		driver.get(CartPage.CART_URL);
		extentTest.info("directed to cartpage");
	}

	@When("^I click checkout$")
	public void i_click_checkout() throws Throwable {
		CartPage cartPage = new CartPage(driver);
		cartPage.clickCheckout();
		extentTest.createNode("WHEN").info("checkout clicked");
	}

	@Then("^I am brought to the checkout page$")
	public void i_am_brought_to_the_checkout_page() throws Throwable {
		String outputDestination = TestUtils.takeScreenshot(driver, scenario);
		if (driver.getCurrentUrl().equals(CheckoutPage.CHECKOUT_URL)) {
			extentTest.createNode("Then").pass("Directed to checkout page",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.createNode("Then").fail("Not directed to correct checkout page",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
			fail();
		}
	}

	@When("^I click continue shopping$")
	public void i_click_continue_shopping() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click on the cart logo$")
	public void i_click_on_the_cart_logo() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^the same number of items should be visible in my cart$")
	public void the_same_number_of_items_should_be_visible_in_my_cart() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^there are no items in my cart$")
	public void there_are_no_items_in_my_cart() throws Throwable {
		CartPage cartPage = new CartPage(driver);
		if (0 == cartPage.numberOfItemsInBasket()) {
			extentTest.createNode("GIVEN").info("No items are present");
		}else {
			extentTest.createNode("GIVEN").fail("Items are present");
			fail();
		}
		
	}

	@Then("^an error message should pop up saying I need to add something to my cart$")
	public void an_error_message_should_pop_up_saying_I_need_to_add_something_to_my_cart() throws Throwable {
		CartPage cartPage = new CartPage(driver);
		if (cartPage.isErrorButtonDisplayed() == true) {
			extentTest.createNode("THEN").pass("Error box present");
		}
			extentTest.createNode("THEN").fail("NO error");
			fail();
	}
}
