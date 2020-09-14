package com.FWL1.runner;

import java.io.IOException;


import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
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
	HashMap<String ,String> AllParametersFromFactMP;
	Helper help;
	Object jsonData;
	String screenCaptureFlag="yes";
	public Runner_One() {
		//tc = new TestCases_for_Jenkins();
		//help = new Helper();
		//The above code is moved to beforeClass because factory cannot run the default constructor.
	}

	public Runner_One(HashMap<String , String> AllParametersFromFactMP) {
		this.AllParametersFromFactMP = AllParametersFromFactMP;
		//System.out.println("im in constructor::"+this.AllParametersFromFactMP);
	}

	public Runner_One(Object jsondata) {
		this.jsonData=jsondata;
		//System.out.println("im in constructor::"+this.AllParametersFromFactMP);
	}

	@AfterMethod(groups= {"Factory_create_new_project","Factory_DELETE_project","Add_user_group","Group_22_BB"})
	public void takeScreen_Shot_On_Failure_or_Success(ITestResult testResult) {
		/*if(testResult.getStatus()==ITestResult.FAILURE) {
			driver = help.captureScreenshot(driver, "fAil"+testResult.getName());
			//System.out.println("lala failed.");
		}
		else if(testResult.getStatus()==ITestResult.SUCCESS) {
			//on success capture no screencapture
			driver = help.captureScreenshot(driver, "Success_"+testResult.getName());
			//System.out.println("lala passed..");
		}*/
		JsonNode loginPage=(JsonNode)jsonData;
		String flag=loginPage.path("screenCaptureFlag").asText();
		if(flag.equals("")) {
			
		}
		
	}
	public static String photo_on_success(String var) {
		if(var.equals("Yes")) {
			Test1.photo_on_failure(var);
		}
		else if(var.equals("No")) {
			Test1.photo_on_failure(var);
		}
		return var;
		
	}
	public static String photo_on_failure(String var) {
		System.out.println("This is photo on failure");
		
		return var;
		
	}
	
	
	//@Test(groups= {"Factory_create_new_project"})
	public void testtogetvaluefromHMap() {

		System.out.println("the value of the browser911 ::"+getValueFromKey("browserType"));
	}

	
	public String getValueFromKey(String key) {
		
		HashMap<String , String> mpp =AllParametersFromFactMP;
		return mpp.get(key);
	}

	
	
	
	@BeforeClass(groups= {"Factory_create_new_project","Factory_DELETE_project","Add_user_group","Group_22_BB","Group_33_CC"})
	public void To_create_objects_of_the_imported_class() {
		// driver is passed to help to initialize PageFactory.initElements 
		tc = new TestCases_for_Jenkins(driver);
		help = new Helper();
		dfact = new BrowserAndDataFactory();
		String projectPath = System.getProperty("user.dir");
		DOMConfigurator.configure(projectPath+help.getValueFrom("log4jxmlpath"));
		
		//System.out.println("Objects of the imported class created. ");
		Reporter.log("Objects of the imported class created.. ");
		log.info("Objects of the imported class created. ");
	}

	@Test(priority=0, groups= {"Factory_create_new_project","Factory_DELETE_project","Add_user_group"})
	public void OpenBrowserForJenkin() {
		driver = tc.openBrowser(driver, getValueFromKey("browserType"),getValueFromKey("url") );
		driver.manage().window().maximize();
		Reporter.log("The browser is opened successfully. ");
	}
	
	
	
	@Test(priority=1, groups= {"Factory_create_new_project","Factory_DELETE_project","Add_user_group"} )
	public void LoginPage() {
		driver = tc.loginPage(driver, getValueFromKey("jenkinuserName"), getValueFromKey("jenkinpassword"));
		Reporter.log("The login is done successfully");
	}

	@Test(priority=11,groups= {"Factory_create_new_project"})
	public void ClickOnNewItem() {
		driver = tc.ClickOnNewItem(driver);
	}

	@Test(priority=12,groups= {"Factory_create_new_project"})
	public void Enter_The_name_of_the_new_Project_in_theinput_Box_from_xlsx() {
		driver = tc.EnterThenameofthenewProjectintheinputBox(driver,getValueFromKey("projectNameToBeCreated"));
	}

	@Test(priority=13, groups= {"Factory_create_new_project"})
	public void Select_maven_project_byclick() {
		driver = tc.Select_maven_project_byclick(driver);
	}

	@Test(priority=14, groups= {"Factory_create_new_project"})
	public void Click_OK_after_Select_maven_project_byclick() {
		driver = tc.Click_OK_after_Select_maven_project_byclick(driver);
	}

	@Test(priority=15, groups= {"Factory_create_new_project"})
	public void get_the_url_of_the_current_page() {
		driver = tc.get_the_url_of_the_current_page(driver);
	}

	@Test(priority=16, groups= {"Factory_create_new_project"})
	public void Click_the_checkbox_This_project_is_parameterized() {
		driver = tc.Click_the_checkbox_This_project_is_parameterized(driver);
	}

	@Test(priority=17, groups= {"Factory_create_new_project"})
	public void click_the_check_box_for_Discard_old_builds() {
		driver = tc.click_the_check_box_for_Discard_old_builds(driver);
	}

	@Test(priority=18, groups= {"Factory_create_new_project"})
	public void Select_Choice_parameter_from_Add_Parameter_of_This_project_is_parameterized() {
		driver = tc.Select_Choice_parameter_from_Add_Parameter_of_This_project_is_parameterized(driver);
	}

	@Test(priority=19,groups= {"Factory_create_new_project"})
	public void Type_Name_for_Choice_Parameter() {
		//driver = tc.Type_Name_for_Choice_Parameter(driver, parameterName);
		driver = tc.Type_Name_for_Choice_Parameter(driver, getValueFromKey("parameterName"));

	}

	@Test(priority=20,groups= {"Factory_create_new_project"})
	public void Type_Choices_for_Choice_Parameter() {
		driver = tc.Type_Choices_for_Choice_Parameter(driver, getValueFromKey("parameterChoices"));

	}

	@Test(priority=21,groups= {"Factory_create_new_project"})
	public void Type_Goals_and_Options_in_BUILD() {
		//driver = tc.Type_Goals_and_Options_in_BUILD(driver, goalsAndOptions);
		driver = tc.Type_Goals_and_Options_in_BUILD(driver, getValueFromKey("goalsAndOptions"));

	}
	@Test(priority=22,groups= {"Factory_create_new_project"})
	public void Click_Advanced_Button_Of_BUILD() {
		driver = tc.Click_Advanced_Button_Of_BUILD(driver);
	}

	@Test(priority=23,groups= {"Factory_create_new_project"})
	public void Click_Custom_workspace_Checkbox() {
		driver = tc.Click_Custom_workspace_Checkbox(driver);
	}

	@Test(priority=24, groups= {"Factory_create_new_project"})
	public void Enter_text_to_Directory_Custom_workspace() {
		//driver = tc.Enter_text_to_Directory_Custom_workspace(driver, projDirectoryName);
		driver = tc.Enter_text_to_Directory_Custom_workspace(driver, getValueFromKey("projDirectoryName"));
		
	}

	@Test(priority=30, groups= {"Factory_create_new_project"})
	public void Click_the_floating_Apply_Button() {
		driver = tc.Click_the_floating_Apply_Button(driver);

	}

	@Test(priority=32, groups= {"Factory_create_new_project"})
	public void Click_the_floating_SAVE_Button() {
		driver = tc.Click_the_floating_SAVE_Button(driver);
	}
	
	@Test(priority=35, groups= {"Factory_create_new_project"})
	public void Verify_that_project_is_created_successfully() {
		driver = tc.Verify_that_project_is_created_successfully(driver);
	}

		
	
	@Test(priority=100, groups= {"Factory_create_new_project","Factory_DELETE_project","Add_user_group"})
	public void LogoutJenkinPage(){
		driver = tc.LogoutJenkinPage(driver);
		Reporter.log("The logout is done successfully");
	}

	
	
	@Test(priority=91, dataProvider="DP_for_projects_Name",groups= {"Factory_DELETE_project"})
	public void Delete_Existing_Projects_with_DP(String projectToBeDeleted) {
		//System.out.println("the ane of ::"+projectToBeDeleted);
		driver = tc.Delete_an_Existing_Project(driver, projectToBeDeleted);
	}


	//String filepath = "C:/workspace/SelFrame/src/com/FWV1A/runner/Jenkin_DP.xlsx";
	//String sheetname= "ShtProjectToBeDeleted";
	@DataProvider(name="DP_for_projects_Name")
	public Object[][] DataProvider_for_projects_Name() {
		Object[][] dataAll=null;
		try {

			//dataAll = tc.xlsxReaderAllRowsAllColumns("C:/workspace/SelFrame/src/com/FWV1A/runner/Jenkin_DP.xlsx", "ProjectToBeDeleted");
			dataAll = dfact.xlsxReaderAllRowsAllColumns(help.getValueFrom("excelfilepath"),help.getValueFrom("ShtProjectToDelete"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataAll;
	}


	
	//- Add_user_group
	
	//@Test(enabled=false,priority=2,dataProvider="DP_for_user_Add",groups= {"Add_user_group"})
	public void Add_user_in_Jenkin_by_admin(String userName, String passWord, String passWordCon, String fullName, String emailId) {
		driver = tc.Add_user_in_Jenkin_by_admin(driver,userName,passWord,passWordCon,fullName,emailId );
	}

	@DataProvider(name="DP_for_user_Add")
	public Object[][] DataProvider_for_Add_user() {
		Object[][] dataAll=null;
		try {

			dataAll = dfact.xlsxReaderAllRowsAllColumns(help.getValueFrom("excelfilepath"),help.getValueFrom("ShtuserAdd"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataAll;
	}

	@Test(priority=2,dataProvider="DP_json_for_user_Add",groups= {"Add_user_group"})
	public void AB_Add_user_in_Jenkin_by_admin_jsondata(Object node) {
		driver = tc.Add_user_in_Jenkin_by_admin(driver, node );
	}

	
	@DataProvider(name="DP_json_for_user_Add")
	public Object[] Json_DataProvider_for_Add_user() {
		String jsonfilepath = System.getProperty("user.dir")+help.getValueFrom("JsonDataRep");
		String nodeName="addUserPage";
		//JsonNode allNodes = new BrowserAndDataFactory().jsonNodeArrayReader(jsonfilepath);
		JsonNode allNodes = new BrowserAndDataFactory().jsonNodeArrayReader(jsonfilepath);
		//To read the addUserPage node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodes = new Object[sizeofArray];
		for(int count=0;count<sizeofArray;count++) {
			objecttoStorenodes[count]=allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodes;
	}


	
	
	//---for "Group_22_BB" and "Group_33_CC"

	@Test(priority=0, groups= {"Group_22_BB","Group_33_CC"})
	public void OpenBrowserForJenkin_jsondata() {
		System.out.println("hare rame");
		JsonNode loginPage=(JsonNode)jsonData;
		//driver = tc.openBrowser(driver,"chrome","http://localhost:8080/");
		driver = tc.openBrowser(driver, loginPage.path("browserType").asText(), loginPage.path("url").asText() );
		driver.manage().window().maximize();
		Reporter.log("The browser is opened successfully - jsondata. ");
	}


	@Test(priority=1, groups= {"Group_22_BB","Group_33_CC"} )
	public void LoginPage_jsondata() {
		JsonNode loginPage=(JsonNode)jsonData;
		driver = tc.loginPage(driver, loginPage.path("jenkinuserName").asText(),loginPage.path("jenkinpassword").asText());
		Reporter.log("The login is done successfully- jsondata");
	}

	
	@Test(priority=2,dataProvider="DP_json_for_user_Add",groups= {"Group_22_BB"})
	public void AB_Add_user_in_Jenkin_by_admin_jsondata2(Object node) {
		driver = tc.Add_user_in_Jenkin_by_admin(driver, node );
	}

	
	// group "Group_33_CC" is only to delete the user.
	@Test(priority=2, groups= {"Group_33_CC"},dataProvider="DP_json_for_user_Delete")
	public void AA_Delete_existing_Jenkin_user(Object jsonData) {
		JsonNode deleteUser=(JsonNode)jsonData;
		//System.out.println("jjj33:"+deleteUser.path("userName").asText());
		//driver = tc.Delete_existing_Jenkin_user(driver);
		driver = tc.Delete_existing_Jenkin_user(driver,deleteUser.path("userName").asText());
	}
	
	
	@DataProvider(name="DP_json_for_user_Delete")
	public Object[] Json_DataProvider_for_Delete_user() {
		String jsonfilepath = System.getProperty("user.dir")+help.getValueFrom("JsonDataRep");
		String nodeName="deleteUser";
		//JsonNode allNodes = new BrowserAndDataFactory().jsonNodeArrayReader(jsonfilepath);
		JsonNode allNodes = new BrowserAndDataFactory().jsonNodeArrayReader(jsonfilepath);
		//To read the addUserPage node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodes = new Object[sizeofArray];
		for(int count=0;count<sizeofArray;count++) {
			objecttoStorenodes[count]=allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodes;
	}

	
	
	@AfterTest(groups= {"Factory_create_new_project","Factory_DELETE_project","Add_user_group"})
	public void CloseBrowser() {
		driver.navigate().refresh();
		//driver.close();
		driver.quit();
		Reporter.log("The browser is closed successfully. ");
	}

}


////----------------------------------------------------------------------
