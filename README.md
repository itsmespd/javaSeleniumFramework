# Java Selenium Framework

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF6F00?style=for-the-badge&logo=testng&logoColor=white)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Upcoming Integrations](#upcoming-integrations)

## Introduction

The **Java Selenium Framework** is designed to facilitate automated testing of web applications. Utilizing Selenium WebDriver, this framework provides a robust structure for writing and executing test cases, ensuring the reliability and efficiency of your web applications.

## Features

- **Cross-Browser Testing**: Execute tests across multiple browsers to ensure compatibility.
- **Parallel Testing**: Execute tests parallely using TestNG.
- **Data-Driven Testing**: Implement tests that run with various data sets.
- **Page Object Model (POM)**: Utilize POM design patterns for maintainable and reusable code.
- **Reporting**: Generate detailed test execution reports. It has both HTML and Extent Reporting capabilities.
- **Saucelabs Integration**: Comes with Saucelabs integration out of the box.

## Prerequisites

Before setting up the project, ensure you have the following installed:

- **Java Development Kit (JDK) 8 or higher**: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Apache Maven**: [Download Maven](https://maven.apache.org/download.cgi)
- **Integrated Development Environment (IDE)**: Such as IntelliJ IDEA or Eclipse
- **Web Browsers**: Latest versions of Chrome, Firefox, etc.

## Installation

Follow these steps to set up the project locally:

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/itsmespd/javaSeleniumFramework.git

2. **Navigate to the Project Directory**:

   ```bash
   cd javaSeleniumFramework
3. **Install Dependencies**:

   ```bash
   mvn clean install

## Usage

To execute the test cases:

1. Open the Project in Your Preferred IDE:
    Import the project as a Maven project.
2. Run Tests:
    Tests can be run as maven tests and with TestNG as well.

## Upcoming Integrations

1. Healenium (self-healing locators)
2. ReportPortal.io (AI-powered failure analysis)
