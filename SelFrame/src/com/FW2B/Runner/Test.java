package com.FW2B.Runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.FW2B.TC.*;
public class Test {
	static WebDriver driver;
	static TestCases_One TC1;
	static WebDriver driver1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.gecko.driver", "D:/selenium/geckodriver.exe");
		/*System.setProperty("webdriver.gecko.driver", "D:/selenium/geckodriver-v0.27.0-win64/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("file:///D:/staticwebpage/JavaFirstParentPage.html");*/
		TC1=new TestCases_One();
		driver=TC1.openbrowser(driver1, "Chrome", "file:///D:/staticwebpage/JavaFirstParentPage.html");
		driver=TC1.TC_002_test_alllinks_of_main_page(driver1);
	}

}
