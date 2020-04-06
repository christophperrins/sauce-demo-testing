package name.christophperrins.swaglabtesting;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import name.christophperrins.swaglabtesting.cart.CartTest;
import name.christophperrins.swaglabtesting.inventory.InventoryTest;
import name.christophperrins.swaglabtesting.login.LoginTest;
import name.christophperrins.swaglabtesting.overview.OverviewTest;
import name.christophperrins.swaglabtesting.utils.ReadConfig;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class Runner {
	public static void main(String[] args) {
		JUnitCore core = new JUnitCore();
		core.addListener(new TextListener(System.out));
		Class<?>[] classes = {LoginTest.class, InventoryTest.class, CartTest.class, OverviewTest.class};

		if (new ReadConfig().getPropertyValue("driver.chrome.location") != null) {
			System.setProperty("browser", "chrome");
			TestUtils.startReport("Chrome Report");
			core.run(classes);
			TestUtils.endReport();			
		}
		if (new ReadConfig().getPropertyValue("driver.firefox.location") != null) {
			System.setProperty("browser", "firefox");
			TestUtils.startReport("Firefox Report");
			core.run(classes);
			TestUtils.endReport();			
		}
		
		
		
		
	}
}
