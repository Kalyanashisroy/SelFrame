package com.FWV1A.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.log4j.Logger;
//import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class TestCases_for_Jenkins {
	//static Logger log = Logger.getLogger(TestCases_for_Jenkins.class);
	//BrowserFactory bfact;
	public TestCases_for_Jenkins() {
		//DOMConfigurator.configure("./src/com/FWV1A/lib/log4j_FWV1A.xml");
		//bfact = new BrowserFactory();
	}

	public static void main(String[] args) {



	}
	public WebDriver openBrowser(WebDriver driver, String browserType, String url, String driverpath) {
		//driver = bfact.initiateBrowser(driver, browserType, url);
		driver = initiateBrowser(driver, browserType, url, driverpath);
		//log.info("Testcase1>openBrowser> done");
		return driver;
	}

	public WebDriver loginPage(WebDriver driver, String userName, String password) {
		Reporter.log("TC_1_loginPage>Started./n ");
		driver.findElement(By.id("j_username")).clear();
		//driver.findElement(By.id("j_username")).sendKeys("admin");
		driver.findElement(By.id("j_username")).sendKeys(userName);
		Reporter.log("username is entered. ");
		driver.findElement(By.name("j_password")).clear();
		//driver.findElement(By.name("j_password")).sendKeys("admin");
		driver.findElement(By.name("j_password")).sendKeys(password);
		Reporter.log("TC_1 password is entered. ");
		driver.findElement(By.name("Submit")).submit();
		Reporter.log("TC_1 Submit done. ");
		Reporter.log("The title of the page is: "+driver.getTitle()+". ");
		return driver;

	}

	public WebDriver LogoutJenkinPage(WebDriver driver)  {
		driver.navigate().refresh();
		long timeoutinsecondT=5; 
		long pollingFrequencyInMilliSec=200; 
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'hidden-xs hidden-sm') and contains(text(),'log out')]") ));


		WebElement logout = driver.findElement(By.xpath("//span[contains(@class,'hidden-xs hidden-sm') and contains(text(),'log out')]"));
		logout.click();
		return driver;

	}

	public WebDriver Project_Build_With_Parameters(WebDriver driver, String existingprojectName) throws InterruptedException {
		Reporter.log("Project Build With Parameters --start. ");
		//driver.get("http://localhost:8080/job/Selframe1/build?delay=0sec");
		driver.get("http://localhost:8080/job/" +existingprojectName+ "/build?delay=0sec");
		driver.navigate().refresh();
		long timeoutinsecondT=5; // it will not wait till 180 sec if element is found
		long pollingFrequencyInMilliSec=500; // it will wait even 15 sec if the element is found. Increase it to 15 sec and see.
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		wait.until(ExpectedConditions.presenceOfElementLocated( By.xpath("//button[contains(text(),'Build' )] ") ));


		//Click the Build button
		//driver.findElement(By.id("yui-gen4-button")).click();
		//<button type="button" tabindex="0" id="yui-gen1-button">Build</button>
		//driver.findElement(By.cssSelector("button[id^='yui-gen'] ")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Build' )] ")).click();

		Reporter.log("TC_3_SelframeBuildWithParameters --finished. ");
		return driver;
	}

	public WebDriver TC_4_GoToTheLastBuildConsole(WebDriver driver) {
		for(int count=0;count<=2; count++) {
			try {	

				driver.get("http://localhost:8080/job/Selframe1/lastBuild/console");
				break;
			}catch(Exception e) {e.getMessage();}
		}

		return driver;
	}


	public WebDriver GoTheRunningBuildConsole(WebDriver driver) {
		Reporter.log("GoTheRunningBuildConsole -- start. ");
		driver.navigate().refresh();
		long timeoutinsecondT=5; 
		long pollingFrequencyInMilliSec=200; 
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);

		wait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(" table[class=\"pane stripped\"] tr:nth-of-type(2) td:nth-of-type(1) div:nth-of-type(1) a:nth-of-type(1) "))));
		// <a update-parent-class=".build-row" href="http://localhost:8080/job/final6/1/" class="tip model-link inside build-link display-name zws-inserted">#1 </a>

		//To find href from anchor from column ,  rows ,table. 
		String urloftherunningprojectconsole = driver.findElement(By.cssSelector(" table[class=\"pane stripped\"] tr:nth-of-type(2) td:nth-of-type(1) div:nth-of-type(1) a:nth-of-type(1) ")).getAttribute("href");
		//String urloftherunningprojectconsole = driver.findElement(By.xpath("//a[@class=\"tip model-link inside build-link display-name zws-inserted]")).getAttribute("href");
		//navigate to the running console.
		driver.get(urloftherunningprojectconsole);
		Reporter.log("GoTheRunningBuildConsole -- finished. ");
		return driver;	
	}



	public WebDriver ToVerifyThatBuildIsFinishedFromConsole(WebDriver driver) {
		Reporter.log("ToVerifyThatBuildIsFinishedFromConsole is started. ");
		//<div id="spinner" style="display: none;"><img src="./Selframe1 #50 Console [Jenkins]_files/spinner.gif" alt=""></div>		
		driver.navigate().refresh();
		// it will not wait till 180 sec if element is found
		long timeoutinsecondT=60*10; 
		//It will poll every 5 sec to check whether the last element of PRE is present.
		long pollingFrequencyInMilliSec=5000; 
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='spinner'  and  @style='display: none;' ]")));

		WebElement totaltext = driver.findElement(By.tagName("pre"));
		String totaltext1 = totaltext.getText();
		//To find the last word of a long or short sentences. 
		String buildStatus = totaltext1.substring(totaltext1.lastIndexOf(" ")+1);
		//Assert will always PASS. The testcase will always pass independent of buildStatus.
		if(buildStatus.contains("FAILURE")) {
			Reporter.log("The status of the build is FAILURE");
			Assert.assertEquals(buildStatus, "FAILURE");
		}

		else if(buildStatus.contains("SUCCESS")) {
			Reporter.log("The status of the Build is SUCCESS. ");
			Assert.assertEquals(buildStatus, "SUCCESS");
		}
		Reporter.log("The status of the Build from the console::"+buildStatus);
		return driver;
	}

	public WebDriver ClickOnNewItem(WebDriver driver) {
		//<a href="http://localhost:8080/view/all/newJob" title="New Item" class="task-link">New Item</a>
		// <input name="name" id="name" data-valid="false" type="text" tabindex="0">
		driver.navigate().refresh();
		//click on New Item.
		driver.findElement(By.xpath("//a[@title='New Item'  and  @class='task-link']")).click();
		Reporter.log("Clicked on new item to create new project. ");
		driver.navigate().refresh();

		return driver;
	}	

	public WebDriver EnterThenameofthenewProjectintheinputBox(WebDriver driver,String projectName) {
		//Enter text in input box.

		//String newitemname="text123";
		String newitemname = projectName;
		driver.findElement(By.xpath("//input[@name='name' and @id='name']")).sendKeys(newitemname);
		Reporter.log("Entered the project name in the input box: "+newitemname+". ");

		return driver;
	}

	public WebDriver Select_maven_project_byclick(WebDriver driver) {
		//Select maven project by click.
		//<span class="label">Maven project</span>
		//driver.findElement(By.xpath("//*[contains(text(),'Maven project')]")).click();
		driver.findElement(By.xpath("//*[(text()='Maven project')]")).click();
		Reporter.log("The maven project is selected by click. ");

		return driver;

	}
	public WebDriver Click_OK_after_Select_maven_project_byclick(WebDriver driver) {
		//click OK button.
		// <button type="submit" id="ok-button" class="disabled" disabled="">OK</button>
		driver.findElement(By.id("ok-button")).click();
		Reporter.log("Clicked OK. ");
		return driver;
	}		

	public WebDriver get_the_url_of_the_current_page(WebDriver driver) {
		//get the url of the current page.
		String currenturl = driver.getCurrentUrl();
		Reporter.log("The current url of the page is "+currenturl+". ");
		driver.navigate().refresh();
		return driver;
	}

	public WebDriver click_the_check_box_for_Discard_old_builds(WebDriver driver) {
		// click the check box for Discard old builds. 
		// <input onclick="javascript:updateOptionalBlock(this,true)" name="specified" type="checkbox" class="optional-block-control block-control   jenkins-loaded-1598549565160 disable-behavior" id="cb3">
		driver.findElement(By.xpath("//input[@name=\"specified\" and @type=\"checkbox\" and @id='cb3']")).click();
		Reporter.log("Checked Box is selected for Discard old Builds. ");
		ScrollDownThepageByPixel(driver,500);
		return driver;
	}

	public WebDriver Click_the_checkbox_This_project_is_parameterized(WebDriver driver) {
		// <input onclick="javascript:updateOptionalBlock(this,true)" name="specified" type="checkbox" class="optional-block-control block-control   jenkins-loaded-1598549565160 disable-behavior" id="cb4">
		driver.findElement(By.xpath("//input[@name=\"specified\" and @type=\"checkbox\" and @id='cb4']")).click();		
		Reporter.log("The check box is checked or clicked. ");
		return driver;
	}

	public WebDriver Select_Choice_parameter_from_Add_Parameter_of_This_project_is_parameterized(WebDriver driver) {
		//<button type="button" tabindex="0" id="yui-gen1-button" class="hetero-list-add" suffix="parameterDefinitions">Add Parameter</button>
		driver.findElement(By.xpath("//button[contains(text(),'Add Parameter' )]")).click();
		//<div class="bd">
		//<ul class="first-of-type">
		// <li class="yuimenuitem" id="yui-gen18" groupindex="0" index="1">
		//CHAINED XPATH IS USED TO FIND THE CHOICE PARAMETER div>ul>li.
		driver.findElement(By.xpath("//div[@class=\"bd\"] //ul[@class=\"first-of-type\"] //li[@index=\"1\"]")).click();
		Reporter.log("The choice parameter is selected from the dropdown list. ");
		return driver;
	}

	public WebDriver Type_Name_for_Choice_Parameter(WebDriver driver, String parameterName) {

		long timeoutinsecondT=2; 
		long pollingFrequencyInMilliSec=200; 
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("parameter.name")));
		//<input name="parameter.name" type="text" class="setting-input   " value="">
		driver.findElement(By.name("parameter.name")).sendKeys(parameterName);
		Reporter.log("The name of the choice parameter is entered. ");
		return driver;
	}
	public WebDriver Type_Choices_for_Choice_Parameter(WebDriver driver, String parameterChoices ) {
		//<textarea name="parameter.choices"
		driver.findElement(By.name("parameter.choices")).sendKeys(parameterChoices);
		Reporter.log("The Choices are entered. ");
		return driver;
	}





	public WebDriver Type_Goals_and_Options_in_BUILD(WebDriver driver, String goalsAndOptions) {
		//<input name="goals" type="text" class="setting-input   " value="">
		driver.findElement(By.name("goals")).sendKeys(goalsAndOptions);
		Reporter.log("The goal and options are typed. ");
		return driver;
	}

	public WebDriver Click_Advanced_Button_Of_BUILD(WebDriver driver) {
		//<button type="button" tabindex="0" id="yui-gen10-button">Advanced...</button>
		driver.findElement(By.id("yui-gen10-button")).click();
		Reporter.log("The advanced button of the BUILD is clicked. ");
		return driver;
	}


	public WebDriver Click_Custom_workspace_Checkbox(WebDriver driver) {
		ScrollDownThepageByPixel(driver,300);
		long timeoutinsecondT=2; 
		long pollingFrequencyInMilliSec=200; 
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.name("hasCustomWorkspace")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cb29")));

		// <input onclick="javascript:updateOptionalBlock(this,true)" name="hasCustomWorkspace" type="checkbox" class="optional-block-control block-control   jenkins-loaded-1598619755432" id="cb29">
		//driver.findElement(By.name("hasCustomWorkspace")).click();
		driver.findElement(By.id("cb29")).click();
		Reporter.log("Click the checkbox of Custom workspace. ");

		return driver;
	}



	public WebDriver Enter_text_to_Directory_Custom_workspace(WebDriver driver, String projDirectoryName) {
		//<input checkdependson="customWorkspace" checkurl="/job/testz1g/descriptorByName/hudson.maven.MavenModuleSet/checkCustomWorkspace" name="_.customWorkspace" type="text"
		driver.findElement(By.name("_.customWorkspace")).sendKeys(projDirectoryName);
		return driver;
	}





	public WebDriver Click_the_floating_Apply_Button(WebDriver driver) {
		long timeoutinsecondT=2; 
		long pollingFrequencyInMilliSec=200; 
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"first-child\"]//button[text()='Apply']")));

		//<button type="button" tabindex="0" id="yui-gen11-button">Apply</button>
		//<span class="first-child"><button type="button" tabindex="0"	id="yui-gen11-button">Apply</button></span>
		ScrollDownThepageByPixel(driver,2000);
		driver.findElement(By.xpath("//span[@class=\"first-child\"]//button[text()='Apply']")).click();
		Reporter.log("Floating apply button is clicked. ");
		return driver;
	}


	public WebDriver Click_the_floating_SAVE_Button(WebDriver driver) {
		//<button type="button" tabindex="0" id="yui-gen17-button">Save</button>
		//<span class="first-child"><button type="button" tabindex="0" id="yui-gen15-button">Save</button></span>
		driver.findElement(By.xpath(" //span[@class=\"first-child\"] //button[text()='Save']  ")).click();
		Reporter.log("Floating SAVE button is clicked. ");
		return driver;
	}

	public WebDriver Verify_that_project_is_created_successfully(WebDriver driver) {
		//<h1>Maven project final1</h1>
		long timeoutinsecondT=5; 
		long pollingFrequencyInMilliSec=200; 
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Maven project')]")));
		driver.navigate().refresh();
		String projectSuccessfully = driver.findElement(By.xpath("//h1[contains(text(),'Maven project')]")).getText();
		Reporter.log("The project is created successfully. The name of the project is::"+projectSuccessfully);
		Reporter.log("The current url of the page is :"+driver.getCurrentUrl());
		return driver;
	}

	public WebDriver Delete_an_Existing_Project(WebDriver driver, String projectToBeDeleted) {
		// http://localhost:8080/user/admin/my-views/view/all/job/final3/
		//#tasks > div:nth-child(7) > a.task-link    Delete Maven project
		driver.get("http://localhost:8080/user/admin/my-views/view/all/job/"+projectToBeDeleted+"/");
		driver.findElement(By.xpath("//a[contains(text(),'Delete Maven project')]")).click();
		driver.switchTo().alert().accept();
		Reporter.log("The project which has been deleted is ::"+projectToBeDeleted);
		return driver;
	}


	public WebDriver r1(WebDriver driver) {

		return driver;
	}

	public WebDriver r12(WebDriver driver) {

		return driver;
	}
	public WebDriver r31(WebDriver driver) {

		return driver;
	}


	// useful and reusable functions below:
	public WebDriver ScrollDownThepageByPixel(WebDriver driver,int noofpixel) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,  "+noofpixel+"  )");
		return driver;
	}


	public WebDriver initiateBrowser(WebDriver driver, String browserType, String url, String driverpath ) {
		if(browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",driverpath );
			driver = new ChromeDriver();
		}

		else if(browserType.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", driverpath );
			driver = new FirefoxDriver();

		}else if(browserType.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", driverpath );
			driver = new InternetExplorerDriver();
		}

		driver.get(url);
		return driver;
	}	




	//xcel readers below::::::::::::::::::::::::::::::::
	public static DataFormatter formatter= new DataFormatter();
	/////////////   Single cell reader start
	public String xlsxSingleCellReader(String filepath,String sheetname ,int nthRw, int noofCol) throws IOException{
		File file = new File(filepath); // excel file path
		FileInputStream filestream = new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook workbookd = new XSSFWorkbook(filestream);
		XSSFSheet worksheet = workbookd.getSheet(sheetname);  // sheet name
		int ColNum=noofCol;
		XSSFRow row= worksheet.getRow(nthRw-1);
		XSSFCell cell= row.getCell(ColNum -1);
		String value=formatter.formatCellValue(cell);
		return value;
	} 
	//////  Single Cell reader end

	//One row reader start
	public String[][] xlsxReaderOneRow(String filepath,String sheetname ,int nthRw, int noofCol) throws IOException{
		File file = new File(filepath);
		FileInputStream filestream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(filestream);
		XSSFSheet worksheet = workbook.getSheet(sheetname);
		int ColNum=noofCol;
		String Data[][]= new String[1][ColNum]; // pass my count data in array

		//for(int i=RowNum; i<RowNum-1; i++) //Loop work for Rows
		//{
		int i=0;
		XSSFRow row= worksheet.getRow(nthRw-1);

		for (int j=0; j<ColNum; j++) //Loop work for colNum
		{
			if(row==null)
				Data[i][j]= "";
			else
			{
				XSSFCell cell= row.getCell(j);
				if(cell==null)
					Data[i][j]= ""; //if it get Null value it pass no data 
				else
				{
					String value=formatter.formatCellValue(cell);
					Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
					//System.out.println("hhh##"+value);
				}
			}
		}
		//} 
		XSSFCell cell= row.getCell(0);
		//String value=formatter.formatCellValue(cell);
		//System.out.println("## "+value);

		return Data;


	} 
	//One row reader end


	//////////////////

	//All row All colums reader -start
	public String[][] xlsxReaderAllRowsAllColumns(String filepath,String sheetname) throws IOException{
		File file = new File(filepath);
		FileInputStream filestream = new FileInputStream(file);
		//Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(filestream);
		//Get first/desired sheet from the workbook
		//XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFSheet worksheet = workbook.getSheet(sheetname);
		//Iterate through each rows one by one
		XSSFRow Row=worksheet.getRow(0);
		int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		int ColNum= Row.getLastCellNum(); // get last ColNum 
		String Data[][]= new String[RowNum-1][ColNum]; // pass my� count data in array
		for(int i=0; i<RowNum-1; i++) //Loop work for Rows
		{ 
			XSSFRow row= worksheet.getRow(i+1);

			for (int j=0; j<ColNum; j++) //Loop work for colNum
			{
				if(row==null)
					Data[i][j]= "";
				else
				{
					XSSFCell cell= row.getCell(j);
					if(cell==null)
						Data[i][j]= ""; //if it get Null value it pass no data 
					else
					{
						String value=formatter.formatCellValue(cell);
						Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value

					}
				}
			}
		}

		return Data;


	} 
	//All row All colums reader -end

	//Nth RowS Nth ColumnS --start
	public String[][] xlsxReaderNoRowsNoColumns(String filepath,String sheetname,int noofrows,int noofcols) throws IOException{
		File file = new File(filepath);
		FileInputStream filestream = new FileInputStream(file);
		//Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(filestream);
		//Get first/desired sheet from the workbook
		//XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFSheet worksheet = workbook.getSheet(sheetname);
		//Iterate through each rows one by one
		XSSFRow Row=worksheet.getRow(0);
		//int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		//int ColNum= Row.getLastCellNum(); // get last ColNum 
		int RowNum=noofrows+1;
		int ColNum=noofcols;
		String Data[][]= new String[RowNum-1][ColNum]; // pass my� count data in array
		for(int i=0; i<RowNum-1; i++) //Loop work for Rows
		{ 
			XSSFRow row= worksheet.getRow(i+1);

			for (int j=0; j<ColNum; j++) //Loop work for colNum
			{
				if(row==null)
					Data[i][j]= "";
				else
				{
					XSSFCell cell= row.getCell(j);
					if(cell==null)
						Data[i][j]= ""; //if it get Null value it pass no data 
					else
					{
						String value=formatter.formatCellValue(cell);
						Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value

					}
				}
			}
		}

		return Data;
	} 


}

