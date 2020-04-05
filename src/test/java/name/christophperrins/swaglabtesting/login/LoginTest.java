package name.christophperrins.swaglabtesting.login;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.junit.Cucumber;
import name.christophperrins.swaglabtesting.utils.TestUtils;

@RunWith(Cucumber.class)
public class LoginTest {

	protected static ExtentTest extentTest;
	
	@BeforeClass
	public static void setup() {
		extentTest = TestUtils.createTest("Login");
	}
	
	@AfterClass
	public static void tearDown() {
		TestUtils.endTest(extentTest);
	}
}
