package com.FWL1.runner;

import org.testng.annotations.Test;

public class Test1 {
	static int flag=3;
	static String var;
	public static void main(String[] args) {
		if(flag==0) {
			System.out.println("No method");
		}
		else if(flag==1) {
			Test1.photo_on_failure(var);
		}
		else if(flag==2) {
			Test1.photo_on_success(var);
		}
		else if(flag==3) {
			Test1.photo_on_success(var);
			Test1.photo_on_failure(var);
		}
	}
	public static String photo_on_success(String var) {
		if(var.equals("Yes")) {
			Test1.photo_on_failure(var);
		}
		else if(var.equals("No")) {
			Test1.photo_on_failure(var);
		}
		return var;
		
	}
	public static String photo_on_failure(String var) {
		System.out.println("This is photo on failure");
		
		return var;
		
	}
	public static String run1(String var) {
		if(var.equals("Yes")) {
			System.out.println(Test1.run2(var));
		}
		
		return var;
		
	}
	public static String run2(String var) {
		return var;
		
	}
}
