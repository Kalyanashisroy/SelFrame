package com.FWL1.lib;

import org.apache.log4j.xml.DOMConfigurator;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


import org.apache.log4j.Logger;
import com.FWL1.lib.Helper;

public class Listener implements ITestListener {
	static Logger log = Logger.getLogger(Listener.class);
	Helper help;
	public Listener() {
		help = new Helper();
		String projectPath = System.getProperty("user.dir");
		DOMConfigurator.configure(projectPath+help.getValueFrom("log4jxmlpath"));
	}

	
	public void onFinish(ITestContext Result) {
		log.info("L:: onFinish : "+Result.getName());
		Reporter.log("Listener> onFinish>"+Result.getName());
	}

	
	public void onStart(ITestContext Result) {
		//System.out.println("L:: onStart : "+Result.getName());
		Reporter.log("L:: The name of the testcase from testng xml: "+Result.getName());
		log.info("L:: The name of the testcase from testng xml: "+Result.getName());

	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		//System.out.println("L:: onTestFailedButWithinSuccessPercentage : "+Result.getName());
		log.info("L:: onTestFailedButWithinSuccessPercentage : "+Result.getName());
	}

	
	public void onTestFailure(ITestResult Result) {
		//System.out.println("L:: onTestFailure : "+Result.getName());
		log.info("L:: onTestFailure : "+Result.getName());
	}

	
	public void onTestSkipped(ITestResult Result) {
		//System.out.println("L:: onTestSkipped : "+Result.getName());
		log.info("L:: onTestSkipped : "+Result.getName());
	}
	
	public void onTestStart(ITestResult Result) {
		log.info("L:: The name of the method: "+Result.getName());
	}
	
	public void onTestSuccess(ITestResult Result) {
		//System.out.println("L:: onTestSuccess : "+Result.getName());
		log.info("L:: onTestSuccess : "+Result.getName());
	}

}
