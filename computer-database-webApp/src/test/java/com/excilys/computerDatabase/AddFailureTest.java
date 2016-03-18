package com.excilys.computerDatabase;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddFailureTest {
  private static WebDriver driver;
  private static String baseUrl;
  private static StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass
  public static void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get(baseUrl + "/computer-database-webApp/index");
  }

  @Test
  public void testNameFailure() throws Exception {
    driver.findElement(By.id("addComputer")).click();
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("a");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
  }
  
  @Test
  public void testIntroducedFailure() throws Exception {
	    driver.findElement(By.id("computerName")).clear();
	    driver.findElement(By.id("computerName")).sendKeys("iphone 6");
	    driver.findElement(By.id("introduced")).clear();
	    driver.findElement(By.id("introduced")).sendKeys("vfvf");
	    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	    driver.findElement(By.id("computerName")).clear();
	    driver.findElement(By.id("computerName")).sendKeys("iphone 6");
	    driver.findElement(By.id("introduced")).clear();
	    driver.findElement(By.id("introduced")).sendKeys("2015");
	    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	    driver.findElement(By.id("computerName")).clear();
	    driver.findElement(By.id("computerName")).sendKeys("iphone 6");
	    driver.findElement(By.id("introduced")).clear();
	    driver.findElement(By.id("introduced")).sendKeys("1970-01-01");
	    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
  }
  
  @Test
  public void testDiscontinuedFailure() throws Exception {
	    driver.findElement(By.id("computerName")).clear();
	    driver.findElement(By.id("computerName")).sendKeys("iphone 6");
	    driver.findElement(By.id("discontinued")).clear();
	    driver.findElement(By.id("discontinued")).sendKeys("vfvf");
	    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	    driver.findElement(By.id("computerName")).clear();
	    driver.findElement(By.id("computerName")).sendKeys("iphone 6");
	    driver.findElement(By.id("discontinued")).clear();
	    driver.findElement(By.id("discontinued")).sendKeys("2015");
	    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	    driver.findElement(By.id("computerName")).clear();
	    driver.findElement(By.id("computerName")).sendKeys("iphone 6");
	    driver.findElement(By.id("discontinued")).clear();
	    driver.findElement(By.id("discontinued")).sendKeys("1970-01-01");
	    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
  }
  
  @Test
  public void testDateFailure() throws Exception {
	    driver.findElement(By.id("computerName")).clear();
	    driver.findElement(By.id("computerName")).sendKeys("iphone 6");
	    driver.findElement(By.id("introduced")).clear();
	    driver.findElement(By.id("introduced")).sendKeys("2015-10-23");
	    driver.findElement(By.id("discontinued")).clear();
	    driver.findElement(By.id("discontinued")).sendKeys("2014-10-15");
	    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
  }

  @AfterClass
  public static void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
