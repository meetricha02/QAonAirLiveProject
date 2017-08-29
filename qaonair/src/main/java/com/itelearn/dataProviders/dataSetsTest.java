package com.itelearn.dataProviders;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.itelearn.Properties.PropertyFileReaderTest;
import com.itelearn.TestBase.BaseTest;
import com.itelearn.Utility.ExcelUtilityTest;
import com.itelearn.Utility.SeleniumHelperTest;


public class dataSetsTest{
	//Reads the data from the dataprovider method in the class
	//Executes the test for both sets of data
	
	
	String key, eid, type, value;
	WebDriver driver;
	SeleniumHelperTest s;
	ExcelUtilityTest e;

	@BeforeTest
	// Setting the Chrome Browser property
	public void settingBrowserProperties() {
		String prop1 = PropertyFileReaderTest.getProperty("setUpName");
		String prop2 = PropertyFileReaderTest.getProperty("setUpPath");
		System.setProperty(prop1, prop2);
	}
	
	
	@BeforeMethod
	public void initialization(){
		 e = new ExcelUtilityTest();
		 s = new SeleniumHelperTest();
	}

	@Test(dataProvider = "logindata")
	public void loginDataSet( String Uname, String Pwd) throws IOException, InterruptedException {

		String Testcase = "TC_valid_dataset_001";

		String[][] testSteps = e.readExcel("TestSteps");
		int rows = e.sheetRows;

		for (int a = 1; a < rows; a++) {
			String TCID = testSteps[a][1];
		    value = testSteps[a][11];
		    type= testSteps[a][12];
			key = testSteps[a][5];
			//System.out.println("Keyword is :-----------" + key);
			eid = testSteps[a][8];

			//If the value of the cell is "Username", then get the data from data providers//
			if (value.equals("Username")) {

				System.out.println("UserName is :" + value);
				value = Uname;
			}

			//If the value of the cell is "Password", then get the data from data providers//

			if (value.equals("Password")) {

				System.out.println("Password is :" + value);

				value = Pwd;
			}
			if (TCID != null && TCID.equalsIgnoreCase(Testcase)) {
				//Value from the data providers(Uname/Pwd) passed to the execute method in Selenium Helper Class//
				s.execute(key, value, eid, type);
			}

		}

	}

	// Using Data Providers to Login//
	@DataProvider(name = "logindata")
	@Test
	public static Object[][] dataSet() throws IOException {
		Object[][] obj = new Object[2][2];

		//		String[][] SheetDataSets = e.readExcel("Sheet1");
		//		int rowNum= e.sheetRows;
		//		for(int i=1;i<rowNum;i++ ){
		//		String	Uvalue= SheetDataSets[i][1];
		//		String	Pvalue= SheetDataSets[i][2];

		// [1] Username
		// [2] Password

			
		obj[1][0] ="annl@gmail.com" ;
		obj[1][1] = "12345";
		
		obj[0][0] ="tomt@gmail.com" ;
		obj[0][1] ="123456";
		
			
		return obj;

	}
	
	}


