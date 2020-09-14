package com.FWV1A.lib;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
	static Logger log = Logger.getLogger(BrowserFactory.class); 
String chromedriverpath;
String firefoxdriverpath;
String iedriverpath;
HelperOne help;

public BrowserFactory() {
	DOMConfigurator.configure("./src/com/FWV1A/Lib/log4j_FWV1A.xml");
	help = new HelperOne();
	chromedriverpath = help.getValueFrom("chromedriverpath");
	firefoxdriverpath = help.getValueFrom("firefoxdriverpath");
	iedriverpath = help.getValueFrom("iedriverpath");

}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" im factory");
	}

	
public WebDriver initiateBrowser(WebDriver driver, String browserType, String url) {
	if(browserType.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver",chromedriverpath );
		driver = new ChromeDriver();
		log.info("browserFactory>initiateBrowser> chrome opened");
	}
	
	else if(browserType.equalsIgnoreCase("ff")) {
		System.setProperty("webdriver.gecko.driver",firefoxdriverpath );
		driver = new FirefoxDriver();
		log.info("browserFactory>initiateBrowser> firefox opened");

	}else if(browserType.equalsIgnoreCase("ie")) {
		System.setProperty("webdriver.ie.driver",iedriverpath );
		driver = new InternetExplorerDriver();
		log.info("browserFactory>initiateBrowser> ie opened");
	}
	
	driver.get(url);
	return driver;
}	
}
