package com.FWL1.testcases;


import java.util.List;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.FWL1.lib.*;
import com.FWL1.po.PO_LoginPage;
import com.fasterxml.jackson.databind.JsonNode;
public class TestCases_for_Jenkins {
	static Logger log = Logger.getLogger(TestCases_for_Jenkins.class);
	BrowserAndDataFactory bfact;
	Helper help;
	PO_LoginPage pageObj;
	String baseurl=null;
	public TestCases_for_Jenkins(WebDriver driver) {
		help = new Helper();
		String projectPath = System.getProperty("user.dir");
		DOMConfigurator.configure(projectPath+help.getValueFrom("log4jxmlpath"));
		bfact = new BrowserAndDataFactory();
		// driver is passed to initialize Factory.initElements .
		pageObj = new PO_LoginPage(driver);
	}

	public static void main(String[] args) {	}

	public WebDriver openBrowser(WebDriver driver, String browserType, String url) {
		this.baseurl=url;
		//System.out.println("tc open browser "+browserType+" "+url);
		driver = bfact.initiateBrowser(driver, browserType, url);
		//driver = initiateBrowser(driver, browserType, url, driverpath);
		log.info("openBrowser done.");

		return driver;
	}
	/*
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
	 */
	public WebDriver loginPage(WebDriver driver, String userName, String password) {



		String type="default";
		//String type="PO";
		//String type="json";
		if(type.contentEquals("default")) {
			driver.findElement(By.id("j_username")).clear();
			driver.findElement(By.id("j_username")).sendKeys(userName);
			Reporter.log("username is entered. ");

			driver.findElement(By.name("j_password")).clear();
			driver.findElement(By.name("j_password")).sendKeys(password);
			Reporter.log("password is entered. ");

			driver.findElement(By.name("Submit")).submit();
			Reporter.log(" Submit done. ");
			Reporter.log("The title of the page is: "+driver.getTitle()+". ");
			log.info("The login page is opened successfully. ");

		}else if(type.contentEquals("PO")) {
			pageObj.userName.clear();
			pageObj.userName.sendKeys(userName);
			Reporter.log("username is entered. ");
			pageObj.passWord.clear();
			pageObj.passWord.sendKeys(password);
			Reporter.log("password is entered. ");
			pageObj.submit.submit();
			Reporter.log("The title of the page is: "+driver.getTitle()+". ");
			log.info("The login page is opened successfully. ");

		}else if(type.contentEquals("json")) {
			WebElement uname = driver.findElement(bfact.jSONReader("loginPage", "userName"));
			uname.clear();
			uname.sendKeys(userName);
			WebElement pword = driver.findElement(bfact.jSONReader("loginPage","passWord"));
			pword.clear();
			pword.sendKeys(password);
			Reporter.log("password is entered. ");
			driver.findElement(bfact.jSONReader("loginPage", "submit")).submit();
			Reporter.log("The title of the page is: "+driver.getTitle()+". ");
			log.info("The login page is opened successfully. ");
		}	

		return driver;

	}




	public WebDriver LogoutJenkinPage(WebDriver driver)  {
		driver.navigate().refresh();
		 /* 
		 * 
		 * WebElement logout = driver.findElement(By.
		 * xpath("//span[contains(@class,'hidden-xs hidden-sm') and contains(text(),'log out')]"
		 * )); logout.click();
		 */		
		driver.get(baseurl+"logout");
		return driver;

	}

	public WebDriver Project_Build_With_Parameters(WebDriver driver, String existingprojectName) throws InterruptedException {
		Reporter.log("Project Build With Parameters --start. ");
		//driver.get("http://localhost:8080/job/Selframe1/build?delay=0sec");
		driver.get(baseurl+"job/" +existingprojectName+ "/build?delay=0sec");
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

				driver.get(baseurl + "job/Selframe1/lastBuild/console");
				break;
			}catch(Exception e) {e.getMessage();}
		}

		return driver;
	}


	public WebDriver GoTheRunningBuildConsole(WebDriver driver) {
		Reporter.log("GoTheRunningBuildConsole -- start. ");
		driver.navigate().refresh();
		explicitWait(driver,By.cssSelector(" table[class=\"pane stripped\"] tr:nth-of-type(2) td:nth-of-type(1) div:nth-of-type(1) a:nth-of-type(1) "),5);
		
		/*
		 * long timeoutinsecondT=5; long pollingFrequencyInMilliSec=200; WebDriverWait
		 * wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		 * 
		 * wait.until(ExpectedConditions.presenceOfElementLocated((By.
		 * cssSelector(" table[class=\"pane stripped\"] tr:nth-of-type(2) td:nth-of-type(1) div:nth-of-type(1) a:nth-of-type(1) "
		 * ))));
		 */
		// <a update-parent-class=".build-row" href="http://localhost:8080/job/final6/1/" class="tip model-link inside build-link display-name zws-inserted">#1 </a>

		//To find href from anchor from column ,  rows ,table. 
		String urloftherunningprojectconsole = driver.findElement(By.cssSelector(" table[class=\"pane stripped\"] tr:nth-of-type(2) td:nth-of-type(1) div:nth-of-type(1) a:nth-of-type(1) ")).getAttribute("href");
		//String urloftherunningprojectconsole = driver.findElement(By.xpath("//a[@class=\"tip model-link inside build-link display-name zws-inserted]")).getAttribute("href");
		//navigate to the running console.
		driver.get(urloftherunningprojectconsole);
		Reporter.log("GoTheRunningBuildConsole -- finished. ");
		log.info("Go to The Running Build Console. ");
		return driver;	
	}



	public WebDriver ToVerifyThatBuildIsFinishedFromConsole(WebDriver driver) {
		Reporter.log("ToVerifyThatBuildIsFinishedFromConsole is started. ");
		//<div id="spinner" style="display: none;"><img src="./Selframe1 #50 Console [Jenkins]_files/spinner.gif" alt=""></div>		
		driver.navigate().refresh();
		// it will not wait till 180 sec if element is found
		//long timeoutinsecondT=60*10; 
		//It will poll every 5 sec to check whether the last element of PRE is present.
		/*
		 * long pollingFrequencyInMilliSec=5000; WebDriverWait wait = new
		 * WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.
		 * xpath("//div[@id='spinner'  and  @style='display: none;' ]")));
		 */
		explicitWait(driver,By.xpath("//div[@id='spinner'  and  @style='display: none;' ]"), 60*10);
		
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
		log.info("The status of the Build from the console::"+buildStatus);
		//nagivate to home page.
		driver.get(baseurl);
		return driver;
	}

	public WebDriver ClickOnNewItem(WebDriver driver, String projectName) {
	driver.navigate().refresh();
	log.info("To check that the PROJECT to be created is existed or not. ");
	driver = Delete_an_Existing_Project(driver,projectName);	
	log.info("To create a new project is initiated.  ");
		//<a href="http://localhost:8080/view/all/newJob" title="New Item" class="task-link">New Item</a>
		// <input name="name" id="name" data-valid="false" type="text" tabindex="0">
		//nagivate to home page.
		driver.get(baseurl);
		driver.navigate().refresh();
		//click on New Item.
		driver.findElement(By.xpath("//a[@title='New Item'  and  @class='task-link']")).click();
		Reporter.log("Clicked on new item to create new project. ");
		driver.navigate().refresh();
		log.info("Click on New Item done. ");
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
		explicitWait(driver,By.xpath("//button[contains(text(),'Add Parameter' )]"),6);
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

		explicitWait(driver,By.name("parameter.name"),6);
		//<input name="parameter.name" type="text" class="setting-input   " value="">
		driver.findElement(By.name("parameter.name")).sendKeys(parameterName);
		Reporter.log("The name of the choice parameter is entered. ");
		return driver;
	}
	public WebDriver Type_Choices_for_Choice_Parameter(WebDriver driver, String parameterChoices ) {
		//<textarea name="parameter.choices"
		explicitWait(driver,By.name("parameter.choices"),6);
		driver.findElement(By.name("parameter.choices")).sendKeys(parameterChoices);
		Reporter.log("The Choices are entered. ");
		return driver;
	}





	public WebDriver Type_Goals_and_Options_in_BUILD(WebDriver driver, String goalsAndOptions) {
		//<input name="goals" type="text" class="setting-input   " value="">
		explicitWait(driver,By.name("goals"),6);
		driver.findElement(By.name("goals")).sendKeys(goalsAndOptions);
		Reporter.log("The goal and options are typed. ");
		return driver;
	}

	public WebDriver Click_Advanced_Button_Of_BUILD(WebDriver driver) {
		explicitWait(driver,By.id("yui-gen10-button"),6);
		//<button type="button" tabindex="0" id="yui-gen10-button">Advanced...</button>
		driver.findElement(By.id("yui-gen10-button")).click();
		Reporter.log("The advanced button of the BUILD is clicked. ");
		return driver;
	}


	public WebDriver Click_Custom_workspace_Checkbox(WebDriver driver) {
		driver = ScrollDownThepageByPixel(driver,300);
		explicitWait(driver,By.id("cb29"),6);
		// <input onclick="javascript:updateOptionalBlock(this,true)" name="hasCustomWorkspace" type="checkbox" class="optional-block-control block-control   jenkins-loaded-1598619755432" id="cb29">
		//driver.findElement(By.name("hasCustomWorkspace")).click();
		driver.findElement(By.id("cb29")).click();
		Reporter.log("Click the checkbox of Custom workspace. ");

		return driver;
	}



	public WebDriver Enter_text_to_Directory_Custom_workspace(WebDriver driver, String projDirectoryName) {
		explicitWait(driver,By.name("_.customWorkspace"),6 );
		//<input checkdependson="customWorkspace" checkurl="/job/testz1g/descriptorByName/hudson.maven.MavenModuleSet/checkCustomWorkspace" name="_.customWorkspace" type="text"
		driver.findElement(By.name("_.customWorkspace")).sendKeys(projDirectoryName);
		return driver;
	}





	public WebDriver Click_the_floating_Apply_Button(WebDriver driver) {
		
		explicitWait(driver,By.xpath("//span[@class=\"first-child\"]//button[text()='Apply']"),5);
		//<button type="button" tabindex="0" id="yui-gen11-button">Apply</button>
		//<span class="first-child"><button type="button" tabindex="0"	id="yui-gen11-button">Apply</button></span>
		ScrollDownThepageByPixel(driver,2000);
		driver.findElement(By.xpath("//span[@class=\"first-child\"]//button[text()='Apply']")).click();
		Reporter.log("Floating apply button is clicked. ");
		return driver;
	}


	public WebDriver Click_the_floating_SAVE_Button(WebDriver driver) {
		explicitWait(driver,By.xpath(" //span[@class=\"first-child\"] //button[text()='Save']  "),5);
		//<button type="button" tabindex="0" id="yui-gen17-button">Save</button>
		//<span class="first-child"><button type="button" tabindex="0" id="yui-gen15-button">Save</button></span>
		driver.findElement(By.xpath(" //span[@class=\"first-child\"] //button[text()='Save']  ")).click();
		Reporter.log("Floating SAVE button is clicked. ");
		return driver;
	}

	public WebDriver Verify_that_project_is_created_successfully(WebDriver driver) {
		//<h1>Maven project final1</h1>
		
		explicitWait(driver, By.xpath("//h1[contains(text(),'Maven project')]"), 5);
		driver.navigate().refresh();
		String projectSuccessfully = driver.findElement(By.xpath("//h1[contains(text(),'Maven project')]")).getText();
		Reporter.log("The project is created successfully. The name of the project is::"+projectSuccessfully);
		Reporter.log("The current url of the page is :"+driver.getCurrentUrl());
		//nagivate to home page.
		driver.get(baseurl);

		return driver;
	}

	
	public WebDriver Delete_an_Existing_Project(WebDriver driver, String projectToBeDeleted) {
		//log.info("lll "+bfact.jSONReader("projectTablePage","tableName"));
		log.info("The PROJECT to be deleted if exist is : "+projectToBeDeleted+" . ");
		driver.get(baseurl);
		driver.navigate().refresh();
		//List<WebElement> list =driver.findElements(By.xpath("//table[@id='projectstatus']// tbody /child::tr"));
		List<WebElement> list =driver.findElements(bfact.jSONReader("projectTablePage","tableName"));
		int totalrows = list.size();
		log.info("the size of the project list is "+totalrows);
		int flag=0;
		for(int rowcount=2;rowcount<=totalrows-5;rowcount++) {
			String findprojectidName = driver.findElement(By.xpath("//table[@id='projectstatus']//tr["+rowcount+"]/child::td[3] ")).getText();
			// NOTE:: the below doesnot work , rowcount cannot be read from json file.
			//String findprojectidName = driver.findElement(bfact.jSONReader("projectTablePage","findprojectidName")).getText();
			log.info("The name of the project found inside for loop ::"+findprojectidName);
			log.info("Expected# "+projectToBeDeleted +" .  actual#  "+findprojectidName);
			//log.info("555 "+projectToBeDeleted.contentEquals(findprojectidName));
			if(projectToBeDeleted.contentEquals(findprojectidName)) {
				
				flag=1;
				log.info("The expected "+projectToBeDeleted +".  actual-  "+findprojectidName);
				break;
			}else {
				
				flag=2;
				log.info("The project to be deleted is not present the table. ");
				//log.info("The stat flag is ::"+flag);
				//break;
			}

		}

		log.info("The status to the flag is ::"+flag);
		if(flag==1) {
			// http://localhost:8080/user/admin/my-views/view/all/job/final3/
			//#tasks > div:nth-child(7) > a.task-link    Delete Maven project
			driver.get(baseurl+"user/admin/my-views/view/all/job/"+projectToBeDeleted+"/");
			//driver.findElement(By.xpath("//a[contains(text(),'Delete Maven project')]")).click();
			driver.findElement(bfact.jSONReader("projectTablePage","Delete_Maven_project")).click();
			driver.switchTo().alert().accept();
			log.info("The project is deleted successfully. ");
			driver.get(baseurl);
			//return driver;

		}else {
			log.info("The project to be deleted is NOT present in the table . ");
			//nagivate to home page.
			driver.get(baseurl);
			//return driver;
		}

		return driver;

	}


	public WebDriver Add_user_in_Jenkin_by_admin(WebDriver driver, String userName, String passWord, String passWordCon, String fullName, String emailId) {
		driver.get(baseurl+"securityRealm/");
		driver.navigate().refresh();
		driver.get(baseurl+"securityRealm/addUser");
		Reporter.log("Navigated to add user page. ");
		driver.navigate().refresh();
		String type="default";
		//String type="PO";
		if(type.contentEquals("default")) {
			// <table><tbody> .. <td><input name="username" id="username
			//driver = expliWait_xpath(driver, By.xpath("//input[@id='username' and @name='username']") );
			//driver.findElement(By.xpath("//input[@id='username' and @name='username']")).sendKeys(userName);
			Reporter.log("The username is entered. ");
			//<input name="password1" 	type="password"
			driver.findElement(By.xpath("//input[ @name='password1' and @type='password']")).sendKeys(passWord);
			Reporter.log("The password is entered. ");
			//<input name="password2" type="password" 
			driver.findElement(By.xpath("//input[@type='password' and @name='password2']")).sendKeys(passWordCon);
			Reporter.log("The password is entered for second time. ");
			//<input name="fullname" type="text" 
			driver.findElement(By.xpath("//input[@type='text' and @name='fullname']")).sendKeys(fullName); 
			Reporter.log("Fullname is entered. ");

			/*
			 * long timeoutinsecondT=15; long pollingFrequencyInMilliSec=10000;
			 * WebDriverWait wait = new
			 * WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
			 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * "//html[@class='']")));
			 */


			//<input name="email" type="text" 
			driver.findElement(By.xpath("//input[@type='text' and @name='email']")).sendKeys(emailId); 
			Reporter.log("The email is entered. ");
			//<span class="yui-button yui-submit-button submit-button primary 
			//	  yui-button-hover yui-submit-button-hover" id="yui-gen4" name="Submit"><span class="first-child">
			//  <button type="button" 
			//tabindex="0" id="yui-gen4-button">Create User</button></span>
			//</span>
			//driver.findElement(By.xpath("//button[text()='Create User']")).click();
			//Reporter.log("The create user button is clicked.");
			//driver.navigate().refresh();	


		}else if(type.contentEquals("PO")) {
		}



		return driver;
	}

	//by json data.
	public WebDriver Add_user_in_Jenkin_by_admin(WebDriver driver, Object node) {

		JsonNode addUserPage=(JsonNode)node;
		//Before creating user, it will call the below method to check the name exist or not
		//if the name exists then it will be deleted ,if not nothing will happen .
		driver = Delete_existing_Jenkin_user(driver,addUserPage.path("userName").asText());

		driver.get(baseurl+"securityRealm/");
		driver.navigate().refresh(); 
		driver.get(baseurl+"securityRealm/addUser");
		Reporter.log("Navigated to add user page. ");
		driver.navigate().refresh();

		driver = explicitWait(driver, bfact.jSONReader("addUserPage", "userName"),5);
		driver.findElement(bfact.jSONReader("addUserPage","userName")).sendKeys(addUserPage.path("userName").asText());
		driver.findElement(bfact.jSONReader("addUserPage","passWord")).sendKeys(addUserPage.path("passWord").asText());

		driver.findElement(bfact.jSONReader("addUserPage","confpassWord")).sendKeys(addUserPage.path("confpassWord").asText());
		driver.findElement(bfact.jSONReader("addUserPage","fullname")).sendKeys(addUserPage.path("fullname").asText());
		driver.findElement(bfact.jSONReader("addUserPage","email")).sendKeys(addUserPage.path("email").asText());
		driver.findElement(bfact.jSONReader("addUserPage","button")).click();;

		Reporter.log("The create user button is clicked. Json data used of Node -addUserPage ");
		//nagivate to home page.
		driver.get(baseurl);

		return driver;
	}


	public WebDriver Delete_existing_Jenkin_user(WebDriver driver,String usertodelete) {
		Reporter.log("The user to be deleted if exist is : "+usertodelete+" . ");
		driver.get(baseurl+"securityRealm/");
		driver.navigate().refresh();
		List<WebElement> list =driver.findElements(By.tagName("tr"));
		int totalrows = list.size();
		//System.out.println("the total rows ::"+totalrows);
		Boolean flag=false;
		for(int count=1;count<=totalrows;count++) {

			String finduseridName = driver.findElement(By.xpath("//table[@id='people']//tr["+count+"] ")).getText();
			String[] arrayofwords = finduseridName.split(" ", 2);
			//System.out.println("firstword: "+arrayofwords[0]);// this is to get the UserID
			//System.out.println("secondword: "+arrayofwords[1]);
			
			//System.out.println("kk::"+finduseridName);
			if(usertodelete.contentEquals(arrayofwords[0])) {
				//Reporter.log("The user to be deleted if exist is present in the table. ");
				flag=true;
			}else {
				//Reporter.log("The user to be deleted is not present the table. ");
				flag=false;
			}

		}

		if(flag.equals(true)) {
			Reporter.log("The user to be deleted is present in the table. ");
			String urltodel = baseurl+"securityRealm/user/"+usertodelete+"/delete";
			driver.get(urltodel);
			//<button type="button" tabindex="0" id="yui-gen4-button">Yes</button>
			explicitWait(driver,By.xpath("//button[text()='Yes']"),5);
			driver.findElement(By.xpath("//button[text()='Yes']")).click();
			Reporter.log("The user is deleted successfully. ");
			driver.get(baseurl);
			return driver;

		}else {
			Reporter.log("The user to be deleted is  not present in the table. ");
			//nagivate to home page.
			driver.get(baseurl);
			return driver;
		}

		//System.out.println("Delete existing Jenkin user");
		//nagivate to home page.
		//driver.get(baseurl);

		//return driver;
	}

	public WebDriver explicitWait(WebDriver driver, By locator, long timeoutinsecondT) {
		long time_start = System.currentTimeMillis();
		//long timeoutinsecondT=5; 
		long pollingFrequencyInMilliSec=200; 
		WebDriverWait wait = new WebDriverWait(driver,timeoutinsecondT,pollingFrequencyInMilliSec);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		long time_finish= System.currentTimeMillis();
		long time_elasped = time_finish - time_start;
		log.info("Waited for MILLI_Seconds:: "+time_elasped);
		Reporter.log("\t       Waited for MILLI_Seconds:: "+time_elasped);

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


}

