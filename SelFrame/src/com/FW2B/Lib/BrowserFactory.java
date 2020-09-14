package com.FW2B.Lib;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import com.FW2B.Lib.Helper;

public class BrowserFactory {
	static Logger log = Logger.getLogger(BrowserFactory.class.getName());
	Helper help;
	String chromedriverpath = null;
	String firefoxdriverpath = null;
	String iedriverpath = null;
	public BrowserFactory(){
		help = new Helper();
		//DOMConfigurator.configure("log4j_FW_staticPage.xml");
		DOMConfigurator.configure(help.PropertyFileRead("log4jxmlpath"));
		chromedriverpath = help.PropertyFileRead("chromedriverpath");
		//chromedriverpath = "./Drivers/chromedriver.exe";
		
		firefoxdriverpath = help.PropertyFileRead("firefoxdriverpath");
		iedriverpath = help.PropertyFileRead("iedriverpath");
	}
	//public static void main(String[] args) {	}

	public  WebDriver startApplication(WebDriver driver,String browsername,String url) {
		
		if (browsername.equalsIgnoreCase("Chrome")) {
			log.info("The chromedriver loaded from property file:> "+ chromedriverpath);
			//System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe" );
			System.setProperty("webdriver.chrome.driver", chromedriverpath );
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("FF")) {
			log.info("The firefox driver loaded from property file: "+ firefoxdriverpath);
			//System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", firefoxdriverpath);
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("Opera")) {
			System.setProperty("webdriver.opera.driver", "./Drivers/operadriver.exe");
			driver = new OperaDriver();
		} else if (browsername.equalsIgnoreCase("IE")) {
			log.info("The IE driver loaded from property file: "+ iedriverpath);
			//System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", iedriverpath);
			driver = new InternetExplorerDriver();
		}else if (browsername.equalsIgnoreCase("rChrome")) {
			log.info("The remote chromedriver url and port from property file loading");
			try {
				driver = OpenChromeRemote(driver);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			log.info("We do not support this browser");
		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);

		return driver;

	}
	public  WebDriver quitbrowser(WebDriver driver) {
		driver.quit();
		return driver;
	}


	public WebDriver OpenChromeRemote(WebDriver driver) throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		//driver = new RemoteWebDriver(new URL("http://127.0.0.1:9515"),
		//	new ChromeOptions());
		//driver = new RemoteWebDriver(new URL("http://127.0.0.1:9099"),options);
		driver = new RemoteWebDriver(new URL(help.PropertyFileRead("ipremoteport")),options);
		//driver.get(url);
		return driver;
	}
	public void bfact() {System.out.println("inside browser factory");}
	
	
}
