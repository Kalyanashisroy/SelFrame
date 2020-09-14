package com.Test_ng;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;

public class group_school {
	@Test(groups= {"RegressionTest"})
	public void tc_001() {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());
		System.out.println(" passed:tc_001");
	}

	
	
	@Test(groups= {"SmokeTest"})
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
	@Test(groups= {"SmokeTest"})
	public void tc_004() {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());
		System.out.println(" passed:tc_004");
	}

	@Test(groups= {"RegressionTest"})
	public void tc_005() {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());
		System.out.println(" passed:tc_005");
	}

	@Test(groups= {"RegressionTest","SanityTest"})
	public void tc_reg_san1() {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());
		System.out.println(" passed:tc_reg_san2");
	}

	@Test(groups= {"RegressionTest","SanityTest"})
	public void tc_reg_san2() {
		System.out.println("Para2:Thread ID: "+Thread.currentThread().getName()+" "
				+Thread.currentThread().getId());
		System.out.println(" passed:tc_reg_san2");
	}

	
	
	@AfterMethod
	public void afterMethod(int cc1,int dd1) {
	}

	@AfterClass
	public void afterClass() {
	}

	@AfterTest
	public void afterTest() {
	}

}
