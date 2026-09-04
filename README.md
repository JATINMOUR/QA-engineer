# FieldForce Connect QA assessment

Maven + Selenium + TestNG UI automation, a Postman collection, and manual test design for `https://test.fieldforceconnect.com`.

## Prerequisites and run

Install JDK 17+ and Maven, then provide a test account without committing its password:

```powershell
$env:FFC_EMAIL = "your-email@example.com"
$env:FFC_PASSWORD = "your-password"
mvn test -Demail=$env:FFC_EMAIL -Dpassword=$env:FFC_PASSWORD -DbaseUrl=https://test.fieldforceconnect.com
```

To keep each browser window visible temporarily while debugging, append `-DpauseAfterTestMs=15000` (15 seconds). Selenium closes the browser after each test by design.

`LoginTest` includes parameterized valid and invalid paths. The application's login form accepts passwords up to 12 characters, so all parameterized invalid credentials deliberately satisfy that UI rule and reach server-side validation. Authenticated punch-in and add-customer tests skip with a clear message if credentials are not supplied. Browser choice defaults to Chrome; override with `-Dbrowser=edge` or run headlessly with `-Dheadless=true`.

## Important setup notes

* `CustomerTest` uses visible labels/placeholders and includes a small locator fallback. After signing up, run it once and adjust locators in `CustomerTest` if this test environment uses different field labels.
* Import `postman/FieldForceConnect.postman_collection.json` and `postman/FieldForceConnect.postman_environment.json`. In Postman DevTools / Console, use the login request to discover and record the actual endpoint and bearer-token response path if they differ. No credential is committed.
* Manual test cases, validations, and observed/possible defects are in `manual/TESTING_TEMPLATE.md`.
