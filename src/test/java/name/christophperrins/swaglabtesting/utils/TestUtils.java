package name.christophperrins.swaglabtesting.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestUtils {
	
	public static final WebDriver initialiseDriver(String browser) {
		if (browser.equals("chrome")) {
			ReadConfig config = new ReadConfig();
			System.setProperty("webdriver.chrome.driver",
					config.getPropertyValue("driver.chrome.location"));
			String chromeArguments = config.getPropertyValue("driver.chrome.arguments");
			ChromeOptions chromeOptions = new ChromeOptions();
			if (chromeArguments == null) {
				return new ChromeDriver();
			}
			chromeOptions.addArguments(chromeArguments);
			return new ChromeDriver(chromeOptions);
		}
		else {
			throw new RuntimeException("Error has occured as i didnt get the chrome for browser");
		}
	}
	
	static ExtentReports extentReports;
	
	public static void startReport(String reportName) {
		extentReports = new ExtentReports();
		extentReports.attachReporter(new ExtentHtmlReporter(new ReadConfig().getPropertyValue("extentreport.directory") + "/" + reportName +".html"));
	}
	
	public static void endReport() {
		extentReports.flush();
	}
	
	public static ExtentTest createTest(String testName) {
		return extentReports.createTest(testName);
	}
		
	public static void endTest(ExtentTest extentTest) {
		extentReports.flush();
	}
}
