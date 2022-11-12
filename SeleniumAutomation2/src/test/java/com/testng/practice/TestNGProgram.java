package com.testng.practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGProgram {
	
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
	
	@Test(priority = 2,groups = {"SanityTest"})
	public void FramesEx() {

		driver.get("https://the-internet.herokuapp.com/iframe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Switch to the frame id,name, webelement
		driver.switchTo().frame("mce_0_ifr");
		
		String text = driver.findElement(By.xpath("//body[@id='tinymce']/p")).getText();
		System.out.println("Iframe text is "+ text);
		
		//To come out from all the frames
		driver.switchTo().defaultContent();
		
		Assert.assertEquals(text, "Your content goes here.");
		

	}
	
	
	@Test(priority=4,dependsOnMethods = {"FramesEx"},groups = {"RegressionTest"})
	public void PositionEx() {


		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("-------------Postion Details------------");
		System.out.println(driver.manage().window().getPosition());
		System.out.println(driver.manage().window().getPosition().getX());
		System.out.println(driver.manage().window().getPosition().getY());
		
		System.out.println("-------------Size Details------------");
		System.out.println(driver.manage().window().getSize());
		System.out.println(driver.manage().window().getSize().getWidth());
		System.out.println(driver.manage().window().getSize().getHeight());
		
		//Set browser into other position
		Point p = new Point(500,500);
		driver.manage().window().setPosition(p);
		
		//Set size 
		Dimension d = new Dimension(300,500);
		driver.manage().window().setSize(d);

	}

}
