package com.itelearn.TestBase;


import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.itelearn.Utility.ExcelUtilityTest;
import com.itelearn.Utility.SeleniumHelperTest;
import com.itelearn.dataProviders.dataSetsTest;


public class BaseTest {

	//String keyword4, vEID4, vTDataName4;
	public String type;

	WebDriver driver;
	String TestCaseName;
	String RunMode;
	String xkeyword, xEID, vTDataNames;


	SeleniumHelperTest sh= new SeleniumHelperTest();
	ExcelUtilityTest eu= new ExcelUtilityTest();


	Logger log= LogManager.getLogger(BaseTest.class.getName()); 


	@Test
	public void basetest() throws IOException, InterruptedException{
		log.info("--------------Reading test cases----------------");

		String[][] testCaseSheet= eu.readExcel("TestCases");
		int sheetRowNum= eu.sheetRows;

		String[][] testStepSheet	= eu.readExcel("TestSteps");

		for(int k=1;k<sheetRowNum;k++){
			TestCaseName=	testCaseSheet[k][1];
			RunMode= testCaseSheet[k][3];
			log.info("The test case name is:"+ TestCaseName);
			System.out.println("The test case name is:"+ TestCaseName);


			if(RunMode.equalsIgnoreCase("Y")){
				log.info("*************The Test case under execution is *************:"+TestCaseName );
		/*		if(TestCaseName.equals("TC_valid_dataset_001")){
					dataSetsTest ds= new dataSetsTest();
					ds.loginDataSet( vUsername, vPwd);	
				}
				
		*/		
				log.info("--------  Reading the TestSteps Sheet ----------");


				int	TestStepRows= testStepSheet.length;

				for (int y=1;y<TestStepRows;y++){
					String	vTCID=testStepSheet[y][1];


					if(TestCaseName.equals(vTCID)){
						xkeyword= testStepSheet[y][5];
						xEID= testStepSheet[y][8];
						vTDataNames=testStepSheet[y][11];
						type= testStepSheet[y][12];

						//System.out.println("------------------Reading the EID sheet-------------");
						//String[][] EIDdataSheet= eu.readExcel("EID");
						//for(int m=1; m<eu.sheetRows;m++){
						//type =EIDdataSheet[m][2];
						//System.out.println("The EID sheet locator data is :"+ type);
						//}
						try{
							sh.execute(xkeyword,vTDataNames,xEID,type);
						} catch(Exception e){
							System.out.println("An error has occured!!!" + e);
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	@AfterTest
	public void teardown(){
		sh.getDriver().quit();

	}

	
}
