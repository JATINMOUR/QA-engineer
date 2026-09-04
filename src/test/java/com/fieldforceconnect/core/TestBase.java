package com.fieldforceconnect.core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class TestBase {

protected WebDriver driver;

protected String baseUrl =
        System.getProperty("baseUrl", "https://test.fieldforceconnect.com");


@BeforeMethod
public void startBrowser() {

    String browser =
            System.getProperty("browser", "chrome");

    boolean headless =
            Boolean.parseBoolean(
                    System.getProperty("headless", "false")
            );


    if (browser.equalsIgnoreCase("chrome")) {

        ChromeOptions options =
                new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

    }

    else if (browser.equalsIgnoreCase("edge")) {

        EdgeOptions options =
                new EdgeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--start-maximized");

        driver = new EdgeDriver(options);

    }

    else {

        throw new IllegalArgumentException(
                "Unsupported browser: " + browser
        );

    }


    // Keep implicit waits disabled. Mixing them with explicit waits makes
    // Selenium timeouts unpredictable.
    driver.manage().timeouts().implicitlyWait(Duration.ZERO);

    driver.manage()
            .timeouts()
            .pageLoadTimeout(Duration.ofSeconds(30));

    driver.manage()
            .window()
            .maximize();

}


@AfterMethod(alwaysRun = true)
public void stopBrowser() {

    if (driver != null) {

        long pauseAfterTestMs = Long.parseLong(
                System.getProperty("pauseAfterTestMs", "0")
        );

        if (pauseAfterTestMs > 0) {
            try {
                Thread.sleep(pauseAfterTestMs);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        driver.quit();

        driver = null;

    }

}


}
