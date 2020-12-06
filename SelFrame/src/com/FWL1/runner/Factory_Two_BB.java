package com.FWL1.runner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.FWL1.lib.*;
public class Factory_Two_BB {
	Helper help;
	BrowserAndDataFactory dfact;

	@Factory(dataProvider="datP2b")
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


@Test
public void test234() {
	System.out.println("helllo sier ");
}
	}

