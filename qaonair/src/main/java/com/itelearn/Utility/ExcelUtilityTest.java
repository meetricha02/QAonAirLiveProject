package com.itelearn.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.Test;

import com.itelearn.Properties.PropertyFileReaderTest;


public class ExcelUtilityTest {
	public int sheetRows, sheetCols;
//String[][] xData, xTC;        
	//public int nRows;
	//public int rEID, rTC,rdata, rDataSet;
	//public String[][] xElement;
	public HSSFSheet sheet;
	HSSFRow row;
	HSSFCell cell;
	
	

@Test
public String[][] readExcel(String Sheetname) throws IOException{
	
	System.out.println("+++++++++++++++Reading Excel Sheet+++++++++");
	//Get xlpath from Properties File//
	String fpath=PropertyFileReaderTest.getProperty("xlpath");
	FileInputStream fIn= new FileInputStream(fpath);
	HSSFWorkbook wb = new HSSFWorkbook(fIn); 
	 sheet= wb.getSheet(Sheetname);
			
	//Calculating Rows and Columns//
	//**HSSFRow row= sheet.getRow(0);
	  sheetRows=sheet.getLastRowNum()+1;
	 sheetCols=sheet.getRow(0).getLastCellNum();
//	System.out.println("No. of rows are "+ sheetRows);
//	System.out.println("No. of Columns are "+ sheetCols);
	
	String[][] xData = new String[sheetRows][sheetCols];
	for (int i=1; i<sheetRows;i++){
		 row= sheet.getRow(i);
		// System.out.println("ROW OBJECT AT "+i+row);
		for(int j=0;j<sheetCols;j++){
		//	System.out.println("VALUE OF COL :"+j+"        "+ row.getCell(j) );
			cell= row.getCell(j);            
			String testcaseValue = "-";
			if (cell!=null){
				testcaseValue = cellToString(cell);
				//System.out.println("The Sheet values are:"+ testcaseValue);
				xData[i][j]= testcaseValue;
			}
		}
	}
	return xData;
	
}
	

	

	@Test	
	public static String cellToString(HSSFCell cell) { 
		// This function will convert an object of type excel cell to a string value
		int type = cell.getCellType();                        
		Object result;                        
		switch (type) {                            
		case HSSFCell.CELL_TYPE_NUMERIC: //0                                
			result = cell.getNumericCellValue();                                
			break;                            
		case HSSFCell.CELL_TYPE_STRING: //1                                
			result = cell.getStringCellValue();                                
			break;                            
		case HSSFCell.CELL_TYPE_FORMULA: //2                                
			throw new RuntimeException("We can't evaluate formulas in Java");  
		case HSSFCell.CELL_TYPE_BLANK: //3                                
			result = "%";                                
			break;                            
		case HSSFCell.CELL_TYPE_BOOLEAN: //4     
			result = cell.getBooleanCellValue();       
			break;                            
		case HSSFCell.CELL_TYPE_ERROR: //5       
			throw new RuntimeException ("This cell has an error");    
		default:                  
			throw new RuntimeException("We don't support this cell type: " + type); 
		}                        
		return result.toString();      
	}

		
	}
	
	
	
	
	

