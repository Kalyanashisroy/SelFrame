package com.FWV1A.runner;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FWV1A.testcases.*;
//import com.FWV1A.lib.*;

public class Runner_Create_new_project_load_all_parameters_at_runtime_from_xls {
	//static Logger log = Logger.getLogger(Runner_for_Jenkin_project.class);
	WebDriver driver;
	TestCases_for_Jenkins tc;
	String AllParametersFromFact;
	//HelperOne helpone;
	public Runner_Create_new_project_load_all_parameters_at_runtime_from_xls() {
		//DOMConfigurator.configure("./src/com/FWV1A/lib/log4j_FWV1A.xml");
		//tc = new TestCases_for_Jenkins();
		//helpone = new HelperOne();
		//The above code is moved to beforeClass because factory cannot run the default constructor.
	}

	public Runner_Create_new_project_load_all_parameters_at_runtime_from_xls(String AllParametersFromFact) {
		this.AllParametersFromFact = AllParametersFromFact;

	}

	@Test(groups= {"Factory_create_new_project"})
	public void testtogetvaluefromHMap() {

		System.out.println("the value of the browser911 ::"+getValueFromKey("browserType"));
	}

	public String getValueFromKey(String key) {
		// converting the AllParameters  string to hashmap, so that the 
		// Value of the parameters can be accessed by its key.
		String value;
		value= AllParametersFromFact.substring(1, AllParametersFromFact.length()-1 );
		String[] keyvaluepairs =value.split(",");
		HashMap<String , String> mpp = new HashMap<String, String>();
		for(String pair:keyvaluepairs) {
			String[] entry = pair.split("=");
			mpp.put(entry[0].trim(), entry[1].trim());
		}

		return mpp.get(key);
	}

	@BeforeClass(groups= {"Factory_create_new_project"})
	public void To_create_objects_of_the_imported_class() {
		tc = new TestCases_for_Jenkins();
		//helpone = new HelperOne();
		System.out.println("Objects of the imported class created. ");
		Reporter.log("Objects of the imported class created.. ");
	}

	@Test(priority=0, groups= {"Factory_create_new_project"})
	public void OpenBrowserForJenkin() {
		driver = tc.openBrowser(driver, getValueFromKey("browserType"),getValueFromKey("url"),getValueFromKey("driverpath") );
		driver.manage().window().maximize();
		Reporter.log("The browser is opened successfully. ");
	}



	@Test(priority=1, groups= {"Factory_create_new_project"} )
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


	@Test(priority=100, groups= {"Factory_create_new_project"})
	public void LogoutJenkinPage(){
		driver = tc.LogoutJenkinPage(driver);
		Reporter.log("The logout is done successfully");
	}

	
	@AfterTest(groups= {"Factory_create_new_project"})
	public void CloseBrowser() {
		driver.navigate().refresh();
		driver.close();
		driver.quit();
		Reporter.log("The browser is closed successfully. ");
	}

}



