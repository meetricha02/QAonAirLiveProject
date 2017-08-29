	package com.itelearn.Listeners;

	import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.itelearn.Utility.SeleniumHelperTest;


	public class ListenerTest extends TestListenerAdapter {


		// ListenerTest to check if the method has failed, so tales the Screenshot
		@Override
	public void onTestSuccess(ITestResult tr) {

		 	if(ITestResult.SUCCESS==tr.getStatus())
		{
		try 
		{
		TakesScreenshot ts=(TakesScreenshot)SeleniumHelperTest.driver;
		 
		File source=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("./Screenshots/"+tr.getName()+".png"));
		 
		System.out.println("Screenshot taken!!!!!!!!!");
		} 
		catch (Exception e)
		{
		System.out.println("Exception while taking screenshot "+e.getMessage());
		} 
		 
		}
		}

	}
