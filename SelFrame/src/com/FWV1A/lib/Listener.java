package com.FWV1A.lib;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.apache.log4j.Logger;
//import com.FWV1A.lib.HelperOne;

public class Listener implements ITestListener {
	static Logger log = Logger.getLogger(Listener.class);
	public Listener() {
		//HelperOne help = new HelperOne();
		DOMConfigurator.configure("./src/com/FWV1A/lib/log4j_FWV1A.xml");
		
	}

	
	public void onFinish(ITestContext Result) {
		log.info("L:: onFinish : "+Result.getName());
		Reporter.log("Listener> onFinish>"+Result.getName());
	}

	
	public void onStart(ITestContext Result) {
		System.out.println("L:: onStart : "+Result.getName());
		Reporter.log("L:: The name of the testcase from testng xml: "+Result.getName());

	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		System.out.println("L:: onTestFailedButWithinSuccessPercentage : "+Result.getName());
	}

	
	public void onTestFailure(ITestResult Result) {
		System.out.println("L:: onTestFailure : "+Result.getName());
	}

	
	public void onTestSkipped(ITestResult Result) {
		System.out.println("L:: onTestSkipped : "+Result.getName());
	}
	
	public void onTestStart(ITestResult Result) {
		log.info("L:: The name of the method: "+Result.getName());
	}
	
	public void onTestSuccess(ITestResult Result) {
		System.out.println("L:: onTestSuccess : "+Result.getName());
	}

}
