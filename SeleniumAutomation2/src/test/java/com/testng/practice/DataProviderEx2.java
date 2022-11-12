package com.testng.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderEx2 {
	
	XSSFWorkbook wb;
	XSSFSheet sh;
	
	
	@BeforeClass
	public  void inilExcel() throws IOException {
		FileInputStream fis = new FileInputStream(new File("E:\\TestData\\InputData.xlsx"));
		wb = new XSSFWorkbook(fis); //xls --> HSSFWorkbook
		sh = wb.getSheet("Data");
	}
	
	@AfterClass
	public void closeExcel() throws IOException {
		wb.close();
	}
	
	@Test(dataProvider = "addition")
	public void add(int a,int b,int c) {
		int value = a+b;
		Assert.assertEquals(value, c);
	}
	

	
	
	@DataProvider(name="addition")
	public Object[][] data(){
		
		//Row Count
		int rowCount = sh.getPhysicalNumberOfRows();
		int cols = sh.getRow(0).getPhysicalNumberOfCells();
		
		Object[][] obj = new Object[rowCount-1][cols]; //[6][3] -->[0][0] -->[6][3] -->[7][0]
		
		for(int i=0;i<rowCount-1;i++) {
			for(int j=0;j<cols;j++) {
				
				obj[i][j] = (int) sh.getRow(i+1).getCell(j).getNumericCellValue();
			}
		}

		for(int i=0;i<rowCount-1;i++) {
			for(int j=0;j<cols;j++) {
				System.out.println("Row is "+ i +" Col is "+ j  +" actual value is "+ obj[i][j]);
			}
		}
		return obj;
		
	}
	


}