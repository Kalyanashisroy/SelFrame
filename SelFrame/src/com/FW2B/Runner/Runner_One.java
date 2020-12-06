package com.FW2B.Runner;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.FW2B.Lib.ExcelDataRW;
import com.FW2B.Lib.Helper;
//Listener is added in xml file
//@Listeners(com.FW.Lib.Listener.class)
//below code donot work - not invoking 2 times.
//@Test(invocationCount=2)
import com.FW2B.TC.TestCases_One;

public class Runner_One {
	public WebDriver driver;
	public WebDriver driver1;

	
	ExcelDataRW exceldata;
	Helper help;
	static Logger log = Logger.getLogger(Runner_One.class.getName());
	TestCases_One TS1;
	//String url="/src/com/FW/web/staticPage/JavaFirstParentPage.html";
	//String url="";
	String browser_pom = null;
	String environment = null;
	String xlxsfilepath = null;
	String pomurl = null;
	public Runner_One() {
		//TS1 = new TestCases_One(this.driver);
		TS1 = new TestCases_One();
		//url = System.getProperty("user.dir")+url;
		//exceldata = new ExcelDataRW();
		help = new Helper();
		//log4jfile
		DOMConfigurator.configure("log4j_FW_staticPage.xml");
		DOMConfigurator.configure(help.PropertyFileRead("log4jxmlpath"));
		log.info("The path of log4j.xml from property file: "+help.PropertyFileRead("log4jfile"));
		browser_pom = System.getProperty("browser_pom");
		environment = System.getProperty("environment");
		pomurl = System.getProperty("pomurl");
	}

	/*public static void main(String[] args) {
		System.out.println("hi");
		String abc = System.getProperty("browser");
		System.out.println("nabo sir:"+abc);

	}*/



	//@Test(dataProvider="usersdata", enabled = true)
	public void TC_001(String username,String password){
		//TS1.Q1_2("hello dear friend how are you ");
		driver = TS1.TC_001_ToTesttheinput(driver,username+password);
		//Helper.captureScreenshot(driver, "q1_2");
		//Assert.assertFalse(true);
		try {
			//wait for 500 ms
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Test
	public void TC_002() {
		driver = TS1.TC_002_test_alllinks_of_main_page(driver);
	}


	//@Test(enabled = true)
	public void TC_030() {
		driver = TS1.TC_030_Polling_by_Explicit_Wait(driver);
	}

	//@Test
	public void TC_031() {
		driver = TS1.TC_031_test_iframes_of_main_page(driver);
	}

	//@Test
	public void TC_033() {
		try {
			driver = TS1.TC_033_switching_among_windows_by_windowHandles(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) {
		if(testResult.getStatus()==ITestResult.FAILURE) {
			help.captureScreenshot(driver, "fAil"+testResult.getName());
		}
		else if(testResult.getStatus()==ITestResult.SUCCESS) {
			//on success capture no screencapture
			//help.captureScreenshot(driver, "Success_"+testResult.getName());
		}
	}

	@BeforeClass
	public void AA() {System.out.println("beforeclass");}

	@BeforeTest
	//@Parameters({"browser_pom","pomurl"})
	//public void OpenBrowser(String browsertestng)
	public void OpenBrowser() {
		//browsertestng form testng.xml is not used here.
		//Taking the environment variable from pom.xml
		log.info("The environment variable from pom:> "+environment);
		log.info("The type of browser from pom:> "+browser_pom);
		
		if(environment.equalsIgnoreCase("dev")) {
			//driver = TS1.openbrowser(driver, browser_pom, help.PropertyFileRead("devURL"));
			driver1 = TS1.openbrowser(driver, help.PropertyFileRead("Browser"), help.PropertyFileRead("devURL"));
			log.info("The type of dev URL from property file:> "+help.PropertyFileRead("devURL"));
			//xlxsfilepath = help.PropertyFileRead("devxlsxfile");
			log.info("The type of dev xlxsfile from property file:> "+xlxsfilepath);
		}else if(environment.equalsIgnoreCase("prod")) {
			//driver = TS1.openbrowser(driver, browser_pom, help.PropertyFileRead("prodURL"));
			driver1 = TS1.openbrowser(driver, help.PropertyFileRead("Browser"), help.PropertyFileRead("prodURL"));
			log.info("The type of prod URL from property file:> "+help.PropertyFileRead("prodURL"));
			xlxsfilepath = help.PropertyFileRead("prodxlsxfile");
			log.info("The type of prod xlxsfile from property file:> "+xlxsfilepath);
		
		}else if(environment.equalsIgnoreCase("uat")) {
			//driver = TS1.openbrowser(driver, browser_pom, help.PropertyFileRead("uatURL"));
			driver1 = TS1.openbrowser(driver, help.PropertyFileRead("Browser"), help.PropertyFileRead("uatURL"));
			log.info("The type of uat URL from property file:> "+help.PropertyFileRead("uatURL"));
			xlxsfilepath = help.PropertyFileRead("uatxlsxfile");
			log.info("The type of uat xlxsfile from property file:> "+xlxsfilepath);
		}

	}
	
	
	//@BeforeTest
	public void openbrowser() {
		//log.info("The pomurl from pom> "+pomurl);
		//driver = TS1.openbrowser(driver, browser_pom, pomurl);
		driver = TS1.openbrowser(driver, help.PropertyFileRead(browser_pom), help.PropertyFileRead(pomurl));
	}
	
	//@BeforeTest
	public void openbrowser1() {
		
		//driver1 = TS1.openbrowser(driver, "Chrome", "file:///D:/staticwebpage/JavaFirstParentPage.html");
		/*String browser=System.getProperty("browser_pom");
		System.out.println(browser);*/
		
		driver1 = TS1.openbrowser(driver, help.PropertyFileRead("Browser"), help.PropertyFileRead("devURL"));
		
	}
	@AfterTest
	public void Closebrowser1() {
		TS1.closebrowser(driver1);
	}

	//@AfterTest
	public void CloseBrowser() throws InterruptedException {
		Thread.sleep(2000);
		TS1.closebrowser(driver);
		System.out.println("beforeTest");
	}


	// Testng listeners examples --start--
	@Test(enabled = false)
	public void TestListen_1a() {System.out.println("TC inside Test_1a");}
	@Test(enabled = false)
	public void TestListen_2b() {System.out.println("TC inside Test_2b");}
	@Test(enabled = false)
	public void TestListen_3c() {
		Assert.assertFalse(true);
		System.out.println("TC inside Test_3c");}

	@Test(enabled = false)
	public void TestListen_testcaseSkipException_4d() {
		System.out.println("TC inside Test_4d");
		throw new SkipException("skipping this exception");
	}

	@Test(enabled = false)
	public void TestListen_testcaseConditionalSkipExecution_5e() throws InterruptedException {
		System.out.println("TC inside 5e");
		Thread.sleep(600);
		if(3>2) {
			throw new SkipException("Webelement is not present");
		}

	}

	@Test(timeOut=1000,enabled = false)
	public void TestListen_timeoutTest_6f() throws InterruptedException {
		System.out.println("TC 6f");
		Thread.sleep(5000);
	}

	// Testng listeners examples --end--





	//@DataProvider(name="usersdata")
	public Object[][] readerforBrowser() throws IOException{
		//String filepath=System.getProperty("user.dir")+ "/src/com/FW/Lib/"+help.PropertyFileRead("xlsfilenameA2");
		//String xlxsfilepath = help.PropertyFileRead("devxlsxfile");
		String sheetname="users";
		int noofRow=2;
		int noofcolumns=2;
		//the xlxsfilepath 
		String[][] data=exceldata.xlsxReaderNoRowsNoColumns(xlxsfilepath,sheetname,noofRow,noofcolumns); 	
		return data;

	}






}
