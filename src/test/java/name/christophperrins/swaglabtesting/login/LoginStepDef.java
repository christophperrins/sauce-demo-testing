package name.christophperrins.swaglabtesting.login;

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
import name.christophperrins.swaglabtesting.inventory.InventoryPage;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class LoginStepDef {

	Scenario scenario;
	WebDriver driver;
	ExtentTest extentTest;

	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
		extentTest = LoginTest.extentTest.createNode(scenario.getName());
		String browser = System.getProperty("browser");
		driver = TestUtils.initialiseDriver(browser);
		driver.manage().window().setSize(new Dimension(1536, 756));
	}

	@After
	public void tearDown() {
		TestUtils.endTest(extentTest);
		driver.quit();
	}

	@Given("^you are on the login page$")
	public void you_are_on_the_login_page() throws Throwable {
		driver.get(LoginPage.LOGIN_URL);
		extentTest.createNode("GIVEN").info("Driver opened login page at" + LoginPage.LOGIN_URL);
	}

	@When("^you enter the username \"([^\"]*)\"$")
	public void you_enter_the_username(String arg1) throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(arg1);
		extentTest.createNode("WHEN").info(arg1 + "typed into username input field");
	}

	@When("^you enter the password \"([^\"]*)\"$")
	public void you_enter_the_password(String arg1) throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		extentTest.createNode("WHEN").info(arg1 + "typed into password input field");
		loginPage.enterPassword(arg1);
	}

	@When("^you click login button$")
	public void you_click_login_button() throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		extentTest.createNode("WHEN").info("Login button clicked");
		loginPage.clickLoginButton();
	}

	@Then("^the application should bring you to the inventory screen$")
	public void the_application_should_bring_you_to_the_inventory_screen() throws Throwable {
		String outputDestination = TestUtils.takeScreenshot(driver, scenario);
		if (InventoryPage.INVENTORY_URL.equals(driver.getCurrentUrl())) {
			extentTest.createNode("THEN").pass("Inventory screen",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			extentTest.createNode("THEN").fail("'Inventory screen'",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
			fail();
		}
	}

	@When("^you hit the enter key in the password input$")
	public void you_hit_the_enter_key_in_the_password_input() throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.pressEnterOnPasswordInput();
	}

	@Then("^an error message should pop up$")
	public void an_error_message_should_pop_up_stating_you_are_locked_out() throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		if (loginPage.isErrorButtonDisplayed()) {
			String outputDestination = TestUtils.takeScreenshot(driver, scenario);
			extentTest.createNode("THEN").pass("Error displayed",
					MediaEntityBuilder.createScreenCaptureFromPath(outputDestination).build());
		} else {
			fail();
		}
	}

}
