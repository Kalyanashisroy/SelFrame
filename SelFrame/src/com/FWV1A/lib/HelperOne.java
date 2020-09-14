package com.FWV1A.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class HelperOne {
String propertyfilepath="./src/com/FWV1A/lib/propertyfile.properties";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

public static  void test() {
	//getValueFrom("chromepath");
	System.out.println(new HelperOne().getValueFrom("chromepath"));
	System.out.println(new HelperOne().getValueFrom("browserType"));
}
	
public String getValueFrom(String key) {
	Properties prop = new Properties();
	File file = new File(propertyfilepath);
	try {
		FileInputStream fis = new FileInputStream(file);
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return prop.getProperty(key);
}	
}
