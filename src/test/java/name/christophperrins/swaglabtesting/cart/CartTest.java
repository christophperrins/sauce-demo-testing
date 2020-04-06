package name.christophperrins.swaglabtesting.cart;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.junit.Cucumber;
import name.christophperrins.swaglabtesting.utils.TestUtils;

@RunWith(Cucumber.class)
public class CartTest {

protected static ExtentTest extentTest;
	
	@BeforeClass
	public static void setup() {
		extentTest = TestUtils.createTest("Cart");
	}
	
	@AfterClass
	public static void tearDown() {
		TestUtils.endTest(extentTest);
	}
}
