package name.christophperrins.swaglabtesting.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.Scenario;

public class TestUtils {
	
	public static final WebDriver initialiseDriver(String browser) {
		ReadConfig config = new ReadConfig();
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					config.getPropertyValue("driver.chrome.location"));
			String chromeArguments = config.getPropertyValue("driver.chrome.arguments");
			ChromeOptions chromeOptions = new ChromeOptions();
			if (chromeArguments == null) {
				return new ChromeDriver();
			}
			chromeOptions.addArguments(chromeArguments);
			return new ChromeDriver(chromeOptions);
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					config.getPropertyValue("driver.firefox.location"));
			FirefoxOptions options = new FirefoxOptions();
			String firefoxArguments = config.getPropertyValue("driver.firefox.arguments");
			if (firefoxArguments == null) {
				return new FirefoxDriver();
			}
			options.addArguments(firefoxArguments);
			return new FirefoxDriver(options);
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
	
	public static String takeScreenshot(WebDriver driver, Scenario scenario) throws IOException {
		return takeScreenshot(driver, scenarioIdToName(scenario.getId()));
	}
	
	public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
		FileInputStream  fileInputStream = new FileInputStream(screenshot);
		String output = new ReadConfig().getPropertyValue("extentreport.directory")+"/"+screenshotName+".png";
		FileOutputStream fileOutputStream = new FileOutputStream(output);
		
		byte[] bytes = new byte[1024];
		
		while(fileInputStream.read(bytes) != -1) {
			fileOutputStream.write(bytes);
		}
		
		fileInputStream.close();
		fileOutputStream.close();
		return output;
	}
	
	public static String scenarioIdToName(String scenarioName) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < scenarioName.length(); i++) {
			String letter = scenarioName.substring(i, i+1);
			switch (letter) {
			case ";":
			case ":":
				letter = "-";
				break;
			case ".":
				letter = "";
				break;
			}
			stringBuilder.append(letter);
		}
		return stringBuilder.toString();
	}
}
