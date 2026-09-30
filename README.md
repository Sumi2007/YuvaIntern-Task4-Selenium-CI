# YuvaIntern Task 4 - Selenium Test Automation with CI

## Overview

This project integrates a Selenium-based test automation framework with Continuous Integration (CI) using GitHub Actions.

The framework executes automated Selenium tests in a headless Chrome browser through GitHub Actions whenever changes are pushed to the `main` branch or a pull request is created.

## Technology Stack

* Java 17
* Selenium WebDriver 4
* TestNG
* Maven
* GitHub Actions
* Chrome / ChromeDriver
* ExtentReports
* Log4j2
* Jackson

## Project Structure


YuvaIntern-Task4-Selenium-CI
│
├── .github
│   └── workflows
│       └── selenium-tests.yml
│
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   │       └── config
│   │           ├── default.properties
│   │           ├── qa.properties
│   │           └── ci.properties
│   │
│   └── test
│
├── pom.xml
├── testng.xml
└── testngSmoke.xml


## Environment Configuration

The framework supports environment-specific configuration.

Configuration files are located under:


src/main/resources/config/


Available environments:

* default.properties
* qa.properties
* ci.properties

The CI environment is activated using:

-Denv=ci


The ci.properties configuration enables:

headless=true
maximize=false
retry.count=1


This allows the Selenium tests to run without opening a visible browser window in the GitHub Actions runner.

## Running Tests Locally

Make sure Java 17 and Maven are installed.

To run the default TestNG suite:

mvn test


To run the tests using the CI configuration:

mvn test -Denv=ci -DsuiteXmlFile=testng.xml


## GitHub Actions CI

The GitHub Actions workflow is located at:

.github/workflows/selenium-tests.yml


The workflow is triggered when:

* Code is pushed to the `main` branch
* A pull request is created against the `main` branch

### CI Execution Flow

Developer pushes code
        ↓
GitHub Actions starts
        ↓
Checkout source code
        ↓
Set up Java 17
        ↓
Restore Maven dependencies
        ↓
Run Selenium tests
        ↓
Tests execute in headless Chrome
        ↓
Test reports are uploaded


## CI Test Command

GitHub Actions runs:

mvn test -Denv=ci -DsuiteXmlFile=testng.xml


## Test Reports

After the CI execution, test report files are uploaded as GitHub Actions artifacts.

The workflow collects available reports from:

target/surefire-reports/
test-output/
test-output/ExtentReports/


The reports can be downloaded from the completed GitHub Actions workflow under the **Artifacts** section.

## Prerequisites

### Local Execution

* Java 17
* Maven
* Git
* Google Chrome

### GitHub Actions

Java and the required Maven environment are configured by the workflow. Chrome is available on the GitHub-hosted Ubuntu runner.

## Troubleshooting

### Tests fail because of browser visibility

Verify that the CI environment contains:

headless=true


### Tests use the wrong environment

Run the suite with:

-Denv=ci


### Maven dependency problems

Try:

mvn clean test


### Checking CI Logs

Open the GitHub repository and go to:

Actions → Selenium Tests - CI


Select the workflow run and open the failed step to view the execution logs.

## CI Benefits

Using GitHub Actions provides:

* Automated test execution
* Continuous feedback after code changes
* Headless browser execution
* Centralized CI logs
* Test report artifacts
* Reduced manual test execution effort

## Author

**Sumithra C**

YuvaIntern - Software Test Engineer Automation Internship
