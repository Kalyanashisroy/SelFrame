package com.Test_ng;

import org.testng.annotations.Test;

public class TestRun_01 {
	String abc;
	
	public TestRun_01() {
		System.out.println("Without parameterized constructor");
	}
	
	public TestRun_01(String str) {
		abc=str;
		System.out.println("Parameterized constructor:"+str);
	}
	
	@Test
	public void Verify(){
		
		System.out.println("I am inside TestNG:"+abc);
		
	}
	
		
	
}
