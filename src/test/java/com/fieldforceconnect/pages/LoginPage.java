package com.fieldforceconnect.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {


private final WebDriver driver;

private final WebDriverWait wait;


private final By identifier =
        By.xpath(
                "//input[@placeholder='Enter Email or Mobile Number']"
        );


private final By password =
        By.cssSelector("input[name='password']");


private final By signIn =
        By.cssSelector("button[type='submit']");


public LoginPage(WebDriver driver) {

    this.driver = driver;

    this.wait =
            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(15)
            );

}


public void open() {

    driver.get(
            System.getProperty(
                    "baseUrl",
                    "https://test.fieldforceconnect.com"
            ) + "/auth/login"
    );

}


public void login(
        String email,
        String secret) {


    WebElement emailField =
            wait.until(
                    ExpectedConditions
                            .visibilityOfElementLocated(identifier)
            );


    emailField.clear();

    emailField.sendKeys(email);


    WebElement passwordField =
            wait.until(
                    ExpectedConditions
                            .visibilityOfElementLocated(password)
            );


    passwordField.clear();

    passwordField.sendKeys(secret);

    // The React form disables submit until its client-side validation has
    // accepted both inputs. Report the validation state without exposing the
    // password value when a configured credential cannot be submitted.
    WebElement signInButton;

    try {
        signInButton = wait.until(
                ExpectedConditions.elementToBeClickable(signIn)
        );
    } catch (TimeoutException exception) {
        String formText = driver.findElement(By.tagName("body")).getText();

        throw new IllegalStateException(
                "Login form did not enable Sign In. "
                        + "The supplied password has "
                        + secret.length()
                        + " characters. The current application UI allows "
                        + "passwords up to 12 characters. Form feedback: "
                        + formText.replaceAll("\\s+", " "),
                exception
        );
    }


    signInButton.click();

}


public String feedback() {

    return wait.until(driver -> {

        String text =
                driver.findElement(
                        By.tagName("body")
                ).getText();


        if (text.toLowerCase().contains("invalid")
                || text.toLowerCase().contains("incorrect")
                || text.toLowerCase().contains("error")
                || text.toLowerCase().contains("dashboard")) {

            return text;

        }


        return null;

    });

}


public boolean isLoginSuccessful() {

    return wait.until(driver ->
            !driver.getCurrentUrl()
                    .contains("/auth/login")
    );

}


public String validationMessage() {

    return driver.findElement(By.tagName("body")).getText();

}

}
