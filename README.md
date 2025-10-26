# Atlys-Automation-Assignment
Java + Selenium Page Object Model (POM) automation framework for the Iceland visa application portal (https://visa.government.is/).  Features: JSON-driven test data, robust form automation with error handling, and centralized logging.

## Features
- ✅ **Page Object Model (POM)** - Clean separation of page objects and test logic
- ✅ **JSON-Driven Testing** - Dynamic test data from REST API
- ✅ **Comprehensive Error Handling** - Retry mechanisms for flaky elements
- ✅ **Log4j2 Logging** - Detailed logging for debugging
- ✅ **Configurable** - Easy configuration through properties file
- ✅ **Cross-Browser Support** - Chrome, Firefox, Edge

## Technology Stack
- **Java** 11+
- **Selenium WebDriver** 4.27.0
- **TestNG** 7.10.2
- **Maven** - Build and dependency management
- **Log4j2** 2.25.1 - Logging framework
- **Gson** - JSON processing
- **Apache HttpClient** - API calls
- **Apache PDFBox** - PDF validation
- **WebDriverManager** - Automatic driver management
<img width="868" height="474" alt="image" src="https://github.com/user-attachments/assets/2004f48f-3f13-4e11-95fc-912e9ec521a0" />

## Prerequisites
1. **Java JDK 11** or higher
2. **Maven 3.6+**
3. **Chrome/Firefox** browser installed
4. **Internet connection** (for downloading drivers and API calls)


## Setup Instructions

### 1. Clone the Repository
- git clone <https://github.com/akshat-jnn/Atlys-Automation-Assignment>
- cd atlys-visa-automation

### 2. Install Dependencies
- mvn clean install -DskipTests

### 3. Configure the Framework
- Edit `src/main/resources/config.properties`:
- app.url=https://visa.government.is/
- browser=chrome

### 4. Run Tests

**Run all tests:**
- mvn clean test

**Run specific test:**
- mvn test -Dtest=VisaApplicationTest

**Run with specific browser:**
- mvn test -Dbrowser=chrome

**Run headless:**
- mvn test -Dheadless=true

   ## Test Data
- The framework fetches test data from the JSON API:
 https://325b27ba3f2e4e229c672014a82e2175.api.mockbin.io/

```
Sample JSON structure:
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "+1234567890",
  "nationality": "United States",
  "passportNumber": "AB1234567",
  "dateOfBirth": "01/01/1990",
  "gender": "Male",
  "travelDate": "01/12/2024",
  "purpose": "Tourism",
  "duration": 15
}
```

## Error Handling Features

### 1. Retry Mechanism
- Automatic retry (3 attempts) for:
  - `StaleElementReferenceException`
  - `NoSuchElementException`
  - `ElementClickInterceptedException`

### 2. Explicit Wait
- Configurable timeouts

### 3. Fallback Strategies
- Alternative locator strategies
- Graceful degradation for optional fields
- Detailed error logging

### Known Issues / Limitations
- The PDF download after clicking "Print Application" is triggered via an OS-level dialog.
Selenium WebDriver cannot directly handle this dialog or automate the file save operation; but PDF saving must be manual or performed using DevTools commands where supported.

- The provided sample API URL does not supply all the necessary visa form fields. For missing fields, hardcoded fallback values are used in the automation script.

  ---
**Author**: Akshat Jain  
**Date**: October 2025  
**Version**: 1.0.0


