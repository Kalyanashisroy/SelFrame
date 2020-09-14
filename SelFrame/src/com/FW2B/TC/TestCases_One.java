package com.FW2B.TC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.FW2B.PO.*;
import com.FW2B.Lib.*;
public class TestCases_One {
	static Logger log = Logger.getLogger(TestCases_One.class.getName());
	BrowserFactory browfact;
	PO_Parent ppage;
	ExcelDataRW excel;
	Helper help;
	Listener listen;
	public TestCases_One() {
		browfact = new BrowserFactory();
		excel = new ExcelDataRW();
		help = new Helper();
		listen = new Listener();
		DOMConfigurator.configure("log4j_FW_staticPage.xml");
		DOMConfigurator.configure(help.PropertyFileRead("log4jxmlpath"));
	}

	/*public static void main(String[] args) {

	}*/

	public void TC_AA_11() {
		System.out.println("i am inside TestSuite--> TC_AA_11");
		//calling parent Pom 
		ppage.pageobject1();
		//calling browser factory
		browfact.bfact();
		//calling listener
		listen.insidelisten();
	}	

	public WebDriver openbrowser(WebDriver driver,String browser,String url) {
		driver = browfact.startApplication(driver, browser, url);
		log.info("Starting the browser");
		log.info("creating object for parent page");
		driver = createPpageObject(driver);
		log.info("Create done object for parent page");
		return driver;
	}
	public WebDriver closebrowser(WebDriver driver) {
		log.info("Closing the browser");
		driver.close();
		//to close all opened windows
		driver.quit();
		log.info("Closed and quit the browser");
		return driver;
	}

	public WebDriver createPpageObject(WebDriver driver) {
		ppage = new PO_Parent(driver);
		log.info("Created parent page object");
		return driver;
	}


	public WebDriver TC_030_Polling_by_Explicit_Wait(WebDriver driver) {
		log.info("method: Polling_by_Explicit_Wait --start--");
		//System.out.println("2.before Click the button:: "+driver.findElement(By.id("timRand001")).getText() );
		log.info("2.before Click the button:: "+driver.findElement(By.id("timRand001")).getText() );
		ppage.button.click();
		//driver.findElement(By.id("butt0012")).click();
		log.info("click the button");
		long timestart = System.currentTimeMillis();
		//ppage.timerand.getText();
		//String ele =ppage.timerand.getText();
		String ele = driver.findElement(By.id("timRand001")).getText();
		//System.out.println("2.After Click the button ::"+ele);
		log.info("2.After Click the button ::"+ele);

		long timeoutinsecondT=10;  // it will not wait till 10 sec if element is found
		long sleepinMilliT=50;   // it will wait even 15 sec if the element is found. Increase it to 15 sec and see.
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,sleepinMilliT);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		long timefinish= System.currentTimeMillis();
		long timeelasped = timefinish - timestart;
		System.out.println("2.the time taken click-accept alert :: "+timeelasped+" milliseconds");
		System.out.println("hello all bivas tuesday");
		System.out.println(driver.getTitle());
		log.info("2.the time taken click-accept alert :: "+timeelasped+" milliseconds");
		log.info("The title of the page is::"+driver.getTitle());
		log.info("method: Polling_by_Explicit_Wait --ended--");

		return driver;
	}


	public WebDriver TC_001_ToTesttheinput(WebDriver driver,String textinput)  {
		log.info("TC_001 started");
		//driver.findElement(By.id("firstname")).sendKeys("helo pineapple");
		//PO factory is initialised --it should be called after browser launch
		//ppage = new PO_Parent(driver);
		//highlight with red
		driver = help.highlightElement(driver, ppage.inputtext, "red");
		log.info("TC_001 highlight Element done");
		ppage.inputtext.sendKeys(textinput);
		//alert is created by javascriptexecutor
		//driver = help.JSExecutor(driver);
		//The alert of javascriptexecutor is accepted
		//driver.switchTo().alert().accept();
		//driver = help.captureScreenshot(driver, "q1_2");
		log.info("TC_001 ended");
		return driver;
	}




	public WebDriver TC_002_test_alllinks_of_main_page(WebDriver driver) {
		log.info("TC- test_alllinks_of_main_page- started");
		String parentpage = driver.getCurrentUrl();
		//System.out.println("the id of parent page:"+parentpage);
		List<WebElement> listforWebElement = driver.findElements(By.tagName("a"));
		List<String> listforurl=new ArrayList<String>() ;
		for(int getrow=0;getrow<listforWebElement.size();getrow=getrow+1) {
			//System.out.println(listforWebElement.get(getrow).getText());
			listforurl.add( listforWebElement.get(getrow).getAttribute("href"));
		}
		for(int i=0;i<listforurl.size();i++) {
			//System.out.println("the url::"+listforurl.get(i));
			driver.get(listforurl.get(i));

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");// This is not working
		}
		//Switch to parent page
		driver.get(parentpage);
		log.info("TC- test_alllinks_of_main_page- ended");
		return driver;

	}


	public  WebDriver TC_031_test_iframes_of_main_page(WebDriver driver) {
		String parentpage = driver.getCurrentUrl();
		List<WebElement> listt = driver.findElements(By.tagName("iframe"));
		//System.out.println("the total number of iframes are::"+listt.size());
		//GO TO IFRAME NUMBER 1
		String parent = driver.getWindowHandle();
		driver.switchTo().frame("number11"); // by id
		String variframeh=driver.findElement(By.cssSelector("h1")).getText();
		System.out.println("TTTTT"+variframeh);
		//now navigating to page webpage_1.html
		driver = table_Data_ByCssLocator_for_webpage_1(driver);
		driver.switchTo().window(parent);
		System.out.println("___________________________________________________");

		//switching to iframe2
		driver.switchTo().frame("number2"); //by id

		String variframe2h = driver.findElement(By.cssSelector("h1")).getText();
		//System.out.println("YYYYYY:: "+variframe2h);
		driver.switchTo().window(parent);
		//for main page
		//System.out.println("===============for parent page=============");
		driver = test_xpath_by_AND_Contains(driver);
		//System.out.println("====parent page end========");
		//Switch to parent page
		driver.get(parentpage);
				
		return driver;
	}

	public WebDriver table_Data_ByCssLocator_for_webpage_1(WebDriver driver) {


		//TO FIND THE FIRST ROW OF THE TABLE BY CSS SELECTOR
		String cell1 = driver.findElement(By.cssSelector(" table#table99   tr:nth-of-type(1)   td:nth-of-type(1)   ")).getText();
		String cell2 = driver.findElement(By.cssSelector(" table#table99   tr:nth-of-type(1)   td:nth-of-type(2)   ")).getText();
		String cell3 = driver.findElement(By.cssSelector(" table#table99   tr:nth-of-type(1)   td:nth-of-type(3)   ")).getText();
		String cell4 = driver.findElement(By.cssSelector(" table#table99   tr:nth-of-type(1)   td:nth-of-type(4)   ")).getText();
		System.out.println("the first row is::");
		System.out.println("	"+cell1+"	"+cell2+"	"+cell3+"	"+cell4);
		System.out.println("-----------------------------------------------------------");

		//DYNAMIC TABLE. TO FIND ALL THE ROW AND COLUMN
		List<WebElement> totalnumberofrows = driver.findElements(By.cssSelector("table#table99 tr"));
		List<WebElement> totalnumberofcolumns = driver.findElements(By.cssSelector("table#table99  tr:first-child  td  "));
		for(int rowcounter=1;rowcounter<=totalnumberofrows.size();rowcounter++) {
			for(int columncounter=1;columncounter<=totalnumberofcolumns.size();columncounter++) {
				String cell=driver.findElement(By.cssSelector(" table[width='50%']	tr:nth-of-type("+rowcounter+")	td:nth-of-type("+columncounter+") ")).getText();
				System.out.print("	"+cell);
			}
			System.out.println("");


		}

		return driver;
	}

	public WebDriver test_xpath_by_AND_Contains(WebDriver driver) {


		driver.findElement(By.xpath("//input[@class=\"classOne123\" " +
				"and @style=\"display:block\" and " +
				"@placeholder=\"haloplaceKK\" ]")).sendKeys("MayaPUR");


		driver.findElement(By.xpath("//input [contains(@class,'One123')    ]")).sendKeys("BelurMath");
		driver.findElement(By.xpath("//* [contains(@class,'One123')    ]")).sendKeys("BelurMath");

		return driver;
	}






	public WebDriver TC_033_switching_among_windows_by_windowHandles(WebDriver driver) throws InterruptedException {

		/*
		 * driver=new ChromeDriver(); driver.manage().timeouts().implicitlyWait(5,
		 * TimeUnit.SECONDS); driver.get(url);
		 */
		String parentWindow = driver.getWindowHandle();  // It hold the ID of first page or parent page.

		driver.findElement(By.xpath("/html/body/div/li[4]/a")).click();	// clicking the url
		driver.findElement(By.xpath("/html/body/div/li[3]/a")).click();	// clicking the url

		Set<String> childWindowId = driver.getWindowHandles(); // Storing all ID to 'Set' variable.
		System.out.println("@@@HALO## : "+childWindowId);
		int numberofwindows = childWindowId.size();   // counting the number of child windows.

		String[] noofstrAr = new String[numberofwindows]; //Creating the number of 'string' array
		String[] childWarrays = childWindowId.toArray(noofstrAr); // Storing all child window 'set' to string array . And assigning to string array variable. 

		//Switching windows based on 'childWarrays' array index.
		driver.switchTo().window(parentWindow);
		System.out.println("base");
		Thread.sleep(1000);
		driver.switchTo().window(childWarrays[1]);
		Thread.sleep(1000);
		driver.switchTo().window(parentWindow);
		Thread.sleep(1000);
		driver.switchTo().window(childWarrays[0]);
		System.out.println("base");
		driver.switchTo().window(childWarrays[2]);
		Thread.sleep(1000);
		driver.switchTo().window(childWarrays[0]);
		System.out.println("base");
		driver.switchTo().window(parentWindow);
		return driver;
	}


}


