package com.qa.Opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil  {

	public static String TEST_DATA_SHEET_PATH="./src/main/java/com/qa/Opencart/testdata/Testdata.xlsx";
			
	
	private static Workbook book;
	private static Sheet sheet;
	public static Object[][] getTestData(String sheetname) {
		Object  data[][]=null;
		try {
			FileInputStream fis=new FileInputStream(TEST_DATA_SHEET_PATH);//navigate to excelpath
			book=WorkbookFactory.create(fis);//creates a copy of the workbook in the internal memory
			sheet=book.getSheet(sheetname);//gets the sheet name as a reference and traverses to the sheet
			
			   data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			 //for loop for row in the excel sheet
			 for(int i=0;i<sheet.getLastRowNum();i++) {
				 
				 for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					 
					 data[i][j]=sheet.getRow(i+1).getCell(j);
				 }
			 }
					 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return data;
	}
}
