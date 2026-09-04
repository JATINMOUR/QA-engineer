package com.fieldforceconnect.tests;

import com.fieldforceconnect.core.TestBase;
import com.fieldforceconnect.pages.LoginPage;

import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;

public abstract class AuthenticatedTestBase extends TestBase {

@BeforeMethod(alwaysRun = true)
public void login() {

    String email = System.getProperty(
            "email",
            System.getenv("FFC_EMAIL")
    );

    String password = System.getProperty(
            "password",
            System.getenv("FFC_PASSWORD")
    );

    if (email == null
            || password == null
            || email.isBlank()
            || password.isBlank()) {

        throw new SkipException(
                "Credentials are required for authenticated scenarios."
        );
    }

    LoginPage page = new LoginPage(driver);

    // Open Login Page
    page.open();

    // Login
    page.login(email, password);
}


}
        