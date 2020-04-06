package name.christophperrins.swaglabtesting.checkout;

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
import name.christophperrins.swaglabtesting.overview.OverviewPage;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class CheckoutStepDef {

	Scenario scenario;
	WebDriver driver;
	ExtentTest extentTest;

	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
		extentTest = CheckoutTest.extentTest.createNode(scenario.getName());
		String browser = System.getProperty("browser");
		driver = TestUtils.initialiseDriver(browser);
		driver.manage().window().setSize(new Dimension(1536, 756));
	}

	@After
	public void tearDown() {
		TestUtils.endTest(extentTest);
		driver.quit();
	}
	
	@Given("^I am on the checkout page$")
	public void i_am_on_the_checkout_page() throws Throwable {
	    driver.get(CheckoutPage.CHECKOUT_URL);
	    extentTest.createNode("GIVEN").info("moved to checkout page");
	}

	@When("^I enter the firstname \"([^\"]*)\"$")
	public void i_enter_the_firstname(String arg1) throws Throwable {
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    checkoutPage.addFirstName(arg1);
	    extentTest.createNode("When").info("firstname " + arg1 + " added");

	}

	@When("^I enter the lastname \"([^\"]*)\"$")
	public void i_enter_the_lastname(String arg1) throws Throwable {
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    checkoutPage.addLastName(arg1);
	    extentTest.createNode("When").info("lastname " + arg1 + " added");

	}

	@When("^i enter the Zip/Postal code \"([^\"]*)\"$")
	public void i_enter_the_Zip_Postal_code(String arg1) throws Throwable {
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    checkoutPage.addPostalName(arg1);
	    extentTest.createNode("When").info("postcode " + arg1 + " added");
	}
	
	@When("^I click continue$")
	public void i_click_continue() throws Throwable {
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    checkoutPage.continueToOverview();
	    extentTest.createNode("When").info("Continue clicked");

	}

	@Then("^I am brought to the checkout overview page$")
	public void i_am_brought_to_the_checkout_overview_page() throws Throwable {
		String outputDestination = TestUtils.takeScreenshot(driver, scenario);
	    if(driver.getCurrentUrl().equals(OverviewPage.CHECKOUT_URL)) {
			extentTest.createNode("THEN").pass("Overview page should be visible",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.createNode("THEN").fail("'Overview page should be visible'",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
			fail();
		}
	}

	@Then("^an error message should pop up$")
	public void an_error_message_should_pop_up() throws Throwable {
		String outputDestination = TestUtils.takeScreenshot(driver, scenario);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
	    if (checkoutPage.isErrorDisplayed()) {
			extentTest.createNode("THEN").pass("Error message should be visible",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.createNode("THEN").fail("Error message should be visible",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
			fail();
		}
	}
}
