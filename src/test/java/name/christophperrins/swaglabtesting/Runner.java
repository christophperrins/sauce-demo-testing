package name.christophperrins.swaglabtesting;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import name.christophperrins.swaglabtesting.cart.CartTest;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class Runner {
	public static void main(String[] args) {

		System.setProperty("browser", "chrome");
		TestUtils.startReport("Chrome Report");
		JUnitCore core = new JUnitCore();
		
		core.addListener(new TextListener(System.out));
		
		Class<?>[] classes = {CartTest.class};
		
		core.run(classes);
		TestUtils.endReport();
	}
}
