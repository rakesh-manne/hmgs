package com.hmgs.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmergencyRegistration {
	
public WebDriver driver;
	
	@BeforeClass
	public void setUp() throws IOException {
		driver = new FirefoxDriver();
		driver.get("http://selenium4testing.com/hms/");
		driver.manage().window().maximize();
		  
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("submit")).click();
	}
	
	@Test
	public void emergencyRegistration() throws FileNotFoundException, IOException {
		
		Properties properties = new Properties(); 
		properties.load(new FileInputStream(new File("D:\\workspace\\hms\\UI Map\\PermanentRegistration.properities")));
		
		driver.findElement(By.xpath(properties.getProperty("Expand").trim())).click();
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/ul/li[1]/ul/li[2]/a")).click();
	    Select selectPatient = new Select(driver.findElement(By.name(properties.getProperty("Patient_Category").trim())));
	    selectPatient.selectByVisibleText("Insurance");
	    
	    Select selectTitle = new Select(driver.findElement(By.name("TITLE")));
	    selectTitle.selectByVisibleText("Mr.");
	    
	    //entering the text in textbox
	    driver.findElement(By.name("PNT_NAME")).sendKeys("John Williams");
	    driver.findElement(By.name("MIDDLE_NAME")).sendKeys("wave");
	    driver.findElement(By.name("LAST_NAME")).sendKeys("Mine");
	    
	    //entering the date in datebox
	    driver.findElement(By.name("DOB")).sendKeys("10-03-2015");
	    driver.findElement(By.name("AGE")).sendKeys("30");
	    Select selectGender = new Select(driver.findElement(By.name("SEX")));
	    selectGender.selectByVisibleText("Male");
	    
	    Select selectMrtlstatus = new Select(driver.findElement(By.name("MTRL_STATUS")));
	    selectMrtlstatus.selectByVisibleText("Married");
	    
	    //select the text from dropdown using selectByIndex
	    Select selectReligion = new Select(driver.findElement(By.name("RELIGION")));
	    selectReligion.selectByIndex(3);
	    
	    Select selectPlanguage = new Select(driver.findElement(By.name("PLANGUAGE")));
	    selectPlanguage.selectByVisibleText("English");
	    
	    driver.findElement(By.name("ADDRESS1")).sendKeys("James Street, Rd No-1");
	    driver.findElement(By.name("MOBILE_NO")).sendKeys("0987654321");
	    driver.findElement(By.name("EMAIL_ID")).sendKeys("james@gmail.com");
	    
	    Select selectCountry = new Select(driver.findElement(By.name("COUNTRY_CODE")));
	    selectCountry.selectByVisibleText("America");
	    
	    Select selectRelation = new Select(driver.findElement(By.name("RELATION")));
	    selectRelation.selectByVisibleText("Brother");
	    
	    Select selectIdentity = new Select(driver.findElement(By.name("PAT_IDENTITY")));
	    selectIdentity.selectByVisibleText("Voter ID");
	    
	    driver.findElement(By.name("PAT_IDENTITY_PROOF")).sendKeys("1243657869808");
	    
	    Select selectNationality = new Select(driver.findElement(By.name("NATIONALITY")));
	    selectNationality.selectByVisibleText("Indian");
	    
	    Select selectVip = new Select(driver.findElement(By.name("IS_MLC")));
	    selectVip.selectByVisibleText("No");
	    
	    Select selectEducation = new Select(driver.findElement(By.name("EDUCATION")));
	    selectEducation.selectByVisibleText("B.Tech");
	    
	    Select selectOccupation = new Select(driver.findElement(By.name("OCCUPATION")));
	    selectOccupation.selectByVisibleText("Employee");
	    
	    Select selectBgroup = new Select(driver.findElement(By.name("BLOOD_GRP_CODE")));
	    selectBgroup.selectByVisibleText("AB+");
	    
	    Select selectCitizenship = new Select(driver.findElement(By.name("CITIZENSHIP")));
	    selectCitizenship.selectByVisibleText("American");
	    
	    Select selectSc = new Select(driver.findElement(By.name("SC_PROOF")));
	    selectSc.selectByVisibleText("No");
	    
	    driver.findElement(By.name("ADDRESS2")).sendKeys("Wine Street, Rd No-11");
	    driver.findElement(By.name("ZIP")).sendKeys("346534");
	    
	    //Upload the photo
	    WebElement upload = driver.findElement(By.name("image"));
	    upload.sendKeys("D:\\workspace\\hms\\Test Data\\screenshot.jpg");
	    
	    driver.findElement(By.name("submit")).click();
	    
	    Alert alert = driver.switchTo().alert();
	    alert.accept();
	}
	
	@AfterClass
    public void tearDown(){
  	  driver.quit();
    }

}
