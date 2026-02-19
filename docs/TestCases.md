# API Test Suite: Health Checks

## 1. System Vital Signs (Smoke)
| ID     | Test Case                 | Method | Endpoint | Expected Result           | Status            |
|:-------|:--------------------------|:-------|:---------|:--------------------------|:------------------|
| API-01 | Verify API System Health  | GET    | /ping    | Status code 201 (Created) | **Automated ✅** |

---
### Test Details: API-01
* **Objective:** Ensure the Restful-Booker environment is up and responding to requests.
* **Note:** Per the official API documentation, this endpoint returns `201 Created` rather than the standard `200 OK` to signal the service is live.