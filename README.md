# SauceDemo Test Automation Framework

![Java](https://img.shields.io/badge/Java-25-blue)
![Selenium](https://img.shields.io/badge/Selenium-4-green)
![TestNG](https://img.shields.io/badge/TestNG-Framework-orange)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![CI](https://github.com/soitirakis/saucedemo_final_project/actions/workflows/run-tests.yml/badge.svg)

---

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

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- Jackson (JSON processing)
- json-simple
- Logback (logging)

---

## 📁 Project Structure

```
saucedemo_final_project/
├── .github/
│   └── workflows/
│       └── run-tests.yml          # GitHub Actions CI pipeline
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── driver/
│   │       │   ├── DriverFactory.java
│   │       │   └── WaitUtils.java
│   │       ├── pages/             # Page Object Model (POM) classes
│   │       │   ├── BasePage.java
│   │       │   ├── LoginPage.java
│   │       │   ├── InventoryPage.java
│   │       │   ├── ShoppingCart.java
│   │       │   └── CheckoutSteps.java
│   │       ├── testdata/          # Test data classes, json files, constants
│   │       │   ├── classes/
│   │       │   ├── files/
│   │       │   └── pages/
│   │       └── utils/
│   │           ├── Reader.java
│   │           ├── Writer.java
│   │           ├── RandomGenerator.java
│   │           └── SortedGenerators.java
│   └── test/
│       └── java/                  # Test classes
│           ├── BaseTests.java
│           ├── LoginTests.java
│           ├── InventoryTests.java
│           ├── FilterTests.java
│           ├── EndToEndTests.java
│           └── UserInformationTests.java
├── pom.xml                        # Maven dependencies & build config
├── testng.xml                     # TestNG suite definition
├── README.md                      # Project documentation
└── .gitignore                     # Git ignored files
```

## CI/CD

Tests run automatically on every push and pull request to `main` via GitHub Actions.

**Workflow:** `.github/workflows/run-tests.yml`

- Runner: `ubuntu-latest`
- JDK: 25 (Temurin)
- Chrome: latest (headless via `--headless=new`)
- Command: `mvn clean test -Dheadless=true`
- Artifacts: Surefire reports uploaded on every run (pass or fail)

To force headless mode locally:

```bash
mvn clean test -Dheadless=true
```

---

## 🚀 How to Run the Tests

Follow the steps below to execute the automated test suite locally.

### ⚙️ Prerequisites

Make sure you have installed:

- Java JDK 17+
- Maven
- Google Chrome

Verify installation:

```bash
java -version
mvn -version
```

Clone and run:

```bash
git clone https://github.com/soitirakis/saucedemo_final_project.git
cd saucedemo_final_project

# Run full suite (headed)
mvn clean test

# Run headless (same as CI)
mvn clean test -Dheadless=true
```

Reports are written to `target/surefire-reports/`.

---

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

---

## 👤 Author

**Andrei**

QA Automation  
Specialized in Java-based test automation frameworks.

This project was built as a portfolio-level automation framework demonstrating clean architecture, best practices, and scalable design.

GitHub: https://github.com/soitirakis
