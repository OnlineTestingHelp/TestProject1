package com.testng.practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(ListenerLogic.class)
public class ActionsEx4 {
	
	WebDriver driver;
	
	
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
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@Test(groups = {"RegressionTest"})
	public void actionEx4() {

		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//
		WebElement ele = driver.findElement(By.name("q"));
		ele.sendKeys("indian historical places");
		Actions a =new Actions(driver);
		a.sendKeys(Keys.DOWN,Keys.DOWN,Keys.DOWN,Keys.ENTER).build().perform();
		
		Assert.assertTrue(true);
	}

}
