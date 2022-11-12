package com.testng.practice;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsEx2 {
	WebDriver driver;
	Logger log = (Logger) LogManager.getLogger(ActionsEx2.class);
	
	
	@BeforeMethod
	@Parameters(value= {"Browser"})
	public void launchBrowser(String brName) {
		// TODO Auto-generated method stub
		// Actions class is used for keyboard and mouse operations
		
		if(brName.equalsIgnoreCase("CHROME")) {
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
		}
		else if(brName.equalsIgnoreCase("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(brName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
			
		log.info("Browser is launched successfully");
		
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
		log.info("Browser is closed successfully");
	}
	
	@Test(groups = {"RegressionTest"})
	public void actionEx1() {
		driver.get("https://the-internet.herokuapp.com/jqueryui/menu");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Actions a =new Actions(driver);
		WebElement enabled = driver.findElement(By.xpath("//li[@id='ui-id-3']/a"));
		WebElement downloads = driver.findElement(By.xpath("//li[@id='ui-id-4']/a"));
		WebElement excel = driver.findElement(By.xpath("//li[@id='ui-id-7']/a"));
		
		a.moveToElement(enabled).pause(1000).moveToElement(downloads).pause(1000).moveToElement(excel).click().build().perform();
		
		log.info("LOG:: Test is passed");
	}

}
