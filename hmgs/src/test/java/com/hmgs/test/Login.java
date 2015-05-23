package com.hmgs.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import atu.testng.reports.logging.LogAs;
import atu.testng.reports.ATUReports;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

public class Login {
	
  {
    System.setProperty("atu.reporter.config", "D:\\AUT Reporter");
  }
	
  public WebDriver driver;
  
  
  @BeforeClass
  public void setUp() throws IOException {
	  driver = new FirefoxDriver();
	  driver.get("http://selenium4testing.com/hms/");
	  driver.manage().window().maximize();
	  
	  // ATU Reports
	  ATUReports.setWebDriver(driver);
	  ATUReports.indexPageDescription = "Test Project";
	  
	 // this code is for creating logs
//	  Logger logger = Logger.getLogger("");
//	  FileHandler fh;
//	  fh = new FileHandler("D:\\workspace\\hmgs\\Log\\logs.txt");
//	  logger.addHandler(fh);
//	  logger.setLevel(Level.ALL);
//	  SimpleFormatter formatter = new SimpleFormatter();
//	  fh.setFormatter(formatter);
	  
      //File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  //FileUtils.copyFile(screenshot, new File("D:/workspace/hmgs/Screenshots/screenshot.jpg"));
  }
  
  @Test
  public void Login() throws BiffException, IOException{
	  FileInputStream f = new FileInputStream("D:\\workspace\\hmgs\\Test Data\\login.xls");
      Workbook wb = Workbook.getWorkbook(f);
      Sheet sheet = wb.getSheet(0);
      
      for(int row=1; row < sheet.getRows(); row++) {
  	    String uname = sheet.getCell(0,row).getContents();
  		System.out.println("User Name is:" +uname);
  		String pwd= sheet.getCell(1, row).getContents();
  		System.out.println("Password is:" +pwd);
  		driver.findElement(By.name("username")).sendKeys(uname);
  		driver.findElement(By.name("password")).sendKeys(pwd);
  		driver.findElement(By.name("submit")).click();
  		driver.getPageSource().contains("Welcome, admin");
  		System.out.println("row is" +row);
  		driver.findElement(By.xpath("/html/body/div[2]/div/h3/a")).click();
      }
      
      driver.findElement(By.name("username")).sendKeys("admin");
	  driver.findElement(By.name("password")).sendKeys("admin");
	  driver.findElement(By.name("submit")).click();
  }
  
    //ATU Reports Method
	@Test
	public void testNewLogs() throws AWTException, IOException {

		ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		WebElement element = driver.findElement(By.xpath("/html/body/div/h1/a"));
		ATUReports.add("Warning Step", LogAs.WARNING,new CaptureScreen(element));
		ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
	}
  
  @AfterClass
  public void tearDown(){
	  driver.quit();
  }
}
