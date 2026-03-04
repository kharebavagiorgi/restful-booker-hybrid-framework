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
| API-03 | Get Booking By ID    | GET    | /booking/{id} | Status code 200 (OK)      | **Automated ✅** |

---

### Test Details: API-03
* **Objective:** Verify that the system can retrieve the full details of a specific booking record.
* **Prerequisite:** Requires a valid `bookingid`. The test dynamically fetches an ID from the list provided by **API-02** to ensure data availability.
* **Validation:** * Response status must be `200 OK`.
    * Response body must be a JSON object containing specific fields: `firstname`, `lastname`, `totalprice`, `depositpaid`, and a `bookingdates` nested object.
* **Logic:** This test validates the data integrity of a single record, ensuring all mandatory fields are present and of the correct data type (e.g., `totalprice` as a number).


| ID     | Test Case             | Method | Endpoint | Expected Result           | Status              |
|:-------|:----------------------|:-------|:---------|:--------------------------|:--------------------|
| API-04 | Create Booking Record | POST   | /booking | Status code 200 (OK)      | **Automated**       |

---

### Test Details: API-03
* **Objective:** Verify that the system can successfully create a new booking record and return a unique identifier.
* **Prerequisite:** Requires a JSON body containing firstname, lastname, totalprice, depositpaid, bookingdates (nested object), and additionalneeds.
* **Validation:** * Response status must be `200 OK`.
  * Response body must contain a generated bookingid.
  * The returned booking object must contain all fields sent in the request.
* **Logic:** This test validates the data integrity of a single record, ensuring all mandatory fields are present and of the correct data type (e.g., `totalprice` as a number).


## Mobile Automation Test Cases

| ID     | Test Case             | Platform | Browser | Expected Result           | Status              |
|:-------|:----------------------|:---------|:--------|:--------------------------|:--------------------|
| MOB-01 | Verify Mobile Web Landing | Android (Pixel 8)         | Chrome  | "Book Now" link is visible and clickable      | **Automated**       |

### TC-M1: Verify Booking Site Responsiveness (Mobile Web)
**Objective:** Ensure the Restful-Booker website loads correctly on a mobile device.
1. **Launch** Chrome Browser on Pixel 8.
2. **Navigate** to `https://automationintesting.online/` (The UI version of API).
3. **Verify** the "Book Now" button is visible.
4. **Capture** a screenshot of the mobile landing page.
   **Expected Result:** The site scales correctly for mobile and the booking button is functional.

### UI Automation Test Suite: Contact Management
** 1. ** Contact Form Functionality

** ID ** ,Test Case,Layer,Element/Component,Expected Result,Status
1. UI-01,Successful Contact Submission,UI,Contact Form,Success message displayed, Automated ✅
2. UI-02,Submit with Invalid Email Format,UI,Email Input Field,Validation error (well-formed), Automated ✅
3. UI-03,Submit with Empty Mandatory Fields,UI,Form Validation,Multiple error alerts appear, Needs to be automated
4. UI-04,Phone Number Length Validation,UI,Phone Input Field,Phone length error displayed, Needs to be automated
5. UI-05	Verify Branding Logo Visibility	UI	Header/Logo	Hotel logo is displayed, Needs to be automated