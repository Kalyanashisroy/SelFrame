package com.FW2B.Lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.ss.usermodel.DataFormatter;
public class ExcelDataRW {
	public static DataFormatter formatter= new DataFormatter();

	public	static void main(String args[] ) throws IOException{
	}



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
		String value=formatter.formatCellValue(cell);
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
}

