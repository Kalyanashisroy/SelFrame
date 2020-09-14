package com.FWV1A.runner;


import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import com.FWV1A.testcases.*;
public class Factory_create_New_Projects {
	TestCases_for_Jenkins readxcel;
	
	@Factory(dataProvider="DP_for_projects_Name")
	public Object[] FactoryToCreateNewProjects(String projectNam) {

		System.out.println("Project name from the excel sheet ::"+projectNam);
		return new Object[]{
				new Runner_for_Jenkin_project(projectNam)
		};
	}
	
	@DataProvider(name="DP_for_projects_Name")
	public Object[][] DataProvider_for_projects_Name(){	
		Object[][] dataAll=null; 
		readxcel = new TestCases_for_Jenkins(); 
		try {

			dataAll = readxcel.xlsxReaderAllRowsAllColumns("C:/workspace/SelFrame/src/com/FWV1A/runner/Jenkin_DP.xlsx","ProjectToCreate"); 
			//dataAll = tc.xlsxReaderAllRowsAllColumns(filepath,sheetname);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return dataAll;
	}

}
