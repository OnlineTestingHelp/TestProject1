package com.testng.practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MultipleTests {
	
	@BeforeSuite
	public void bsuite() {
		System.out.println("Report initialization");
	}
	
	@AfterSuite
	public void aSuite() {
		System.out.println("Close Suite");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Test Level Prerequisite");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("Close Test Level Prerequisite");
	}
	
	
	@BeforeClass
	public void preConditions() {
		System.out.println("Excel connection");
		System.out.println("Database Connection");
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("Close Excel connection");
		System.out.println("Close Database Connection");
	}
	
	
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Prerequisite");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("Clear");
	}
	
	
	@Test(description = "This is first testNG",priority = 2,groups = {"Smoke"})
	public void cFirst() {
		System.out.println("First TestNG Program");
	}
	
	@Test(priority = -1,enabled = false,groups = {"Smoke"})
	public void bSecond() {
		System.out.println("Second TestNG Program");
	}
	
	@Test(priority = 1,groups = {"RegressionTest"})
	public void aThird() {
		System.out.println("Third TestNG Program");
	}
	
	@Test(priority = 3,groups = {"RegressionTest"})
	public void dFour() {
		System.out.println("Fourth TestNG Program");
	}

}
