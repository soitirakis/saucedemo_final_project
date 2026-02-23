# SauceDemo Test Automation Framework

Automated UI testing project for the SauceDemo application built with **Java, Selenium WebDriver, TestNG, and Maven**.

This project demonstrates a clean and scalable automation framework structure using industry best practices.

---

## 📌 Project Overview

The goal of this project is to automate key user flows of the SauceDemo web application, including:

- Login functionality
- Product listing validation
- Cart operations
- Checkout process
- Logout functionality

The framework is structured for maintainability, scalability, and clarity.

---

## 🛠 Tech Stack

- Java
- Maven
- Selenium WebDriver
- TestNG
- Jackson (JSON processing)
- json-simple
- Logback (logging)

---

## 📁 Project Structure

---
```
saucedemo_final_project/
├── .github/
│   └── workflows/
│       └── run-tests.yml          # GitHub Actions CI pipeline
│
├── src/
│   ├── main/
│   │   └── java/
|   |        └── driver/            
│   │          ├── DriverFactory.java
│   │          ├── WaitUtils.java
│   │        └── pages/               # Page Object Model (POM) classes
│   │          ├── BasePage.java
│   │          ├── LoginPage.java
│   │          └── InventoryPage.java
│   │          ├── ShoppingCart.java
│   │          └── CheckoutSteps.java
│   │        └── testdata/             # Test data classes, json files, constants 
│   │          ├── classes
│   │          ├── files
│   │          └── pages
│   │        └── utils/             
│   │          ├── Reader.java
│   │          ├── Writer.java
│   │          ├── RandomGenerator
│   │          └── SortedGenerator
│   │   
│   └── test/
│       └──  java/
│             └── tests/             # Test classes
│               ├── BaseTests.java
│               └── LoginTests.java
|               └── InventoryTests.java
|               └── FilterTests.java
|               └── UserInformationTests.java
│                   
│
├── pom.xml                        # Maven dependencies & build config
├── README.md                      # Project documentation
└── .gitignore                     # Git ignored files
```

## 🚀 How to Run the Tests

Follow the steps below to execute the automated test suite locally.

### ⚙️ Prerequisites

Make sure you have installed:

- Java JDK (recommended: 17+)
- Maven
- Google Chrome or Firefox

Verify installation:

```bash

java -version
mvn -version

git clone https://github.com/soitirakis/saucedemo_final_project.git
cd saucedemo_final_project

mvn clean install

mvn clean test

mvn clean test -Dsurefire.suiteXmlFiles=testng.xml

mvn clean test -Dbrowser=chrome

mvn clean test -Dbrowser=firefox

target/surefire-reports/


---

If you'd like, I can also:
- Add a **CI section (GitHub Actions ready)**
- Make it more “Senior QA Engineer portfolio style”
- Or tailor it exactly to your framework (if you paste your test runner structure)

Just tell me the level you’re aiming for 🚀
```

## 📐 Design Principles Used

This framework follows industry best practices to ensure scalability, readability, and maintainability.

### 🧱 Page Object Model (POM)
Each page of the application is represented by a dedicated class.
This improves:
- Code reusability
- Separation of concerns
- Easier maintenance when UI changes

---

### 🔄 Reusability & DRY Principle
Common functionality (driver setup, waits, utilities, configuration handling) is abstracted into reusable components to avoid code duplication.

---

### 🧪 Test Isolation
Each test is independent and can be executed individually or as part of a suite without affecting others.

---

### 📂 Separation of Test Logic and Test Data
Test data is externalized (JSON / configuration files) to:
- Improve flexibility
- Allow easy modification without changing test logic
- Support future data-driven testing

---

### 📋 Logging & Debugging Support
Logging is implemented to provide visibility into test execution flow and simplify debugging.

---

### 🧩 Scalable Structure
The project structure supports:
- Adding new test cases easily
- Parallel execution (future improvement)
- CI/CD integration

## 👤 Author

**[Andrei]**

QA Automation  
Specialized in Java-based test automation frameworks.

This project was built as a portfolio-level automation framework demonstrating clean architecture, best practices, and scalable design.

GitHub: https://github.com/soitirakis