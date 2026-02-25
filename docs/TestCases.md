# API Test Suite: Health Checks

## 1. System Vital Signs (Smoke)
| ID     | Test Case                 | Method | Endpoint | Expected Result           | Status            |
|:-------|:--------------------------|:-------|:---------|:--------------------------|:------------------|
| API-01 | Verify API System Health  | GET    | /ping    | Status code 201 (Created) | **Automated ✅** |
---

## 2. Booking Management
| ID     | Test Case                 | Method | Endpoint | Expected Result           | Status            |
|:-------|:--------------------------|:-------|:---------|:--------------------------|:------------------|
| API-02 | Get All Booking IDs       | GET    | /booking | Status code 200 (OK)      | **Automated ✅** |
---

### Test Details: API-01
* **Objective:** Ensure the Restful-Booker environment is up and responding to requests.
* **Note:** Per the official API documentation, this endpoint returns `201 Created` rather than the standard `200 OK` to signal the service is live.

### Test Details: API-02
* **Objective:** Verify that the system successfully returns a list of all currently active booking IDs.
* **Validation:** Response status must be `200 OK`. 
* Response body must be a JSON array containing `bookingid` objects.

## 2. Booking Management (Continued)

| ID     | Test Case            | Method | Endpoint      | Expected Result           | Status          |
|:-------|:---------------------|:-------|:--------------|:--------------------------|:----------------|
| API-03 | Get Booking By ID    | GET    | /booking/{id} | Status code 200 (OK)      | To Be Automated |

---

### Test Details: API-03
* **Objective:** Verify that the system can retrieve the full details of a specific booking record.
* **Prerequisite:** Requires a valid `bookingid`. The test dynamically fetches an ID from the list provided by **API-02** to ensure data availability.
* **Validation:** * Response status must be `200 OK`.
    * Response body must be a JSON object containing specific fields: `firstname`, `lastname`, `totalprice`, `depositpaid`, and a `bookingdates` nested object.
* **Logic:** This test validates the data integrity of a single record, ensuring all mandatory fields are present and of the correct data type (e.g., `totalprice` as a number).