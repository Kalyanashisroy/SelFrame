package com.FW2B.Lib;

import org.testng.ITestContext;



import org.testng.ITestListener;
import org.testng.ITestResult;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Listener implements ITestListener {
	static Logger log = Logger.getLogger(Listener.class.getName());
	Helper help;
	public Listener() {
		help = new Helper(); 
		//DOMConfigurator.configure("log4j_FW_staticPage.xm");
		DOMConfigurator.configure(help.PropertyFileRead("log4jxmlpath"));
	}
	/*public static void main(String[] args) {

	}*/
	public void onFinish(ITestContext Result) {
		System.out.println("L:: onFinish : "+Result.getName());
		log.info("L:: onFinish : "+Result.getName());
		
	}

	
	public void onStart(ITestContext Result) {
		System.out.println("L:: onStart : "+Result.getName());
		log.info("L:: onStart : "+Result.getName());
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
		System.out.println("L:: onTestStart : "+Result.getName());
	}
	
	public void onTestSuccess(ITestResult Result) {
		System.out.println("L:: onTestSuccess : "+Result.getName());
	}



	public void insidelisten() {System.out.println("i am inside listener_1");}	
}
