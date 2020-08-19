package com.mycompany.l01;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FirstTest {
    WebDriver driver = null;
    String baseUrl = "https://intra.t-systems.ru";
    String expectedTitle = "Intra";
    String actualTitle = "";
    String current_env = "chrome";

    @BeforeTest
    public void initBrowser() {
        if (current_env.equals("firefox")) {
            String pathToGeckoDriver = Paths.get("C:\\webdrivers\\geckodriver\\geckodriver.exe").toAbsolutePath().toString();
            System.setProperty("webdriver.gecko.driver", pathToGeckoDriver);
            System.setProperty("webdriver.firefox.marionette", "false");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else if (current_env.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        else if (current_env.equals("opera")){
            System.setProperty("webdriver.opera.driver","C:\\webdrivers\\operadriver\\operadriver.exe");
            driver = new OperaDriver();
            driver.manage().window().maximize();
        }
        else
        {
            System.exit(0);
        }
        driver.get(baseUrl);
    }

    @Test(enabled = false)
    public void runBrowser() {
        driver.get(baseUrl);
        actualTitle = driver.getTitle();
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("test pass");
        }
        else {
            System.out.println("test failed");
            Assert.fail();
        }

    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }


    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
