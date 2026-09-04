package com.fieldforceconnect.tests;

import com.fieldforceconnect.core.TestBase;
import com.fieldforceconnect.pages.LoginPage;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


@DataProvider(name = "invalidCredentials")
public Object[][] invalidCredentials() {

    return new Object[][]{

            {
                    "no.user@example.com",
                    "WrongPass12"
            },


    };

}


@Test(dataProvider = "invalidCredentials")
public void rejectsInvalidLogin(
        String email,
        String password) {


    LoginPage page =
            new LoginPage(driver);


    page.open();


    page.login(email, password);


    String feedback =
            page.feedback()
                    .toLowerCase();


    System.out.println(
            "Invalid Login Feedback: "
                    + feedback
    );


    Assert.assertTrue(

            feedback.contains("invalid")
                    || feedback.contains("incorrect")
                    || feedback.contains("error"),

            "Invalid credentials should display an error message."

    );

}


@Test
public void acceptsValidLogin() {


    String email =
            System.getProperty(
                    "email",
                    System.getenv("FFC_EMAIL")
            );


    String password =
            System.getProperty(
                    "password",
                    System.getenv("FFC_PASSWORD")
            );


    if (email == null
            || password == null
            || email.isBlank()
            || password.isBlank()) {


        throw new SkipException(
                "FFC_EMAIL or FFC_PASSWORD is not set."
        );

    }


    LoginPage page =
            new LoginPage(driver);


    page.open();


    page.login(email, password);


    boolean loginSuccessful =
            page.isLoginSuccessful();


    Assert.assertTrue(

            loginSuccessful,

            "Login failed. User is still on login page."

    );


    System.out.println(
            "Valid Login Successful!"

    );

}


}
