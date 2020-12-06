package demo2b.DemoTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class FileUpload {
	
	@Test
	public void testFileUpload() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\Browser\\chromedriver_win32_86\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		/*System.setProperty("webdriver.gecko.driver", "D:\\Browser\\geckodriver-v0.15.0-win64\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();*/
		driver.manage().window().maximize();
		driver.get("file:///D:/FileUpload/fileupload.html");
		//driver.findElement(By.xpath(".//*[@id='1']")).click();
		driver.findElement(By.name("resumeupload")).click();
		Thread.sleep(5000);
		//Runtime.getRuntime().exec("D:\\FileUpload\\Upload.exe");
		//Runtime.getRuntime().exec("C:\\Users\\KALYANASHIS\\Desktop\\AutoIT\\Upload.exe");
		Runtime.getRuntime().exec("C:\\Users\\KALYANASHIS\\Desktop\\AutoIT\\Upload1.exe");
	}
}
