package com.fieldforceconnect.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PunchInTest extends AuthenticatedTestBase {

    @Test
    public void verifyToastMessageAfterPunchIn() {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );


        
        // STEP 1: Wait for Dashboard
         

        wait.until(
                ExpectedConditions.urlContains("/dashboard")
        );


         
        // STEP 2: Wait for Punched In card
         

        By punchInCard = By.xpath(
                "//h6[normalize-space()='Punched In']" +
                "/ancestor::div[contains(@class,'MuiPaper-root')][1]"
        );


        WebElement punchCard = wait.until(
                ExpectedConditions.elementToBeClickable(
                        punchInCard
                )
        );


         
        // STEP 3: Click Punch In card
         

        punchCard.click();


         
        // STEP 4: Wait for Toast / Popup
         

        By toastMessage = By.cssSelector(
                "[role='alert'], [role='status']"
        );


        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        toastMessage
                )
        );


        String actualMessage =
                toast.getText().trim();


        System.out.println(
                "Toast Message: " + actualMessage
        );


         
        // STEP 5: Validate Popup
         

        Assert.assertFalse(
                actualMessage.isEmpty(),
                "Toast/Popup message is empty."
        );


        System.out.println(
                "Punch In toast validated successfully!");
    }
}