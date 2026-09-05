# FieldForce Connect QA assessment

Maven + Selenium + TestNG UI automation, a Postman collection, and manual test design for `https://test.fieldforceconnect.com`.

## Prerequisites and run

Install JDK 17+ and Maven, then provide a test account without committing its password:

```powershell
$env:FFC_EMAIL = "your-email@example.com"
$env:FFC_PASSWORD = "your-password"
mvn test -Demail=$env:FFC_EMAIL -Dpassword=$env:FFC_PASSWORD -DbaseUrl=https://test.fieldforceconnect.com
```

