package demo2b.Libs;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class ConfigDataProvider {
	Properties pro;
	public ConfigDataProvider() {
		try {
			File src = new File("./Config/Config.properties");
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Not able to load config file :" + e.getMessage());
		}
	}
	public String getBrowser() {
		return pro.getProperty("Browser");
	}
	public String getAppUrl() {
		return pro.getProperty("Url");
	}
}
