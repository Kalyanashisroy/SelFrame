package com.FWV1A.runner;

import java.io.IOException;
//import org.apache.log4j.Logger;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.FWV1A.testcases.*;
import com.FWV1A.lib.*;

public class Runner_for_Jenkin_project {
	//static Logger log = Logger.getLogger(Runner_for_Jenkin_project.class);
	WebDriver driver;
	TestCases_for_Jenkins tc;
	String filepath;
	String sheetname;
	String projectName;
	String ShtProjectToBeDeleted;
	
	String browserType; 
	String url;
	String driverpath;
	String jenkinuserName;
	String jenkinpassword;
	String existingprojectName;
	String projectNameToBeCreated;
	
	String parameterName;
	String parameterChoices;
	String goalsAndOptions;
	String projDirectoryName;
	HelperOne helpone;
	
	public Runner_for_Jenkin_project() {
		//DOMConfigurator.configure("./src/com/FWV1A/lib/log4j_FWV1A.xml");
		//tc = new TestCases_for_Jenkins();
		//helpone = new HelperOne();
		//The above code is moved to beforeClass because factory cannot run the default constructor.
	}

	public Runner_for_Jenkin_project(String projectName) {
		this.projectName = projectName;

	}

	
@BeforeClass(groups= {"Create_New_Project_group"})
public void To_create_objects_of_the_imported_class() {
	tc = new TestCases_for_Jenkins();
	helpone = new HelperOne();
	System.out.println("Objects of the imported class created. ");
	Reporter.log("Objects of the imported class created.. ");
}
	
	
	
	//to read the parameters from the xml file.
	@Test(priority = -50, groups= {"Create_New_Project_group"})
	@Parameters({"browserType","url","driverpath","jenkinuserName","jenkinpassword","existingprojectName","ShtProjectToBeDeleted","projectNameToBeCreated","parameterName","parameterChoices","goalsAndOptions","projDirectoryName","filepath","sheetname" })
	public void To_read_All_parameters_from_the_xml_file(@Optional() String browserType,@Optional() String url,@Optional() String driverpath,@Optional() String jenkinuserName,@Optional() String jenkinpassword,@Optional("Selframe1") String existingprojectName,@Optional("") String ShtProjectToBeDeleted, @Optional("demo_one") String projectNameToBeCreated,@Optional() String parameterName,@Optional() String parameterChoices,@Optional() String goalsAndOptions,@Optional() String projDirectoryName,@Optional() String filepath,@Optional() String sheetname ) {
		this.browserType = browserType;
		//this.browserType = helpone.getValueFrom("browserType");
		this.url = url;
		this.driverpath = driverpath;
		this.jenkinuserName = jenkinuserName;
		this.jenkinpassword  = jenkinpassword;
		this.existingprojectName = existingprojectName;
		this.projectNameToBeCreated = projectNameToBeCreated;
		
		this.ShtProjectToBeDeleted = ShtProjectToBeDeleted;
		this.parameterName=parameterName;
		this.parameterChoices=parameterChoices;
		this.goalsAndOptions=goalsAndOptions;
		this.projDirectoryName=projDirectoryName;

		this.filepath = filepath;
		this.sheetname = sheetname;
		Reporter.log("Reading parameters from xml file done. ");
		System.out.println("the variable are loaded from xml. ");
	}

	//to read the parameters from the property file.
	@Test(priority = -50)
	public void To_read_All_parameters_from_the_config_file() {	
		this.browserType = helpone.getValueFrom("browserType");
		this.url = helpone.getValueFrom("url");
		this.driverpath = helpone.getValueFrom("driverpath");
		this.jenkinuserName = helpone.getValueFrom("jenkinuserName");
		this.jenkinpassword  = helpone.getValueFrom("jenkinpassword");
		this.existingprojectName = helpone.getValueFrom("existingprojectName");
		this.projectName = helpone.getValueFrom("projectName");
		Reporter.log("Reading parameters from config file done. ");
	}

	public static void main(String[] args) {
		System.out.println("hi");
		new Runner_for_Jenkin_project().testtt();
	}

	public void testtt() {
		//this.browserType = help.getValueFrom("browserType");
		//To_read_All_parameters_from_the_xml_file("s","d","f","v","f","p","p");
		System.out.println(":::"+this.browserType);
		System.out.println(projectName);
	}

	


	@Test(priority=0, groups= {"Create_New_Project_group"})
	public void OpenBrowserForJenkin() {
		System.out.println("oop: "+browserType+url+driverpath);
		driver = tc.openBrowser(driver, browserType, url, driverpath );
		driver.manage().window().maximize();
		Reporter.log("The browser is opened successfully. ");
	}



	@Test(priority=1, groups= {"Create_New_Project_group"} )
	public void LoginPage() {
		//System.out.println("IIII::"+jenkinuserName+jenkinpassword);
		driver = tc.loginPage(driver, jenkinuserName, jenkinpassword);
		Reporter.log("The login is done successfully");
	}

	@Test(priority=100, groups= {"Create_New_Project_group"})
	public void LogoutJenkinPage(){
		driver = tc.LogoutJenkinPage(driver);
		Reporter.log("The logout is done successfully");
	}

	@Test(priority=3)
	public void SelframeBuildWithParameters() throws InterruptedException {
		driver = tc.Project_Build_With_Parameters(driver, existingprojectName);
	}

	@Test()
	public void TC_4_GoToTheLastBuildConsole() {
		driver = tc.TC_4_GoToTheLastBuildConsole(driver);
	}

	@Test(priority=4)
	public void GoTheRunningBuildConsole() {
		driver = tc.GoTheRunningBuildConsole(driver);
	}

	@Test(priority=5)
	public void ToVerifyThatBuildIsFinishedFromConsoleAndFindStatus() {
		driver = tc.ToVerifyThatBuildIsFinishedFromConsole(driver);
	}

	@Test(priority=11,groups= {"Create_New_Project_group"})
	public void ClickOnNewItem() {
		driver = tc.ClickOnNewItem(driver);
	}

	@Test(priority=12)
	public void EnterThenameofthenewProjectintheinputBox() {
		driver = tc.EnterThenameofthenewProjectintheinputBox(driver,projectName);
	}

	@Test(priority=12,groups= {"Create_New_Project_group"})
	public void Enter_The_name_of_the_new_Project_in_theinput_Box_from_xlsx() {

		driver = tc.EnterThenameofthenewProjectintheinputBox(driver,projectName);
	}




	@Test(priority=13, groups= {"Create_New_Project_group"})
	public void Select_maven_project_byclick() {
		driver = tc.Select_maven_project_byclick(driver);
	}

	@Test(priority=14, groups= {"Create_New_Project_group"})
	public void Click_OK_after_Select_maven_project_byclick() {
		driver = tc.Click_OK_after_Select_maven_project_byclick(driver);
	}

	@Test(priority=15, groups= {"Create_New_Project_group"})
	public void get_the_url_of_the_current_page() {
		driver = tc.get_the_url_of_the_current_page(driver);
	}

	@Test(priority=16, groups= {"Create_New_Project_group"})
	public void Click_the_checkbox_This_project_is_parameterized() {
		driver = tc.Click_the_checkbox_This_project_is_parameterized(driver);
	}

	@Test(priority=17, groups= {"Create_New_Project_group"})
	public void click_the_check_box_for_Discard_old_builds() {
		driver = tc.click_the_check_box_for_Discard_old_builds(driver);
	}

	@Test(priority=18, groups= {"Create_New_Project_group"})
	public void Select_Choice_parameter_from_Add_Parameter_of_This_project_is_parameterized() {
		driver = tc.Select_Choice_parameter_from_Add_Parameter_of_This_project_is_parameterized(driver);
	}

	@Test(priority=19,groups= {"Create_New_Project_group"})
	@Parameters({"parameterName"})
	public void Type_Name_for_Choice_Parameter(String parameterName) {
		driver = tc.Type_Name_for_Choice_Parameter(driver, parameterName);

	}

	@Test(priority=20,groups= {"Create_New_Project_group"})
	@Parameters({"parameterChoices"})
	public void Type_Choices_for_Choice_Parameter(String parameterChoices) {
		driver = tc.Type_Choices_for_Choice_Parameter(driver, parameterChoices);

	}

	@Test(priority=21,groups= {"Create_New_Project_group"})
	@Parameters({"goalsAndOptions"})
	public void Type_Goals_and_Options_in_BUILD(String goalsAndOptions) {
		driver = tc.Type_Goals_and_Options_in_BUILD(driver, goalsAndOptions);

	}
	@Test(priority=22,groups= {"Create_New_Project_group"})
	public void Click_Advanced_Button_Of_BUILD() {
		driver = tc.Click_Advanced_Button_Of_BUILD(driver);
	}

	@Test(priority=23,groups= {"Create_New_Project_group"})
	public void Click_Custom_workspace_Checkbox() {
		driver = tc.Click_Custom_workspace_Checkbox(driver);
	}

	@Test(priority=24, groups= {"Create_New_Project_group"})
	@Parameters({"projDirectoryName"})
	public void Enter_text_to_Directory_Custom_workspace(String projDirectoryName) {
		driver = tc.Enter_text_to_Directory_Custom_workspace(driver, projDirectoryName);
	}

	@Test(priority=30, groups= {"Create_New_Project_group"})
	public void Click_the_floating_Apply_Button() {
		driver = tc.Click_the_floating_Apply_Button(driver);

	}

	@Test(priority=32, groups= {"Create_New_Project_group"})
	public void Click_the_floating_SAVE_Button() {
		driver = tc.Click_the_floating_SAVE_Button(driver);
	}
	@Test(priority=35, groups= {"Create_New_Project_group"})
	public void Verify_that_project_is_created_successfully() {
		driver = tc.Verify_that_project_is_created_successfully(driver);
	}


	
	@Test(dataProvider="DP_for_projects_Name")
	public void Create_many_proj_take_name_from_xls_file(String projectName) {
		driver = tc.openBrowser(driver, browserType, url, driverpath );
		driver.manage().window().maximize();
		driver = tc.loginPage(driver, jenkinuserName, jenkinpassword);

		driver = tc.ClickOnNewItem(driver);
		driver = tc.EnterThenameofthenewProjectintheinputBox(driver,projectName);
		driver = tc.Select_maven_project_byclick(driver);

		driver = tc.Click_OK_after_Select_maven_project_byclick(driver);
		driver = tc.get_the_url_of_the_current_page(driver);
		
		driver = tc.Click_the_checkbox_This_project_is_parameterized(driver);
		driver = tc.click_the_check_box_for_Discard_old_builds(driver);
		driver = tc.Select_Choice_parameter_from_Add_Parameter_of_This_project_is_parameterized(driver);
		
		driver = tc.Type_Name_for_Choice_Parameter(driver, parameterName);
		driver = tc.Type_Choices_for_Choice_Parameter(driver, parameterChoices);
		driver = tc.Type_Goals_and_Options_in_BUILD(driver, goalsAndOptions);
		
		driver = tc.Click_Advanced_Button_Of_BUILD(driver);
		driver = tc.Click_Custom_workspace_Checkbox(driver);
		driver = tc.Enter_text_to_Directory_Custom_workspace(driver, projDirectoryName);

		driver = tc.Click_the_floating_Apply_Button(driver);
		driver = tc.Click_the_floating_SAVE_Button(driver);
		driver = tc.Verify_that_project_is_created_successfully(driver);
		driver = tc.LogoutJenkinPage(driver);
		driver.close();

	}

	@Test(priority=90)
	@Parameters({"projectToBeDeleted"})
	public void Delete_an_Existing_Project(String projectToBeDeleted) {
		driver = tc.Delete_an_Existing_Project(driver, projectToBeDeleted);
	}



	@Test(priority=91, dataProvider="DP_for_projects_Name")
	public void Delete_Existing_Projects_with_DP(String projectToBeDeleted) {
		//System.out.println("the ane of ::"+projectToBeDeleted);
		driver = tc.Delete_an_Existing_Project(driver, projectToBeDeleted);
	}





	@DataProvider(name="DP_for_projects_Name")
	public Object[][] DataProvider_for_projects_Name() {
		Object[][] dataAll=null;
		try {

			//dataAll = tc.xlsxReaderAllRowsAllColumns("C:/workspace/SelFrame/src/com/FWV1A/runner/Jenkin_DP.xlsx", "ProjectToBeDeleted");
			dataAll = tc.xlsxReaderAllRowsAllColumns(filepath, sheetname);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataAll;
	}


	//@BeforeTest
	//@Parameters({"browserType","url","driverpath"})
	public void OpenBrowser(String browserType, String url, String driverpath) {
		driver = tc.openBrowser(driver, browserType, url, driverpath );
		driver.manage().window().maximize();
		Reporter.log("The browser is opened successfully. ");
	}

	@AfterTest(groups= {"Create_New_Project_group"})
	public void CloseBrowser() {
		driver.navigate().refresh();
		driver.close();
		Reporter.log("The browser is closed successfully. ");
	}

	//@BeforeSuite
	//@Parameters({"filepath","sheetname"})
	public void beforeSuiteReportingOutputFolder() {
		//this.filepath = filepath;
		//this.sheetname = sheetname;

		//Run1 run1 = (Run1)ctx;
		//run1.setOutputDirectory("D:/selenium/report/");
		System.out.println("inside before Suite");
	}

	public void setOutputDirectory(String abc) {

	}

	/*
	 * public static void main(String[] args) { System.out.println("hi");
	 * 
	 * }
	 */
}
