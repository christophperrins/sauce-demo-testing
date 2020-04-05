package name.christophperrins.swaglabtesting.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import name.christophperrins.swaglabtesting.inventory.InventoryPage;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class LoginStepDef {

	WebDriver driver;

	@Before
	public void setUp() {
		String browser = System.getProperty("browser");
		driver = TestUtils.initialiseDriver(browser);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Given("^you are on the login page$")
	public void you_are_on_the_login_page() throws Throwable {
		driver.get(LoginPage.LOGIN_URL);
	}

	@When("^you enter the username \"([^\"]*)\"$")
	public void you_enter_the_username(String arg1) throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(arg1);
	}

	@When("^you enter the password \"([^\"]*)\"$")
	public void you_enter_the_password(String arg1) throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterPassword(arg1);
	}

	@When("^you click login button$")
	public void you_click_login_button() throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickLoginButton();
	}

	@Then("^the application should bring you to the inventory screen$")
	public void the_application_should_bring_you_to_the_inventory_screen() throws Throwable {
		assertEquals(InventoryPage.INVENTORY_URL, driver.getCurrentUrl());
	}

	@When("^you hit the enter key in the password input$")
	public void you_hit_the_enter_key_in_the_password_input() throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.pressEnterOnPasswordInput();
	}

	@Then("^an error message should pop up stating you are locked out$")
	public void an_error_message_should_pop_up_stating_you_are_locked_out() throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		assertTrue(loginPage.isErrorButtonDisplayed());
	}

}
