package com.rad.tests.expections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Expections {

    private WebDriver driver;
    private Logger logger;


    @BeforeMethod(alwaysRun = true)
    @Parameters("Browser")
    public void setUp(@Optional("chrome") String Browser){
        logger = Logger.getLogger((Expections.class.getName()));
        logger.setLevel(Level.INFO);
        logger.info("Running the tests in"+ Browser);
        switch (Browser.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
            default:
                driver = new ChromeDriver();
        }

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // this is the way to initialize implicit wait

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void CleanUP(){
        driver.quit();
        logger.info("Browser closed");
    }


  public void NoSuchElementException(){

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); //explicit wait

        WebElement addbtn= driver.findElement(By.xpath("//*[@id=\"add_btn\"]"));
        addbtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"row2\"]/input")));
        WebElement row2input= driver.findElement(By.xpath("//div[@id=\"row2\"]/input"));
        row2input.sendKeys("SedheMaut");

 
        row2input.sendKeys("SedheMaut");
        WebElement saveBtn= driver.findElement(By.xpath("/html/body/div[1]/div/section/section/div/div[3]/div/button[1]"));
        saveBtn.click();
  }

  @Test
  public void   StaleElementReferenceException_tc4(){
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); //explicit wait

      WebElement addbtn= driver.findElement(By.xpath("//*[@id=\"add_btn\"]"));
      addbtn.click();

     Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))));

//      Boolean flag=  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions")));
//
//     return flag;
  }



}
