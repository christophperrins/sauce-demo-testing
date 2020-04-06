package name.christophperrins.swaglabtesting.overview;

import static org.junit.Assert.fail;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import name.christophperrins.swaglabtesting.checkoutcomplete.CheckoutCompletePage;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class OverviewStepDef {

	Scenario scenario;
	WebDriver driver;
	ExtentTest extentTest;

	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
		extentTest = OverviewTest.extentTest.createNode(scenario.getName());
		String browser = System.getProperty("browser");
		driver = TestUtils.initialiseDriver(browser);
		driver.manage().window().setSize(new Dimension(1536, 756));
	}

	@After
	public void tearDown() {
		TestUtils.endTest(extentTest);
		driver.quit();
	}
	
	@Given("^I am on the checkout overview page$")
	public void i_am_on_the_checkout_overview_page() throws Throwable {
	    driver.get(OverviewPage.CHECKOUT_URL);
	    extentTest.createNode("GIVEN").info("Directing to " + OverviewPage.CHECKOUT_URL);
	}

	@When("^I click finish$")
	public void i_click_finish() throws Throwable {
	    OverviewPage overviewPage = new OverviewPage(driver);
	    overviewPage.clickFinish();
	    extentTest.createNode("WHEN").info("finish button clicked");
	}

	@Then("^I am brought to the checkout complete page$")
	public void i_am_brought_to_the_checkout_complete_page() throws Throwable {
		String outputDestination = TestUtils.takeScreenshot(driver, scenario);
	    if(driver.getCurrentUrl().equals(CheckoutCompletePage.CHECKOUT_COMPLETE_URL)) {
			extentTest.createNode("THEN").pass("Complete page should be visible",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.createNode("THEN").fail("'Complete page should be visible'",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
			fail();
		}
	}
}
