package com.FWL1.runner;

import java.io.IOException;



import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.FWL1.testcases.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.FWL1.lib.*;
public class Factory_One {
	Helper help;
	BrowserAndDataFactory dfact;

	@Factory(dataProvider="data_for_new_project_creation")
	public Object[]	Factory_for_new_project_creation(String browserType ,String url ,String driverpath ,String jenkinuserName ,String jenkinpassword ,String projectNameToBeCreated ,String parameterName ,String parameterChoices ,String	goalsAndOptions ,String projDirectoryName ) {

		System.out.println("------------------"+projectNameToBeCreated);

		HashMap<String, String> hmp = new HashMap<String, String>(); 
		hmp.put("browserType", browserType); 
		hmp.put("url",url);
		hmp.put("driverpath",driverpath);
		hmp.put("jenkinuserName",jenkinuserName);
		hmp.put("jenkinpassword",jenkinpassword);
		hmp.put("projectNameToBeCreated",projectNameToBeCreated);
		hmp.put("parameterName",parameterName);
		hmp.put("parameterChoices",parameterChoices);
		hmp.put("goalsAndOptions",goalsAndOptions);
		hmp.put("projDirectoryName",projDirectoryName); 
		Object[] obj = new Object[1];
		obj[0]=hmp;
		return new Object[]{ 
				new Runner_One(hmp) }; 
		}
	
	@DataProvider(name = "data_for_new_project_creation")
	public Object[] Data_for_new_project_creation() {
		help = new Helper();
		String[][] dataAll = null;
		dfact = new BrowserAndDataFactory();
		try {
			dataAll = dfact.xlsxReaderAllRowsAllColumns(help.getValueFrom("excelfilepath"), help.getValueFrom("sheetnameforNewProject"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataAll;

	}

	
////---------------------
	@Test(dataProvider="datP2b")
	public void testlo(Object node) {
		testlog(node);
	}

	public void testlog(Object node) {
		JsonNode loginPage=(JsonNode)node;
		System.out.println("testcase:: "+loginPage.path("browserType"));
		/*
		 * System.out.println("testcase:: "+addUserPage.path("passWord"));
		 * System.out.println("testcase:: "+addUserPage.path("confpassWord"));
		 * System.out.println("testcase:: "+addUserPage.path("fullname"));
		 * System.out.println("testcase:: "+addUserPage.path("email"));
		 */
	}

	//@Factory(dataProvider="datP2b")
	public Object[] Factory_to_run_with_login_browser_type_url(Object node) {
		
		return new Object[]{ 
				new Runner_One(node) }; 
		}


	@DataProvider(name="datP2b")
	public Object[] dataPb() {
		help = new Helper();
		String jsonfilepath = System.getProperty("user.dir")+help.getValueFrom("JsonDataRep");
		String nodeName="loginPage";
		JsonNode allNodes = new BrowserAndDataFactory().jsonNodeArrayReader(jsonfilepath);
		//To read the addUserPage node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodeArray = new Object[sizeofArray];
		for(int count=0;count<sizeofArray;count++) {
			objecttoStorenodeArray[count]=allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodeArray;
	}




	}

