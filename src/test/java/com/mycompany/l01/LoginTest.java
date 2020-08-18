package com.mycompany.l01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends FirstTest{

    @Test
    public void loginNegative() {
        //login field
        WebElement userLoginName = driver.findElement(By.id("UserName"));
        userLoginName.sendKeys("vishkov");
        //pw field
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='Password']"));
        userPasswordField.sendKeys("InvalidPassword");
        //click to login button
        driver.findElement(By.id("logonButton")).click();

        assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-error']"))
                        .getText().contains("Ошибка"));
    }

    @Test
    public void loginPositive() {
        WebElement userName = driver.findElement(By.id("UserName"));
        userName.clear();
        userName.sendKeys("vishkov");

        WebElement password = driver.findElement(By.id("Password"));
        password.clear();
        password.sendKeys("correctPassword");

        driver.findElement(By.id("logonButton")).click();
        assertTrue(isElementPresent(By.className("tt-input")));
        assertEquals(driver.findElements(By.className("field-validation-error")).size(), 0);
    }

}
