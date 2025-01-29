package com.rad.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.sql.rowset.BaseRowSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests {

    private WebDriver driver;
    private Logger logger;


    @BeforeMethod(alwaysRun = true)
    @Parameters("Browser")
    public void setUp(@Optional("chrome") String Browser){
        logger = Logger.getLogger((LoginTests.class.getName()));
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
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod(alwaysRun = true)
    public void CleanUP(){
        driver.quit();
        logger.info("Browser closed");
    }

    @Test(groups = {"positive","regression"})
    public void testLoginFunc(){

     logger.info("Starting testLoginFunc()");

        WebElement usernameInput= driver.findElement(By.xpath("//*[@id=\"username\"]"));
        usernameInput.sendKeys("student");

        WebElement passwdInput= driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwdInput.sendKeys("Password123");

        WebElement submitBtn= driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submitBtn.click();

        try {
            Thread.sleep(2000);          //this pauses aftr submit to load url or something not neded for now but just an example the actaull wait will see further
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //verification
        logger.info("Verifying testLoginFunc()");
        String expectedUrl= "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);

        String expcetedMsg="Congratulations student. You successfully logged in!";
        String pageSource= driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expcetedMsg));

        WebElement logoutBtn= driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logoutBtn.isDisplayed());


    }

   @Test(groups = {"negavtive","regression"})
    public void WrongUserName(){

       logger.info("Starting WrongUserName()");

//        Type username incorrectUser into Username field

        WebElement WrongUsername= driver.findElement(By.xpath("//*[@id=\"username\"]"));
        WrongUsername.sendKeys("WrongUsername");

//        Type password Password123 into Password field
       WebElement crtPasswd= driver.findElement(By.xpath("//*[@id=\"password\"]"));
       crtPasswd.sendKeys("Password123");

//        Push Submit button

      WebElement submitbtn= driver.findElement(By.xpath("//*[@id=\"submit\"]"));
      submitbtn.click();

//        Verify error message is displayed
        //        Verify error message text is Your username is invalid!
//      String expectedmsg= "Your username is invalid!";
//      String pagesource= driver.getPageSource();
//        Assert.assertTrue(pagesource.contains(expectedmsg));
        //or
       logger.info("Verifying WrongUserName()");
        try {
            Thread.sleep(5000);          //this pauses aftr submit to load url or something not neded for now but just an example the actaull wait will see further
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement errorMsg= driver.findElement(By.id("error"));
        Assert.assertTrue(errorMsg.isDisplayed());

        String expectedmsg= "Your username is invalid!";
        String actualMsg= errorMsg.getText();
        Assert.assertEquals(actualMsg,expectedmsg);




    }

    //same for passwd
    @Test(groups = {"negavtive","regression"})
    public void WrongPasswd(){

        logger.info("Starting WrongPasswd()");

        WebElement crtUsername= driver.findElement(By.xpath("//*[@id=\"username\"]"));
        crtUsername.sendKeys("student");

        WebElement wrongPasswd= driver.findElement(By.xpath("//*[@id=\"password\"]"));
        wrongPasswd.sendKeys("Wrong");

        WebElement submitbtn= driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submitbtn.click();

        logger.info("Verifying WrongPasswd()");
        String expectedmsg= "Your password is invalid!";
        String pagesource= driver.getPageSource();
        Assert.assertTrue(pagesource.contains(expectedmsg));




    }



}
