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

public class Extent_Arora {
	String pathOfextentHtml = "D:/extentR.html";
	static Logger log4j = Logger.getLogger(Extent_Arora.class);
	WebDriver driver;
	TestCases_for_Jenkins tc;

	public Extent_Arora() {
		DOMConfigurator.configure("./src/com/FWV1A/lib/log4j_FWV1A.xml");
		tc = new TestCases_for_Jenkins();
	}
	


//Testng listeners examples --start--
	@Test(enabled = true)
	public void TestListen_1a() {System.out.println("TC inside Test_1a");}
	@Test(enabled = true)
	public void TestListen_2b() {System.out.println("TC inside Test_2b");}
	@Test(enabled = true)
	public void TestListen_3c() {
		Assert.assertFalse(true);
		System.out.println("TC inside Test_3c");}

	@Test(enabled = true)
	public void TestListen_testcaseSkipException_4d() {
		System.out.println("TC inside Test_4d");
		throw new SkipException("skipping this exception");
	}

	//@Test(enabled = true)
	public void TestListen_testcaseConditionalSkipExecution_5e() throws InterruptedException {
	
		
		System.out.println("TC inside 5e");
		Thread.sleep(600);
		if(3>2) {
			throw new SkipException("Webelement is not present");
		}

	}

	//@Test(timeOut=1000,enabled = true)
	public void TestListen_timeoutTest_6f() throws InterruptedException {
		//System.out.println("TC 6f");
		Thread.sleep(5000);
	}

	// Testng listeners examples --end--


}
