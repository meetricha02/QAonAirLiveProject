	package com.itelearn.Utility;

	import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.itelearn.Properties.PropertyFileReaderTest;


	public class SeleniumHelperTest {
		public static WebDriver driver;
		String url;
		ExcelUtilityTest et= new ExcelUtilityTest();
		
		public Logger log=LogManager.getLogger(SeleniumHelperTest.class.getName());

		
		public void execute(String keyword, String fdata, String fElement, String fElementType) throws IOException, InterruptedException{
			switch(keyword) {

			case "openBrowser":
				openBwsr();
				break;

			case "navigateBrowser":
				navigateBwsr();
				break;

			case "clickLink":
				clicklnk(fElement,fElementType);
				break;
			
			case "clickButton":
				clickbtn(fElement,fElementType);
				break;

			case "typeText":
				typTxt(fElement, fdata, fElementType);
				break;

			case "verifytext":
				verifytxt(fElement, fdata, fElementType );
				break;



			case "verifyTitle":
				verifyPageTitle(fdata);
				break;


			case "maximizeWindow":
				maximizeWindow();
				break;


			case "mouseHover":
				mouseHover(fElement,fdata,fElementType);
				break;



			case "wait":
				wtTime(40);
				break;
			}

		}	


		
		// To check the element type//
		public WebElement elementTyp(String fElement, String fElementType){
			if(fElementType.equalsIgnoreCase("linkText")){
				return driver.findElement(By.linkText(fElement));
			}
			else if(fElementType.equalsIgnoreCase("cssSelector")){ 
				return driver.findElement(By.cssSelector(fElement)); 
			}
			else {
				return driver.findElement(By.xpath(fElement)); 
			}
			
		}
		

		public void openBwsr() throws IOException{

			log.info("-----------Opening the Browser---------");
			String browser= PropertyFileReaderTest.getProperty("Browser");
			if(browser.equals("Chrome")){
				String driverName=	PropertyFileReaderTest.getProperty("setUpName");
				String driverPath=	PropertyFileReaderTest.getProperty("setUpPath");

				System.setProperty(driverName, driverPath);
				driver= new ChromeDriver();
				log.info("Opening Chrome browser");
			}else
			{	String driverName=	PropertyFileReaderTest.getProperty("setUpNameFirefox");
				String driverPath=	PropertyFileReaderTest.getProperty("setUpPathFirefox");

				System.setProperty(driverName, driverPath);
				driver= new FirefoxDriver();
				log.info("Opening Firefox browser");	
			}
		}


		
		public void navigateBwsr() throws IOException{
			log.info("-----------Navigate to the URL---------");
			String url = PropertyFileReaderTest.getProperty("URL");
			log.info("Navigating to url ");
			driver.navigate().to(url);

		}

		
		public void clickbtn(String vElement,String vType){
			log.info("-----------Clicking the button--------- for element " + vElement);
		
			if(vElement != null && !vElement.trim().equals("-")){
				driver.findElement(By.xpath(vElement)).click();
			}
		}


		//Getting the webdriver for FreelProjectsNoLogin Class Screenshot method//
		public WebDriver getDriver() {
			return driver;
		}


		
		public void maximizeWindow() throws InterruptedException{
			log.info("-----------Maximizing the Window---------");
			driver.manage().window().maximize();
		}

		
		public void mouseHover(String vElement, String vdata, String vType){
			log.info("----------MouseHover-------------");
			Actions act= new Actions(driver);
			WebElement we= elementTyp(vElement,vType);
			act.moveToElement(we).click().build().perform();
		}

		public void typTxt(String vElement, String vdata, String fElementType){
			log.info("-----------typing the text---------");
			if(vElement != null && !vElement.trim().equals("-")){
			WebElement we= elementTyp(vElement, fElementType);
			we.clear();
			we.sendKeys(vdata);
			}
		}

		
		public void clicklnk(String vElement, String fElementType){
			log.info("-----------Clicking the Link---------"+vElement);
			log.info("The element type is:"+ fElementType);
			if(vElement != null && !vElement.trim().equals("-")){
				WebElement element= elementTyp(vElement, fElementType);
				element.click();
			}

		}	

		public void wtTime(long fdata){
			log.info("-----------Introducing wait---------");{
				driver.manage().timeouts().implicitlyWait(fdata, TimeUnit.SECONDS);
			}
		}


		public void verifytxt(String vElement, String fdata, String vType){  
			log.info("-----------Verifying--------------------");
			WebElement we=  elementTyp(vElement, vType);
			String actual=		we.getText();
			log.info("Actual text is:"+ actual);
			log.info("Expected text is:"+ fdata);

			log.info(actual);
			Assert.assertEquals(actual, fdata);
		}

		
		public void verifyPageTitle(String fdata){
			log.info("-----------Verifying Page title--------------------");
			String actual=driver.getTitle();
			log.info("---");
			Assert.assertEquals(actual, fdata);
		}
	}	



	
