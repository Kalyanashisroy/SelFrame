package demo2b.Runner;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import demo2b.Libs.BrowserFactory;
import demo2b.Libs.ConfigDataProvider;
import demo2b.PageObjects.PageObject_Lesson_1;
import demo2b.TestCases.TestCases_Lesson_1;

// Runner section
class Runner_Lesson_1 {
	public WebDriver driver;
	//String Framework_Lesson_1_path = System.getProperty("user.dir") + "/src/com/Test_ng/";
	//String url= Framework_Lesson_1_path + "/JavaFirstParentPage.html";
	//String url= "file:///D:/staticwebpage/JavaFirstParentPage.html";
	TestCases_Lesson_1 TCL1;
	PageObject_Lesson_1 pol;
	Runner_Lesson_1(){
		TCL1 = new TestCases_Lesson_1(driver);	
	}
	@BeforeTest
	public void OpenBrowser() {
		//String browser=System.getProperty("executionbrowser");
		//System.out.println(browser);
		ConfigDataProvider config=new ConfigDataProvider();
		driver=TCL1.openbrowser(driver, config.getBrowser(), config.getAppUrl());
		//driver=TCL1.openbrowser(driver, browser, config.getAppUrl());
			
	}
	
	@Test
	public void TR_1() {
		pol=new PageObject_Lesson_1(driver);
		/*PageObject_Lesson_1 pol=new PageObject_Lesson_1(driver);
		pol.enterFirstName();*/
		//pol.inputtext.sendKeys("abcd");
		pol.enterFirstName();	
	}	
			
	@AfterTest
	public void CloseBrowser() {
		TCL1.closebrowser(driver);
				
	}
}




