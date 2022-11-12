package com.testng.practice;

import java.util.concurrent.TimeUnit;

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

public class ActionsEx3 {
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

	@Test(groups = {"Smoke"})
	public void actionEx3() {


		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Drag And Drop Operation
		driver.switchTo().frame(0);
		Actions a =new Actions(driver);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		
		a.dragAndDrop(source,target).build().perform();
		driver.switchTo().defaultContent();
		
		
		//Click on Slider
		driver.findElement(By.xpath("//a[text()='Slider']")).click();
		driver.switchTo().frame(0);
		WebElement sliderSource = driver.findElement(By.xpath("//div[@id='slider']/span"));
		
		a.dragAndDropBy(sliderSource, 200, 0).build().perform();
		driver.switchTo().defaultContent();
	}

}
