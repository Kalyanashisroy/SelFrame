package com.Test_ng;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.FW2B.Lib.Helper;
public class Listener implements ITestListener {
	Helper help;
	
	public static void main(String[] args) {

	}

	
	public void onFinish(ITestContext Result) {
		System.out.println("listener::onFinish :"+Result.getName());	
	}
	
	
	public void onStart(ITestContext Result) {
		System.out.println("listener::onStart:"+Result.getName());
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {}
	
	
	public void onTestFailure(ITestResult Result) {
		System.out.println("listener::the name of the testcase failed is:"+Result.getName());
		
	}
	
	
	public void onTestSkipped(ITestResult Result) {
		System.out.println("listener::the name of the testcase skipped is:"+Result.getName());
	}
	
	public void onTestStart(ITestResult Result) {
		System.out.println("listener::ONTEstStart started is:"+Result.getName());
	}
	
	public void onTestSuccess(ITestResult Result) {
		System.out.println("listener::the name of the testcase passed is:"+Result.getName());
		
	}

	
	
public void insidelisten() {System.out.println("i am inside listener_1");}	
}
