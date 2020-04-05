package name.christophperrins.swaglabtesting;

import org.junit.runner.JUnitCore;

import name.christophperrins.swaglabtesting.login.LoginTest;

public class Runner {
	public static void main(String[] args) {
		System.setProperty("browser", "chrome");
		JUnitCore.runClasses(LoginTest.class);
	}
}
