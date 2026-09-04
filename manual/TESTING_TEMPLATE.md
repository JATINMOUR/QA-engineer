# FieldForce Connect — Manual Testing Template

**Application:** https://test.fieldforceconnect.com/  
**Scope:** Sign Up, Forgot Password, Sign In With OTP, Login  
**Execution note:** Record browser/device, build, test data, actual result, status and evidence against every case below.

## Test cases

| ID | Module | Scenario / steps | Expected result |
|---|---|---|---|
| SU-01 | Sign Up | Open Sign up and submit all mandatory valid fields | Account is created; confirmation and next-login path are clear. |
| SU-02 | Sign Up | Submit empty form | Each mandatory field shows an inline required validation; no account is created. |
| SU-03 | Sign Up | Enter malformed email / short password / nonmatching confirm password | Field-level format, strength and mismatch errors appear. |
| SU-04 | Sign Up | Register an existing email/mobile | Duplicate is rejected without revealing unnecessary account details. |
| SU-05 | Sign Up | Enter leading/trailing spaces, mixed case email, special characters in name | Input is trimmed/normalised where appropriate; safe characters work; unsafe input is rejected/encoded. |
| FP-01 | Forgot Password | Submit registered email/mobile | Neutral success message and reset/OTP delivery are triggered. |
| FP-02 | Forgot Password | Submit invalid or unregistered identifier | Format error or neutral response; no reset occurs. |
| FP-03 | Forgot Password | Use expired/incorrect OTP or reset link | Clear error and option to resend; password is unchanged. |
| FP-04 | Forgot Password | Set weak / mismatching password | Password policy and confirmation errors show; save remains blocked. |
| OTP-01 | Sign In With OTP | Request OTP for registered identifier and enter valid current OTP | User reaches dashboard. |
| OTP-02 | Sign In With OTP | Submit blank/invalid identifier or blank OTP | Inline validation; verification/request blocked. |
| OTP-03 | Sign In With OTP | Enter wrong, expired, reused OTP; exceed retry limit | Verification fails safely; retries/rate limiting/resend behaviour is clear. |
| LG-01 | Login | Valid registered email/mobile and password | Dashboard opens, session is established and no password is exposed. |
| LG-02 | Login | Invalid email/mobile or password | Generic error is shown; session is not established. |
| LG-03 | Login | Submit blank fields; then malformed email/mobile | Required and format validations show; Sign In stays disabled until valid input. |
| LG-04 | Login | Navigate to Forgot Password / Sign Up / OTP links | Correct routes load and browser Back preserves expected safe state. |
| LG-05 | Login | Attempt repeated failures, refresh, logout, then Back | Brute-force protections work; logout invalidates protected access. |

## Field validation checklist

| Field | Validation |
|---|---|
| Email ID / Mobile No | Required; trim whitespace; accept a valid email or configured mobile format only; reject letters in phone, invalid domain, too-short/too-long values; avoid account enumeration. |
| Password | Required; masked; defined min/max length and strength; reject whitespace-only input; support paste; never echo in URL/logs/errors. |
| Confirm password | Required when present; exact match after documented whitespace rules. |
| Name | Required if marked; letters/spaces and agreed punctuation only; trim; min/max length; reject script/HTML payloads. |
| OTP | Required after request; digits only and exact configured length; expire after configured TTL; one-time use; rate-limit resend/attempts. |
| Submit buttons | Disabled until mandatory client validation passes; server repeats every validation; prevent duplicate clicks/submissions. |

## Defect log / observations

| ID | Severity | Observation | Reproduction | Status |
|---|---|---|---|---|
| OBS-01 | Low | Login screen shows Sign In and OTP buttons disabled before the required fields are populated. This is expected behaviour to retain. | Open `/auth/login` with blank fields. | Observed, not a defect. |
| BUG-TBD-01 | — | No authenticated account was supplied, so reset delivery, OTP verification, punch-in and customer persistence must be executed with a candidate-created test account. | Execute relevant cases after sign-up. | Needs execution. |

Do not report an unexecuted assumption as a bug. Attach screenshots/network evidence and include expected vs actual result for each confirmed defect.
