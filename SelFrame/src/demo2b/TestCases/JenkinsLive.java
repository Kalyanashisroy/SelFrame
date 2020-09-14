package demo2b.TestCases;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JenkinsLive {
	public static WebDriver driver;
	
	/*public static void main(String[] args) throws InterruptedException {
		JenkinsLive jl=new JenkinsLive();
		driver=jl.LoginPage(driver);
		//driver=jl.ClickBuildHistory(driver);
		//driver=jl.ClickProjectRelationship(driver);
		//driver=jl.JenkinsSuite1(driver);
		//driver=jl.JenkinsSuite2(driver);
		//driver=jl.ClickCheckFileFingerprint(driver);
		driver=jl.ClickMyViews(driver);
		//driver=jl.ClickCredentials(driver);
		//driver=jl.LogoutPage(driver);
		//driver.close();
	}*/
	//Login with username and passward
	public WebDriver LoginPage(WebDriver driver) {
		System.setProperty("webdriver.chrome.driver", "D:\\Browser\\chromedriver_win32_85\\chromedriver.exe" );
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/login?from=%2F");
		WebElement eleuser=driver.findElement(By.id("j_username"));
		eleuser.sendKeys("Kalyan");
		if(driver.findElement(By.id("j_username"))!=null){
			System.out.println("Element is present");
		}else {
			System.out.println("Element is not present");
		}
		WebElement elepass=driver.findElement(By.name("j_password"));
		elepass.sendKeys("pwd@12345");
		if(driver.getPageSource().contains("pwd@12345")) {
			System.out.println("Text is present");
		}else {
			System.out.println("Text is not present");
		}
		if(driver.getPageSource().contains("Sign in")) {
			System.out.println("Text is present");
		}else {
			System.out.println("Text is not present");
		}
		WebElement elesign=driver.findElement(By.name("Submit"));
		elesign.click();
		return driver;
		
	}
	//New item
	public WebDriver ClickOnNewItem(WebDriver driver) {
		WebElement eleitem=driver.findElement(By.xpath("(//*[contains(@href,'/view/all/newJob')])[2]"));
		eleitem.click();
		//Assert.assertEquals(true, eleitem.isDisplayed());
		return driver;
		
		
	}
	
	//Enter an item name
	public WebDriver EnterAnItemName(WebDriver driver) {
		WebElement eleItemName=driver.findElement(By.xpath("//input[@id='name']"));
		eleItemName.sendKeys("JenkinsLiveProjects");
		return driver;
		
	}
	//Select freestyleproject
	public WebDriver SelectFreestyleProject(WebDriver driver) {
		WebElement eleSctFree=driver.findElement(By.xpath("//span[contains(text(),'Freestyle project')]"));
		eleSctFree.click();
		return driver;
	}
	//After select freestyleproject clicking on ok button
	public WebDriver ClickOkButton(WebDriver driver) {
		WebElement eleOkbtn=driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
		eleOkbtn.click();
		Assert.assertEquals(true, eleOkbtn.isDisplayed());
		
		return driver;
		
	}	
	//Search created item on main search page
	public WebDriver SearchCreatedItem(WebDriver driver) throws InterruptedException {
		WebElement elesearch=driver.findElement(By.xpath("//div[@id='searchform']//input[@name='q']"));
		elesearch.sendKeys("JenkinsLiveProjects",Keys.ENTER);
		Thread.sleep(2000);
		return driver;
	}
	//Build now
	public WebDriver ClickOnBuildNow(WebDriver driver) {
		WebElement elebuildnow=driver.findElement(By.xpath("//a[contains(text(),'Build Now')]"));
		elebuildnow.click();
		return driver;
		
	}
	//Delete created project
	public WebDriver DeleteProject(WebDriver driver) throws InterruptedException {
		WebElement eledeletepro=driver.findElement(By.xpath("//a[contains(text(),'Delete Project')]"));
		eledeletepro.click();
		Thread.sleep(600);
		Alert alt=driver.switchTo().alert();
		alt.accept();
		return driver;
		
	}
	//Build history
	public WebDriver ClickBuildHistory(WebDriver driver) {
		WebElement elebuildhis=driver.findElement(By.xpath("//a[@title='Build History']"));
		elebuildhis.click();
		return driver;
		
	}
	//Project relationship
	public WebDriver ClickProjectRelationship(WebDriver driver) {
		WebElement eleprojectrel=driver.findElement(By.xpath("//a[@title='Project Relationship']"));
		eleprojectrel.click();
		//upstream project 
		WebDriverWait wait=new WebDriverWait(driver, 10);
		//WebElement eleupstream=driver.findElement(By.xpath("//input[@name='lhs']"));
		WebElement eleupstream=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='lhs']")));
		eleupstream.sendKeys("TestSelenium");
		//downstream project
		WebElement eledownstream=driver.findElement(By.xpath("//input[@name='rhs']"));
		eledownstream.sendKeys("CucumberFramework");
		//compare 
		WebElement elecompare=driver.findElement(By.xpath("//button[@id='yui-gen7-button']"));
		elecompare.click();
		
		return driver;
		
	}
	//Check file fingerprint
	public WebDriver ClickCheckFileFingerprint(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement elecheckfilefing=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Check File Fingerprint']")));
		elecheckfilefing.click();
		
		/*WebDriverWait wait1=new WebDriverWait(driver, 10);
		WebElement elefiletocheck=wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='file']")));
		elefiletocheck.click();
		 */		
		WebElement eleupload=driver.findElement(By.xpath("//input[@type='file']"));
		eleupload.sendKeys("D:\\Additional\\Info\\Resume\\New2020\\Screening -QA Engineer (SFDC).docx");
		
		//clicking check button
		WebElement elecheckbtn=driver.findElement(By.xpath("//button[@id='yui-gen7-button']"));
		elecheckbtn.click();
		
		//Clicking back to dashboard link
		WebElement elebacktodash=driver.findElement(By.xpath("//a[@title='Back to Dashboard']"));
		elebacktodash.click();
		
		return driver;
		
	}
	//Manage jenkins
	public WebDriver ClickManageJenkins(WebDriver driver) throws InterruptedException {
		WebElement elemanage=driver.findElement(By.xpath("//a[@title='Manage Jenkins']"));
		elemanage.click();
		Thread.sleep(2000);
		return driver;
		
	}
	//Configure system
	public WebDriver ClickConfigureSystem(WebDriver driver) throws InterruptedException {
		WebElement eleconfigure=driver.findElement(By.xpath("//a[@title='Configure System']"));
		eleconfigure.click();
		//Thread.sleep(500);
		//Clicking on apply button
		WebElement eleapplybtn=driver.findElement(By.xpath("//button[text()='Apply']"));
		boolean status=eleapplybtn.isEnabled();
		System.out.println(status);
		//eleapplybtn.click();
		Thread.sleep(2000);
		//Assert.assertEquals(true, eleapplybtn.isEnabled());
		//Clicking on save button
		WebElement elesavebtn=driver.findElement(By.xpath("//button[text()='Save']"));
		elesavebtn.click();
		//Thread.sleep(2000);
		//Assert.assertEquals(true, elesavebtn.isDisplayed());
		//Thread.sleep(500);
		return driver;
		
	}
	//My Views
	public WebDriver ClickMyViews(WebDriver driver) {
		WebElement eleMyViews=driver.findElement(By.xpath("//a[@title='My Views']"));
		eleMyViews.click();
		//My views click + tab 
		WebElement eleplustab=driver.findElement(By.xpath("//a[@class='addTab']"));
		eleplustab.click();
		//View name
		WebElement eleviewname=driver.findElement(By.xpath("//input[@id='name']"));
		eleviewname.sendKeys("TestRun");
		//Select radio button which view you want to select
		WebElement eleIncludeGobal=driver.findElement(By.xpath("//label[@class='attach-previous']//b[contains(text(),'Include a global view')]"));
		eleIncludeGobal.click();
		//Click ok button after enter view name
		WebElement eleOkbtnView=driver.findElement(By.xpath("//button[@id='ok-button']"));
		eleOkbtnView.click();
		return driver;
		
	}
	//Credentials
	public WebDriver ClickCredentials(WebDriver driver) throws InterruptedException {
		
		WebElement elecredent=driver.findElement(By.xpath("//a[@title='Credentials']"));
		elecredent.click();
		//Click system 
		WebElement elesystem=driver.findElement(By.xpath("//a[@title='System']"));
		elesystem.click();
		//Click Add domain
		WebElement eleadddomain=driver.findElement(By.xpath("//a[@title='Add domain']"));
		eleadddomain.click();
		//Domain name
		WebElement eledomainname=driver.findElement(By.xpath("//input[@type='text']"));
		eledomainname.sendKeys("Healthcare");
		//Description
		WebElement eledescription=driver.findElement(By.xpath("//textarea[@name='description']"));
		eledescription.sendKeys("Hi welcome to healthcare domain");
		//Specification click add button
		WebElement elespecadd=driver.findElement(By.xpath("//button[@id='yui-gen1-button']"));
		elespecadd.click();
		
		//Clicking URI Scheme
		WebElement eleurischeme=driver.findElement(By.xpath("//a[contains(text(),'URI scheme')]"));
		eleurischeme.click();
		//Click ok 
		WebElement eleokbtn=driver.findElement(By.xpath("//button[@id='ok-button']"));
		eleokbtn.click();
		
		return driver;
		
	}
	//Logout from application
	public WebDriver LogoutPage(WebDriver driver) throws InterruptedException {
		WebElement elelogout=driver.findElement(By.xpath("//span[contains(text(),'log out')]"));
		//elelogout.sendKeys(Keys.F5);
		//Thread.sleep(500);
		//WebElement elelogout1=driver.findElement(By.xpath("//span[contains(text(),'log out')]"));
		elelogout.click();
		return driver;
		
	}
	//New item,enter an item, select free style project,search item,
	//build now,delete project
	public WebDriver JenkinsSuite1(WebDriver driver) throws InterruptedException {
		JenkinsLive jl=new JenkinsLive();
		driver=jl.ClickOnNewItem(driver);
		driver=jl.EnterAnItemName(driver);
		driver=jl.SelectFreestyleProject(driver);
		driver=jl.ClickOkButton(driver);
		Thread.sleep(500);
		driver=jl.SearchCreatedItem(driver);
		Thread.sleep(500);
		driver=jl.ClickOnBuildNow(driver);
		Thread.sleep(2000);
		driver=jl.DeleteProject(driver);
		Thread.sleep(2000);

		return driver;
		
	}
	//Manage jenkins,configure system, apply and save button
	public WebDriver JenkinsSuite2(WebDriver driver) throws InterruptedException {
		JenkinsLive jl=new JenkinsLive();
		driver=jl.ClickManageJenkins(driver);
		driver=jl.ClickConfigureSystem(driver);
		return driver;
		
	}
	
	
}
