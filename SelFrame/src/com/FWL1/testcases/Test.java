package com.FWL1.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.FWL1.lib.Helper;

public class Test {
	private String reportFileName = "jenkin-report";
	
	private String getDateAsString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static void main(String[] args) {
		Test t=new Test();
		//System.out.println("jenkin-report.html_"+t.getDateAsString());
		System.out.println("jenkin-report_"+t.getDateAsString()+".html");
		//System.out.println(System.getProperty("user.dir")+"/Reports/Test_"+t.getDateAsString()+".html");
		
	}
	

}
