package demo2b.TestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.common.util.concurrent.CycleDetectingLockFactory.Policies;

import demo2b.Libs.BrowserFactory;
import demo2b.Libs.ConfigDataProvider;
import demo2b.PageObjects.PageObject_Lesson_1;

public class TestCases_Lesson_1 {
	public WebDriver driver;
	BrowserFactory browfact;
		
	public TestCases_Lesson_1(WebDriver driver){
		browfact = new BrowserFactory();
		
	}
	
	public WebDriver openbrowser(WebDriver driver,String browsername,String url) {
		driver =browfact.startApplication(driver, browsername, url);
		return driver;
	}
	
	public WebDriver closebrowser(WebDriver driver) {
		
		driver.close();
		return driver;
	}
	public WebDriver TC_001(WebDriver driver1) {
		driver1.findElement(By.id("firstname")).sendKeys("Halo friend how are  you ?");
		return driver1;
	}

}
