package com.FWL1.runner;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.FWL1.testcases.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.FWL1.lib.*;

public class Runner_One {
	static Logger log = Logger.getLogger(Runner_One.class);
	WebDriver driver;
	TestCases_for_Jenkins tc;
	BrowserAndDataFactory dfact;
	String AllParametersFromFact;
	HashMap<String, String> AllParametersFromFactMP;
	Helper help;
	Object jsonData;

	public Runner_One() {
		// tc = new TestCases_for_Jenkins();
		// help = new Helper();
		// The above code is moved to beforeClass because factory cannot run the default
		// constructor.
	}

	public Runner_One(HashMap<String, String> AllParametersFromFactMP) {
		this.AllParametersFromFactMP = AllParametersFromFactMP;
		// System.out.println("im in constructor::"+this.AllParametersFromFactMP);
	}

	public Runner_One(Object jsondata) {
		this.jsonData = jsondata;
	}

	@AfterMethod(groups = { "Factory_create_new_project", "Factory_DELETE_project", "Add_user_group" })
	public void takeScreen_Shot_On_Failure_or_Success(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			driver = help.captureScreenshot(driver, "fAil" + testResult.getName());
			log.info("Screenshot taken on failure. ");
		} else if (testResult.getStatus() == ITestResult.SUCCESS) {
			driver = help.captureScreenshot(driver, "Success_" + testResult.getName());
			log.info("Screenshot taken on Success. ");
		}
	}

	// @Test(groups= {"Factory_create_new_project"})
	public void testtogetvaluefromHMap() {

		//System.out.println("the value of the browser911 ::" + getValueFromKey("browserType"));
	}

	public String getValueFromKey(String key) {

		HashMap<String, String> mpp = AllParametersFromFactMP;
		return mpp.get(key);
	}

	@BeforeClass(groups = { "Add_user_group", "Group_22_BB","Group_33_CC","Run_the_Build","Create_new_project" ,"DELETE_project"})
	public void To_create_objects_of_the_imported_class() {
		// driver is passed to help to initialize PageFactory.initElements
		tc = new TestCases_for_Jenkins(driver);
		help = new Helper();
		dfact = new BrowserAndDataFactory();
		String projectPath = System.getProperty("user.dir");
		DOMConfigurator.configure(projectPath + help.getValueFrom("log4jxmlpath"));

		// System.out.println("Objects of the imported class created. ");
		Reporter.log("Objects of the imported class created.. ");
		log.info("Objects of the imported class created. ");
	}

	
	@Test(priority = 11,dataProvider="DP_to_Create_New_Project", groups = { "Create_new_project" })
	public void ClickOnNewItem(Object jsonobject) {
		JsonNode addNewProject = (JsonNode)jsonobject;
		//driver = tc.ClickOnNewItem(driver);
		// to check whether project exists or not. If yes ,then it will delete the existing project.
		//And create the new one.
		driver = tc.ClickOnNewItem(driver,addNewProject.path("projectNameToBeCreated").asText());
	}

	@Test(priority = 12,dataProvider="DP_to_Create_New_Project", groups = { "Create_new_project" })
	public void Enter_The_name_of_the_new_Project_in_theinput_Box_from_xlsx(Object jsonobject) {
		JsonNode addNewProject = (JsonNode)jsonobject;
		driver = tc.EnterThenameofthenewProjectintheinputBox(driver, addNewProject.path("projectNameToBeCreated").asText());
	}

	@Test(priority = 13, groups = { "Create_new_project" })
	public void Select_maven_project_byclick() {
		driver = tc.Select_maven_project_byclick(driver);
	}

	@Test(priority = 14, groups = { "Create_new_project" })
	public void Click_OK_after_Select_maven_project_byclick() {
		driver = tc.Click_OK_after_Select_maven_project_byclick(driver);
	}

	@Test(priority = 15, groups = { "Create_new_project" })
	public void get_the_url_of_the_current_page() {
		driver = tc.get_the_url_of_the_current_page(driver);
	}

	@Test(priority = 16, groups = { "Create_new_project" })
	public void Click_the_checkbox_This_project_is_parameterized() {
		driver = tc.Click_the_checkbox_This_project_is_parameterized(driver);
	}

	@Test(priority = 17, groups = { "Create_new_project" })
	public void click_the_check_box_for_Discard_old_builds() {
		driver = tc.click_the_check_box_for_Discard_old_builds(driver);
	}

	@Test(priority = 18, groups = { "Create_new_project" })
	public void Select_Choice_parameter_from_Add_Parameter_of_This_project_is_parameterized() {
		driver = tc.Select_Choice_parameter_from_Add_Parameter_of_This_project_is_parameterized(driver);
	}

	@Test(priority = 19,dataProvider="DP_to_Create_New_Project", groups = { "Create_new_project" })
	public void Type_Name_for_Choice_Parameter(Object jsonobject) {
		JsonNode addNewProject = (JsonNode)jsonobject;
		driver = tc.Type_Name_for_Choice_Parameter(driver, addNewProject.path("parameterName").asText());

	}

	@Test(priority = 20,dataProvider="DP_to_Create_New_Project", groups = { "Create_new_project" })
	public void Type_Choices_for_Choice_Parameter(Object jsonobject) {
		JsonNode addNewProject = (JsonNode)jsonobject;
		driver = tc.Type_Choices_for_Choice_Parameter(driver,addNewProject.path("parameterChoices").asText());

	}

	@Test(priority = 21,dataProvider="DP_to_Create_New_Project", groups = { "Create_new_project" })
	public void Type_Goals_and_Options_in_BUILD(Object jsonobject) {
		JsonNode addNewProject = (JsonNode)jsonobject;
		driver = tc.Type_Goals_and_Options_in_BUILD(driver, addNewProject.path("goalsAndOptions").asText());

	}

	@Test(priority = 22, groups = { "Create_new_project" })
	public void Click_Advanced_Button_Of_BUILD() {
		driver = tc.Click_Advanced_Button_Of_BUILD(driver);
	}

	@Test(priority = 23, groups = { "Create_new_project" })
	public void Click_Custom_workspace_Checkbox() {
		driver = tc.Click_Custom_workspace_Checkbox(driver);
	}

	@Test(priority = 24,dataProvider="DP_to_Create_New_Project", groups = { "Create_new_project" })
	public void Enter_text_to_Directory_Custom_workspace(Object jsonobject) {
		JsonNode addNewProject = (JsonNode)jsonobject;
		driver = tc.Enter_text_to_Directory_Custom_workspace(driver, addNewProject.path("projDirectoryName").asText());

	}

	@Test(priority = 30, groups = { "Create_new_project" })
	public void Click_the_floating_Apply_Button() {
		driver = tc.Click_the_floating_Apply_Button(driver);

	}

	@Test(priority = 32, groups = { "Create_new_project" })
	public void Click_the_floating_SAVE_Button() {
		driver = tc.Click_the_floating_SAVE_Button(driver);
	}

	@Test(priority = 35, groups = { "Create_new_project" })
	public void Verify_that_project_is_created_successfully() {
		driver = tc.Verify_that_project_is_created_successfully(driver);
	}

	
	
	
	//@Test(priority = 100, groups = { "" })
	public void LogoutJenkinPage() {
		driver = tc.LogoutJenkinPage(driver);
		Reporter.log("The logout is done successfully");
	}

	@Test(priority = 91, dataProvider = "DP_for_projects_to_Delete", groups = { "DELETE_project","test" })
	public void Delete_Existing_Projects_with_DP(Object jsonobject) {
		JsonNode deleteProject = (JsonNode)jsonobject;
		//System.out.println("delete******"+deleteProject.path("projectName").asText());
		log.info("delete project started. ");
		driver = tc.Delete_an_Existing_Project(driver, deleteProject.path("projectName").asText().toString());
	}

	@DataProvider(name = "DP_for_projects_to_Delete")
	public Object[] Json_DataProvider_projects_to_Delete() {
		String jsonfilepath = System.getProperty("user.dir") + help.getValueFrom("JsonDataRep");
		String nodeName = "deleteProject";
		// JsonNode allNodes = new
		// BrowserAndDataFactory().jsonNodeArrayReader(jsonfilepath);
		JsonNode allNodes = dfact.jsonNodeArrayReader(jsonfilepath);
		// To read the addUserPage node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodes = new Object[sizeofArray];
		for (int count = 0; count < sizeofArray; count++) {
			objecttoStorenodes[count] = allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodes;
	}

	// - Add_user_group

	public void Add_user_in_Jenkin_by_admin(String userName, String passWord, String passWordCon, String fullName,
			String emailId) {
		driver = tc.Add_user_in_Jenkin_by_admin(driver, userName, passWord, passWordCon, fullName, emailId);
	}

	@DataProvider(name = "DP_for_user_Add")
	public Object[][] DataProvider_for_Add_user() {
		Object[][] dataAll = null;
		try {

			dataAll = dfact.xlsxReaderAllRowsAllColumns(help.getValueFrom("excelfilepath"),
					help.getValueFrom("ShtuserAdd"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataAll;
	}

	@Test(priority = 2, dataProvider = "DP_json_for_user_Add", groups = { "Add_user_group" })
	public void AB_Add_user_in_Jenkin_by_admin_jsondata(Object node) {
		driver = tc.Add_user_in_Jenkin_by_admin(driver, node);
	}

	@DataProvider(name = "DP_json_for_user_Add")
	public Object[] Json_DataProvider_for_Add_user() {
		String jsonfilepath = System.getProperty("user.dir") + help.getValueFrom("JsonDataRep");
		String nodeName = "addUserPage";
		JsonNode allNodes = dfact.jsonNodeArrayReader(jsonfilepath);
		// To read the addUserPage node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodes = new Object[sizeofArray];
		for (int count = 0; count < sizeofArray; count++) {
			objecttoStorenodes[count] = allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodes;
	}

	// ---for "Group_22_BB" and "Group_33_CC"

	@Test(priority = 0, groups = { "Group_22_BB", "Group_33_CC","Run_the_Build","Create_new_project","DELETE_project" })
	public void OpenBrowserForJenkin_jsondata() {
		//System.out.println("hare rame");
		JsonNode loginPage = (JsonNode) jsonData;
		// driver = tc.openBrowser(driver,"chrome","http://localhost:8080/");
		driver = tc.openBrowser(driver, loginPage.path("browserType").asText(), loginPage.path("url").asText());
		driver.manage().window().maximize();
		Reporter.log("The browser is opened successfully - jsondata. ");
	}

	@Test(priority = 1, groups = { "Group_22_BB", "Group_33_CC","Run_the_Build","Create_new_project","DELETE_project" })
	public void LoginPage_jsondata() {
		JsonNode loginPage = (JsonNode) jsonData;
		driver = tc.loginPage(driver, loginPage.path("jenkinuserName").asText(),
				loginPage.path("jenkinpassword").asText());
		Reporter.log("The login is done successfully- jsondata");
	}

	
	@Test(priority=103,dataProvider="DP_project_to_Build", groups= {"Run_the_Build"})
	public void SelframeBuildWith_jsondata(Object jsonData) throws InterruptedException {
		JsonNode buildProject = (JsonNode) jsonData;
		//System.out.println("the llll "+buildProject.path("projectName").asText());
		log.info("The name of the project to be build - "+buildProject.path("projectName").asText());
		//driver = tc.Project_Build_With_Parameters(driver, "test_334");
		driver = tc.Project_Build_With_Parameters(driver, buildProject.path("projectName").asText());
	}
	@Test(priority=104, groups= {"Run_the_Build"})
	public void GoTheRunningBuildConsole() {
		driver = tc.GoTheRunningBuildConsole(driver);
	}

	
	@Test(priority=105,  groups= {"Run_the_Build"})
	public void ToVerifyThatBuildIsFinishedFromConsoleAndFindStatus() {
		driver = tc.ToVerifyThatBuildIsFinishedFromConsole(driver);
	}

	
	@Test(priority = 2, dataProvider = "DP_json_for_user_Add", groups = { "Group_22_BB" })
	public void AB_Add_user_in_Jenkin_by_admin_jsondata2(Object node) {
		driver = tc.Add_user_in_Jenkin_by_admin(driver, node);
	}

	// group "Group_33_CC" is only to delete the user.
	@Test(priority = 2, groups = { "Group_33_CC" }, dataProvider = "DP_json_for_user_Delete")
	public void AA_Delete_existing_Jenkin_user(Object jsonData) {
		JsonNode deleteUser = (JsonNode) jsonData;
		// System.out.println("jjj33:"+deleteUser.path("userName").asText());
		// driver = tc.Delete_existing_Jenkin_user(driver);
		driver = tc.Delete_existing_Jenkin_user(driver, deleteUser.path("userName").asText());
	}

	@DataProvider(name = "DP_json_for_user_Delete")
	public Object[] Json_DataProvider_for_Delete_user() {
		String jsonfilepath = System.getProperty("user.dir") + help.getValueFrom("JsonDataRep");
		String nodeName = "deleteUser";
		JsonNode allNodes = dfact.jsonNodeArrayReader(jsonfilepath);
		// To read the deleteUser node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodes = new Object[sizeofArray];
		for (int count = 0; count < sizeofArray; count++) {
			objecttoStorenodes[count] = allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodes;
	}

	@DataProvider(name = "DP_project_to_Build")
	public Object[] Json_DataProvider_DP_project_to_Build() {
		String jsonfilepath = System.getProperty("user.dir") + help.getValueFrom("JsonDataRep");
		String nodeName = "buildProject";
		JsonNode allNodes = dfact.jsonNodeArrayReader(jsonfilepath);
		// To read the addUserPage node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodes = new Object[sizeofArray];
		for (int count = 0; count < sizeofArray; count++) {
			objecttoStorenodes[count] = allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodes;
	}
	
	
	@Test(dataProvider="DP_to_Create_New_Project",groups= {"test"})
	public void test234(Object jsonobject) {
		//JsonNode addNewProject = (JsonNode)jsonobject;
		/*
		 * System.out.println("eee test:::"+addNewProject.path("projectNameToBeCreated")
		 * .asText());
		 * System.out.println("eee test:::"+addNewProject.path("parameterName").asText()
		 * ); System.out.println("eee test:::"+addNewProject.path("parameterChoices").
		 * asText());
		 * System.out.println("eee test:::"+addNewProject.path("goalsAndOptions").asText
		 * ());
		 * System.out.println("eee test:::"+addNewProject.path("projDirectoryName").
		 * asText());
		 */		
	}
	
	@DataProvider(name = "DP_to_Create_New_Project")
	public Object[] Json_DataProvider_To_Create_New_Project() {
		dfact = new BrowserAndDataFactory();
		help = new Helper();
		String jsonfilepath = System.getProperty("user.dir") + help.getValueFrom("JsonDataRep");
		String nodeName = "addNewProject";
		
		JsonNode allNodes = dfact.jsonNodeArrayReader(jsonfilepath);
		// To read the addNewProject node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodes = new Object[sizeofArray];
		for (int count = 0; count < sizeofArray; count++) {
			objecttoStorenodes[count] = allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodes;
	}
	
	
	
	@AfterMethod(groups = { "Group_22_BB", "Group_33_CC" })
	public void takeScreen_Shot_On_Failure_or_Success2(ITestResult testResult) {
		JsonNode loginPage = (JsonNode) jsonData;
		//String flg = loginPage.path("screenCaptureFlag").asText();


		if(loginPage.path("screenCaptureFlag").asText().equals("0")==true) {
			//System.out.println(" no screen capture on both.");
		}else if(loginPage.path("screenCaptureFlag").asText().equals("1")==true ) {
			if (testResult.getStatus() == ITestResult.FAILURE) {
				driver = help.captureScreenshot(driver, "fail_" + testResult.getName());
				log.info("Screenshot taken on failure. ");
			}
			//System.out.println(" Screencapture on Failure. ");

		}else if(loginPage.path("screenCaptureFlag").asText().equals("2")==true ) {
			if (testResult.getStatus() == ITestResult.SUCCESS) {
				driver = help.captureScreenshot(driver, "Success_" + testResult.getName());
				log.info("Screenshot taken on Success. ");
			}


			//System.out.println(" Screencapture on Success. ");
		}else if(loginPage.path("screenCaptureFlag").asText().equals("3")==true ) {
			if (testResult.getStatus() == ITestResult.FAILURE) {
				driver = help.captureScreenshot(driver, "fail_" + testResult.getName());
				log.info("Screenshot taken on failure. ");
			} else if (testResult.getStatus() == ITestResult.SUCCESS) {
				driver = help.captureScreenshot(driver, "Success_" + testResult.getName());
				log.info("Screenshot taken on Success. ");
			}
		}else {
			log.info("No value present for screeshot key variable: screenCaptureFlag .");
		}	

	}

	//@Test(priority=1,groups= {"Group_22_BB"})
	public void testfail() {
		driver.findElement(By.xpath("//boy[]"));
		//Assert.assertEquals("pass","notpass");
	}

	@AfterTest(groups= {"Group_22_BB", "Group_33_CC","Run_the_Build","Create_new_project","DELETE_project" })
	public void CloseBrowser2() {
		driver.navigate().refresh();
		// driver.close();
		driver.quit();
		Reporter.log("The browser is closed successfully. ");
	}
	@Test(priority=999,groups= {"test"})
	public void TestListen_3c(){
		Assert.assertFalse(true);
		
	}
	@Test(priority=999,groups= {"test"})
	public void TestListen_3c1(){
		throw new SkipException("Skipped this exception");
		
	}
	@Test(priority=999,groups= {"test"})
	public void TestListen_3c2() throws InterruptedException{
		Thread.sleep(600);
		if(3>2) {
			throw new SkipException("Webelement is not present");
		}
		
	}
	
	@Test
	public void test_abc() throws InterruptedException {
		System.out.println();
		Thread.sleep(5000);
	}



}

