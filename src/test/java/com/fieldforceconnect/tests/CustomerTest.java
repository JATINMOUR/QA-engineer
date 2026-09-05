package com.fieldforceconnect.tests;

import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomerTest extends AuthenticatedTestBase {

    @DataProvider(name = "customers")
    public Object[][] customers() {

        String uniqueName =
                "QA Customer " + Instant.now().toEpochMilli();

        return new Object[][]{
                {
                        uniqueName,
                        "9876543210",
                        "qa.customer@example.com"
                }
        };
    }


    @Test(dataProvider = "customers")
    public void createsCustomer(
            String name,
            String phone,
            String email) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));


         
        // STEP 1: Wait for Dashboard after Login
         

        wait.until(
                ExpectedConditions.urlContains("/dashboard")
        );


         
        // STEP 2: Click My Customers Toggle
         

        By myCustomersToggle = By.xpath(
                "//span[normalize-space()='My Customers']"
        );

        WebElement toggle = wait.until(
                ExpectedConditions.elementToBeClickable(
                        myCustomersToggle
                )
        );

        toggle.click();


         
        // STEP 3: Click My Customer submenu
        //
        // Actual HTML:
        // <a href="/customers">
        //     <span>My Customer</span>
        // </a>
         

        By myCustomerLink =
                By.cssSelector("a[href='/customers']");

        WebElement customerMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        myCustomerLink
                )
        );

        customerMenu.click();


         
        // STEP 4: Wait for Customer Page
         

        wait.until(
                ExpectedConditions.urlContains("/customers")
        );


         
        // STEP 5: Click Manage Button
         

        By manageButton = By.xpath(
                "//button[contains(normalize-space(.), 'Manage')]"
        );

        WebElement manage = wait.until(
                ExpectedConditions.elementToBeClickable(
                        manageButton
                )
        );

        manage.click();


         
        // STEP 6: Click New Customer
         

        By newCustomer = By.xpath(
                "//*[normalize-space()='New Customer']"
        );

        WebElement newCustomerButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        newCustomer
                )
        );

        newCustomerButton.click();


         
        // STEP 7: Enter Customer Name
         

        By customerName = By.xpath(
                "//input[" +
                        "contains(" +
                        "translate(@placeholder," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                        "'abcdefghijklmnopqrstuvwxyz')," +
                        "'name')" +
                        " or @name='LeadName'" +
                        "]"
        );

        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        customerName
                )
        );

        nameField.clear();
        nameField.sendKeys(name);


        

         
        // STEP 8: Enter Mobile Number
         

        By mobileNumber = By.xpath(
                "//input[" +
                        "contains(" +
                        "translate(@placeholder," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                        "'abcdefghijklmnopqrstuvwxyz')," +
                        "'mobile')" +
                        " or contains(" +
                        "translate(@placeholder," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                        "'abcdefghijklmnopqrstuvwxyz')," +
                        "'phone')" +
                        " or @name='MobileNo'" +
                        "]"
        );

        WebElement mobileField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        mobileNumber
                )
        );

        mobileField.clear();
        mobileField.sendKeys(phone);


         
        // STEP 9: Enter Email
         

        By emailFieldLocator = By.xpath(
                "//input[" +
                        "@type='Email'" +
                        " or contains(" +
                        "translate(@placeholder," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                        "'abcdefghijklmnopqrstuvwxyz')," +
                        "'email')" +
                        " or @name='Email'" +
                        "]"
        );

        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        emailFieldLocator
                )
        );

        emailField.clear();
        emailField.sendKeys(email);


         
        // STEP 10: Click Save / Submit / Create
         

        By saveButton = By.xpath(
                "//button[" +
                        "normalize-space()='Save'" +
        
                        "]"
        );

        WebElement save = wait.until(
                ExpectedConditions.elementToBeClickable(
                        saveButton
                )
        );

        save.click();


         
        // STEP 11: Validate Customer Creation
         

        Boolean customerCreated = wait.until(d -> {

            String pageText = d.findElement(
                    By.tagName("body")
            ).getText().toLowerCase();

            return pageText.contains(name.toLowerCase())
                    || pageText.contains("success")
                    || pageText.contains("successfully");

        });


        Assert.assertTrue(
                customerCreated,
                "Customer was not created successfully."
        );


        System.out.println(
                "Customer created successfully: " + name
        );
    }
}