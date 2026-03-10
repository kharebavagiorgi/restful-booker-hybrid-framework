# 🏨 Restful-Booker Framework: Full-Stack Test Suite

This document serves as the live backlog and status tracker for the Automated Test Suite, covering API, UI, and Mobile layers.

---

## 🛰️ 1. API Test Suite: Health & Bookings
*Layer: Backend (Rest-Assured)*

| ID | Test Case | Method | Endpoint | Expected Result | Status |
|:---|:---|:---:|:---|:---|:---|
| **API-01** | Verify API System Health | GET | `/ping` | Status code 201 (Created) | Automated ✅ |
| **API-02** | Get All Booking IDs | GET | `/booking` | Returns JSON array of IDs | Automated ✅ |
| **API-03** | Get Booking By ID | GET | `/booking/{id}` | Returns valid JSON object | Automated ✅ |
| **API-04** | Create Booking Record | POST | `/booking` | 200 OK & returns `bookingid` | Automated ✅ |

---

## 🖥️ 2. UI Automation: Contact & Regression
*Layer: Web (Selenium + Java)*

### 📧 Contact Management
| ID | Test Case | Component | Expected Result | Status |
|:---|:---|:---|:---|:---|
| **UI-01** | Successful Contact Submission | Contact Form | Success message displayed | Automated ✅ |
| **UI-02** | Invalid Email Format | Email Field | Validation error displayed | Automated ✅ |
| **UI-03** | Empty Mandatory Fields | Form | Multiple error alerts appear | Automated ✅ |
| **UI-04** | Phone Number Length | Phone Field | Length error displayed | Automated ✅ |
| **UI-05** | Verify Branding Logo | Header/Logo | Hotel logo is displayed | Automated ✅ |

### 🛠️ High-Complexity Regression (Hard Mode)
| ID | Scenario | Tooling | Why it's "Hard Mode" | Status |
|:---|:---|:---|:---|:---|
| **REG-01** | Bypass Admin Footer Link | `JSExecutor` | Bypasses sticky footer overlays. | Automated ✅ |
| **REG-02** | Dynamic Room Management | `Select` Class | Handles non-standard dropdowns. | Automated ✅ |
| **REG-03** | Precision Calendar Booking | `Actions` Class | Mouse Drag & Drop for date range. | 🔴 To be Automated |
| **REG-04** | Branded Room Image Audit | `File Upload` | targeted element screenshot capture. | 🔴 To be Automated |
| **REG-05** | Stale Table Sync | `XPath Axes` | Verifies DOM state post-deletion. | 🔴 To be Automated |

---

## 📱 3. Mobile Automation
*Layer: Mobile Web (Appium + Chrome)*

| ID | Test Case | Platform | Browser | Expected Result | Status |
|:---|:---|:---|:---|:---|:---|
| **MOB-01** | Verify Mobile Landing Page | Android (Pixel 8) | Chrome | "Book Now" is visible/clickable | Automated ✅ |

> **TC-M1 Logic:** Verifies site responsiveness by launching Chrome on a Pixel 8 emulator, navigating to the UI, and ensuring the "Book Now" button scales correctly to the viewport.