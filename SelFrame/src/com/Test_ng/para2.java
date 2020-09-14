package com.Test_ng;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;

public class para2 {
	@Test
	@Parameters({"a","b"})
	public void f1234(int a1, int b1) {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());

		int sum = a1+b1+a1;
		System.out.println("para2: the sum is :"+sum);
	}
	@Test
	public void tc_002() {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());
		System.out.println(" passed:tc_002");
	}
	@Test
	public void tc_003() {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());
		System.out.println(" passed:tc_003");
	}
	@Test
	public void tc_004() {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());
		System.out.println(" passed:tc_004");
	}

	@AfterMethod
	@Parameters({"cc","dd"})
	public void afterMethod(int cc1,int dd1) {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());

		int multiply = 2*(cc1*dd1);
		System.out.println("para2:The result of multiply is:"+multiply);
	}

	@AfterClass
	public void afterClass() {
	}

	@AfterTest
	public void afterTest() {
	}

}
