package com.excilys.computerDatabase;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DeleteTest {
	  private WebDriver driver;
	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  } 

	  @Test
	  public void testAdd() throws Exception {
		  driver.get(baseUrl + "/computer-database-webApp/index?page=12&nbComputersPage=50");
		    driver.findElement(By.id("editComputer")).click();
		    driver.findElement(By.xpath("(//input[@name='cb'])[26]")).click();
		    driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
}
