package name.christophperrins.swaglabtesting.utils;

import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	private String fileLocation = "application.properties";    private Properties properties;

	public ReadConfig() {
		loadProperties();
	}
	
	public ReadConfig(String fileLocation) {
		this.fileLocation = fileLocation;
		loadProperties();
	}
	
	private void loadProperties() {
		properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public String getPropertyValue(String property) {
		return properties.getProperty(property);
	}
	
}
