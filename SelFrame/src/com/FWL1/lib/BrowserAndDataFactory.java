package com.FWL1.lib;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BrowserAndDataFactory {
	static Logger log = Logger.getLogger(BrowserAndDataFactory.class); 
	String chromedriverpath;
	String firefoxdriverpath;
	String iedriverpath;
	Helper help;

	public BrowserAndDataFactory() {
		help = new Helper();
		String projectPath = System.getProperty("user.dir");
		DOMConfigurator.configure(projectPath+help.getValueFrom("log4jxmlpath"));

		chromedriverpath = help.getValueFrom("chromedriverpath");
		firefoxdriverpath = help.getValueFrom("firefoxdriverpath");
		iedriverpath = help.getValueFrom("iedriverpath");

	}


	public WebDriver initiateBrowser(WebDriver driver, String browserType, String url) {
		if(browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",chromedriverpath );
			driver = new ChromeDriver();
			log.info("browserFactory>initiateBrowser> chrome opened. ");
			Reporter.log("Chrome browser is launched. ");
		}

		else if(browserType.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver",firefoxdriverpath );
			driver = new FirefoxDriver();
			log.info("browserFactory>initiateBrowser> firefox opened. ");
			Reporter.log("Firefox browser is launched. ");
		}else if(browserType.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",iedriverpath );
			driver = new InternetExplorerDriver();
			log.info("browserFactory>initiateBrowser> ie opened");
			Reporter.log("IE browser is launched. ");
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
		@SuppressWarnings("resource")
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



	///------JSON Writer and Reader
	public  String jWriter() {
		String jsonstring=null;	
		try {
			// create a writer
			BufferedWriter writer = Files.newBufferedWriter(Paths.get("D://test001.json"));

			// create a map for parent  properties
			Map<String, Object> parentNode = new HashMap<String, Object>();

			// create loginPage
			Map<String, String> loginPage = new HashMap<String, String>();
			loginPage.put("loginName","By.id(\"j_username\")" );
			loginPage.put("passWord","By.name(\"j_password\")" );

			// add loginPage to parent
			parentNode.put("loginPage", loginPage);



			// create ObjectMapper instance
			ObjectMapper mapper = new ObjectMapper();

			// write JSON to file
			writer.write(mapper.writeValueAsString(parentNode));

			jsonstring = mapper.writeValueAsString(parentNode);

			//close the writer
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return jsonstring;

	}


	public By jSONReader(String nodeName, String key) {
		JsonNode parser=null;
		try {
			// create a reader
			//Reader filereader = Files.newBufferedReader(Paths.get("D://test001.json"));
			Reader filereader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+help.getValueFrom("ObjRepository")));
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();

			//read test0001.json file into tree model
			//JsonNode parser = objectMapper.readTree(reader);
			parser = objectMapper.readTree(filereader);

			// reading the return from jWriter method
			//parser = objectMapper.readTree(jWriter());

			//JsonNode parser = objectMapper.readTree(jWriter());
			//System.out.println("bolisKi::"+parser.path("loginPage").path("loginName").asText().trim());

			//close reader
			filereader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String valueofKey = parser.path(nodeName).path(key).asText().trim();
		//return parser.path(nodeName).path(key).asText().trim();
		//System.out.println("ebaba::"+valueofKey);
		return locatorParser(valueofKey);
	}	

	public By locatorParser(String locator) {
		By loc = By.id(locator);
		if(locator.contains("By.id")) {
			loc = By.id(locator.substring(locator.indexOf("By.id") +7, locator.length() -2) );
			//loc = By.id(locator.replaceFirst("By.id", ""));
			System.out.println("---"+locator.replaceFirst("By.id",""));
			System.out.println("huy@ "+loc);
		}else	
			if(locator.contains("By.name")) {
				//loc = By.name(locator.substring(locator.indexOf("\"") +1, locator.length() -2) );
				loc = By.name(locator.substring(locator.indexOf("By.name") +9, locator.length() -2) );
			}else	
				if(locator.contains("By.xpath")) {
					loc = By.xpath(locator.substring(locator.indexOf("By.xpath") +10, locator.length() -2) );
				}


		return loc;

	}

	public String jData(String nodeName, String key) {
		JsonNode parser=null;
		try {
			Reader filereader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+help.getValueFrom("JsonDataRep")));
			ObjectMapper objectMapper = new ObjectMapper();
			parser = objectMapper.readTree(filereader);
			filereader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String valueofKey = parser.path(nodeName).path(key).asText().trim();
		System.out.println("ebaba::"+valueofKey);
		return valueofKey;
	}	


	public JsonNode jsonNodeArrayReader(String jsonfilepath) {
		JsonNode parser=null;
		try {
			//Reader filereader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+help.getValueFrom("JsonDataRep")));
			Reader filereader = Files.newBufferedReader(Paths.get(jsonfilepath));
			ObjectMapper objectMapper = new ObjectMapper();
			parser = objectMapper.readTree(filereader);
			filereader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//String valueofKey = parser.path(nodeName).path(key).asText().trim();
		//System.out.println("ebaba::"+valueofKey);
		return parser;
	}	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" im factory");
		//new BrowserAndDataFactory().jWriter();
		//System.out.println("maakali::"+new BrowserAndDataFactory().jSONReader("loginPage","userName").toString());
		//System.out.println("maakali::"+new BrowserAndDataFactory().jData("addUserPage","userName").toString());
		//new BrowserAndDataFactory().callmethod();

	}
	public void callmethod() {
//		jsonNodeArrayReader(System.getProperty("user.dir")+help.getValueFrom("JsonDataRep"));
		jsonNodeArrayReader("ha");
	}


	//@Test(dataProvider="datP2")
	public void testBB(Object node) {
		testC(node);
	}

	public void testC(Object node) {
		JsonNode addUserPage=(JsonNode)node;
		System.out.println("testcase:: "+addUserPage.path("userName"));
		System.out.println("testcase:: "+addUserPage.path("passWord"));
		System.out.println("testcase:: "+addUserPage.path("confpassWord"));
		System.out.println("testcase:: "+addUserPage.path("fullname"));
		System.out.println("testcase:: "+addUserPage.path("email"));
	}

	@DataProvider(name="datP2")
	public Object[] dataP() {
		help = new Helper();
		String jsonfilepath = System.getProperty("user.dir")+help.getValueFrom("JsonDataRep");
		String nodeName="addUserPage";
		JsonNode allNodes = new BrowserAndDataFactory().jsonNodeArrayReader(jsonfilepath);
		//To read the addUserPage node only.
		int sizeofArray = allNodes.path(nodeName).size();
		Object[] objecttoStorenodeArray = new Object[sizeofArray];
		for(int count=0;count<sizeofArray;count++) {
			objecttoStorenodeArray[count]=allNodes.path(nodeName).path(count);
		}
		return objecttoStorenodeArray;
	}


//////////////////
	
	@Test(dataProvider="datP2b")
	public void testlo(Object node) {
		testlog(node);
	}

	public void testlog(Object node) {
		JsonNode loginPage=(JsonNode)node;
		System.out.println("testcase:: "+loginPage.path("browserType").toString());
		/*
		 * System.out.println("testcase:: "+addUserPage.path("passWord"));
		 * System.out.println("testcase:: "+addUserPage.path("confpassWord"));
		 * System.out.println("testcase:: "+addUserPage.path("fullname"));
		 * System.out.println("testcase:: "+addUserPage.path("email"));
		 */
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