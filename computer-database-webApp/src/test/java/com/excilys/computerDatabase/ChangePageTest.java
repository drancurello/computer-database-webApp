package com.excilys.computerDatabase;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ChangePageTest {
  private static WebDriver driver;
  private static String baseUrl;
  private static StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass
  public static void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get(baseUrl + "/computer-database-webApp/index?page=1&nbComputersPage=50#");
  }

  @Test
  public void testChangePage() throws Exception {
    driver.findElement(By.linkText("11")).click();
    driver.findElement(By.linkText("1")).click();
    driver.findElement(By.cssSelector("a > span")).click();
    driver.findElement(By.linkText("»")).click();
    driver.findElement(By.linkText("»")).click();
    driver.findElement(By.linkText("12")).click();
    driver.findElement(By.linkText("»")).click();
    driver.findElement(By.linkText("1")).click();
  }
  
  @Test
  public void indexToAddTest() throws Exception {
	    driver.findElement(By.id("addComputer")).click();
	    driver.findElement(By.linkText("Cancel")).click();
  }
  
  @Test
  public void indexToUpdate() throws Exception {

	    driver.findElement(By.linkText("MacBook Pro 15.4 inch")).click();
	    driver.findElement(By.linkText("Cancel")).click();
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
