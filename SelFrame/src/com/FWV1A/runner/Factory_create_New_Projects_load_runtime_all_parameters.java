package com.FWV1A.runner;

import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.FWV1A.testcases.*;
public class Factory_create_New_Projects_load_runtime_all_parameters {
	TestCases_for_Jenkins readxcel;
	String filepath="C:/workspace/SelFrame/src/com/FWV1A/runner/Jenkin_DP.xlsx";
	String sheetname="TEST";
	String sheetnameforNewProject="NewProjectsAllValues";


	//@Factory(dataProvider="data_hashMap")
	public Object[] FactoryToCreateNewProjects_hashMP(String name, String mobile, String city, String country ) {

		HashMap<String, String> hmp = new HashMap<String,String>();
		hmp.put("name", name);
		hmp.put("mobile",mobile);
		hmp.put("city",city);
		hmp.put("country",country);
		return new Object[]{
				new Runner_Create_new_project_load_all_parameters_at_runtime_from_xls(hmp.toString())
		};
	}


	@DataProvider(name="data_hashMap")
	public Object[] data_hashMap() {

		String[][] dataAll=null; 

		readxcel = new TestCases_for_Jenkins(); 
		try {

			//dataAll = readxcel.xlsxReaderAllRowsAllColumns("C:/workspace/SelFrame/src/com/FWV1A/runner/Jenkin_DP.xlsx","TEST"); 
			dataAll = readxcel.xlsxReaderAllRowsAllColumns(filepath,sheetname);
		} catch (IOException e) { 
			e.printStackTrace();
		}

		return dataAll;

	}

	@Factory(dataProvider="data_for_new_project_creation")
	public Object[] Factory_for_new_project_creation(String browserType	,String  url	,String  driverpath	,String jenkinuserName	,String jenkinpassword	,String projectNameToBeCreated	,String parameterName	,String parameterChoices	,String goalsAndOptions	,String projDirectoryName ) {

		HashMap<String, String> hmp = new HashMap<String,String>();
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
		return new Object[]{
				new Runner_Create_new_project_load_all_parameters_at_runtime_from_xls(hmp.toString())
		};
	}


	@DataProvider(name="data_for_new_project_creation")
	public Object[] Data_for_new_project_creation() {

		String[][] dataAll=null; 

		readxcel = new TestCases_for_Jenkins(); 
		try {

			//dataAll = readxcel.xlsxReaderAllRowsAllColumns("C:/workspace/SelFrame/src/com/FWV1A/runner/Jenkin_DP.xlsx","TEST"); 
			dataAll = readxcel.xlsxReaderAllRowsAllColumns(filepath,sheetnameforNewProject);
		} catch (IOException e) { 
			e.printStackTrace();
		}

		return dataAll;

	}




	//String browserType	,String  url	,String  driverpath	,String jenkinuserName	,String jenkinpassword	,String projectNameToBeCreated	,String parameterName	,String parameterChoices	,String goalsAndOptions	,String projDirectoryName
	//sheetname below
	//NewProjectsAllValues

}
