package com.FWV1A.runner;

import org.apache.log4j.Logger;



import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.FWV1A.testcases.*;

/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
*/public class Extent_Run {
	String pathOfextentHtml = "D:/extentR.html";
	static Logger log4j = Logger.getLogger(Extent_Run.class);
	WebDriver driver;
	TestCases_for_Jenkins tc;

	public Extent_Run() {
		DOMConfigurator.configure("./src/com/FWV1A/lib/log4j_FWV1A.xml");
		tc = new TestCases_for_Jenkins();
	}
	/*
	 * ExtentReports extent; ExtentHtmlReporter htmlReporter; ExtentTest test;
	 * 
	 * @BeforeTest public void startReport() { htmlReporter = new
	 * ExtentHtmlReporter(pathOfextentHtml); extent = new ExtentReports();
	 * extent.attachReporter(htmlReporter); extent.setSystemInfo("OS", "windows7");
	 * extent.setSystemInfo("Browser", "Chrome");
	 * 
	 * htmlReporter.config().setChartVisibilityOnOpen(true);
	 * htmlReporter.config().setDocumentTitle("Extent Demo ");
	 * htmlReporter.config().setReportName("TEst report");
	 * //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	 * htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	 * htmlReporter.config().setTheme(Theme.DARK); //htmlReporter.config().
	 * setTimeStampFormat("EEE, MMMM dd,yyyy,hh:mm a '('zzz')'");
	 * htmlReporter.config().setEncoding("UTF-8");
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @AfterMethod public void getResult(ITestResult result) {
	 * if(result.getStatus() == ITestResult.SUCCESS) { test.log(Status.PASS,
	 * MarkupHelper.createLabel(result.getName()
	 * +"AfterMethod: This testcase is passed.",ExtentColor.GREEN)); }
	 * 
	 * 
	 * }
	 * 
	 * @AfterTest public void tearDown() { extent.flush(); }
	 * 
	 * 
	 * // Testng listeners examples --start--
	 * 
	 * @Test(enabled = true) public void TestListen_1a() { test =
	 * extent.createTest("Testcase 1 halo ","TEst case is passed 123");
	 * System.out.println("TC inside Test_1a");} //@Test(enabled = true) public void
	 * TestListen_2b() { test =
	 * extent.createTest("Testcase 2","TEst case is passed");
	 * System.out.println("TC inside Test_2b");}
	 * 
	 * @Test public void test3() {test = extent.createTest("test3","test3 passed");}
	 * 
	 * @Test public void test4() {test = extent.createTest("test4","test4 passed");}
	 * 
	 * @Test public void test5() {test = extent.createTest("test5","test5 passed");}
	 * 
	 * @Test public void test6() {test = extent.createTest("test6","test6 passed");}
	 * 
	 * 
	 * 
	 * //@Test(enabled = true) public void TestListen_3c() {
	 * Assert.assertFalse(true); System.out.println("TC inside Test_3c");}
	 * 
	 * //@Test(enabled = true) public void TestListen_testcaseSkipException_4d() {
	 * System.out.println("TC inside Test_4d"); throw new
	 * SkipException("skipping this exception"); }
	 * 
	 * //@Test(enabled = true) public void
	 * TestListen_testcaseConditionalSkipExecution_5e() throws InterruptedException
	 * { System.out.println("TC inside 5e"); Thread.sleep(600); if(3>2) { throw new
	 * SkipException("Webelement is not present"); }
	 * 
	 * }
	 * 
	 * //@Test(timeOut=1000,enabled = true) public void TestListen_timeoutTest_6f()
	 * throws InterruptedException { //System.out.println("TC 6f");
	 * Thread.sleep(5000); }
	 * 
	 * // Testng listeners examples --end--
	 * 
	 * 
	 * 
	 */
}
