package name.christophperrins.swaglabtesting;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import name.christophperrins.swaglabtesting.login.LoginTest;
import name.christophperrins.swaglabtesting.utils.TestUtils;

public class Runner {
	public static void main(String[] args) {
		System.setProperty("browser", "chrome");
		TestUtils.startReport("First Report");
		JUnitCore core = new JUnitCore();
		core.addListener(new TextListener(System.out));
		
		Class<?>[] classes = {LoginTest.class};
		
		core.run(classes);
		TestUtils.endReport();
	}
}
