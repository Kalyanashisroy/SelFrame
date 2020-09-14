package com.Test_ng;

import org.testng.annotations.Test;

import com.Test_ng.FullScreenCapture;

import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
public class parametertest {
	FullScreenCapture screencap;
	
	@Test
	@Parameters({"a","b"})
	public void f(int a1, int b1) {
		System.out.println("Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());

		int sum = a1+b1;
		System.out.println("the sum is :"+sum);
	}

	@AfterMethod
	@Parameters({"cc","dd"})
	public void afterMethod(int cc1,int dd1) {
		System.out.println("Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());

		int multiply = cc1*dd1;
		System.out.println("The result of multiply is:"+multiply);
		
	}

	@AfterClass
	public void afterClass() {
	}

	@AfterTest
	public void afterTest() {
	}

	// Testng listeners examples --start--
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

	@Test(enabled = true)
	public void TestListen_testcaseConditionalSkipExecution_5e() throws InterruptedException {
		System.out.println("TC inside 5e");
		Thread.sleep(600);
		if(3>2) {
			throw new SkipException("Webelement is not present");
		}

	}

	@Test(timeOut=1000,enabled = true)
	public void TestListen_timeoutTest_6f() throws InterruptedException {
		System.out.println("TC 6f");
		Thread.sleep(5000);
	}

	// Testng listeners examples --end--

@AfterMethod
public void screenCaptureOnFailure(ITestResult result) {
	if(result.getStatus() == ITestResult.FAILURE) {
		screencap = new FullScreenCapture();
		screencap.robotScreenShot();
	}
	
}


}
