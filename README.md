# YuvaIntern Task 4 - Selenium Test Automation with CI

[![Selenium Tests - CI](https://github.com/Sumi2007/YuvaIntern-Task4-Selenium-CI/actions/workflows/selenium-tests.yml/badge.svg)](https://github.com/Sumi2007/YuvaIntern-Task4-Selenium-CI/actions/workflows/selenium-tests.yml)

## Table of Contents

1. Project Overview
2. Objectives
3. Technology Stack
4. Project Structure
5. Prerequisites
6. Clone the Repository
7. Install Maven Dependencies
8. Run Tests Locally
9. Environment Configuration
10. GitHub Actions Workflow
11. CI Triggers
12. CI Execution Flow
13. Step-by-Step: Running the Tests Through CI
14. Test Reports
15. Test Execution Results
16. Failure Visibility
17. Troubleshooting
18. CI Benefits
19. Future Improvements
20. Author

## 1. Project Overview

This project demonstrates the integration of a Selenium WebDriver test automation framework with Continuous Integration (CI) using GitHub Actions.

The framework is developed using Java, Selenium WebDriver, TestNG and Maven. GitHub Actions automatically executes the Selenium regression suite when changes are pushed to the main branch or when pull request activity targets the main branch.

The CI environment uses headless Chrome so Selenium tests can run on the GitHub-hosted Ubuntu environment without a visible browser window.

Test reports are generated and uploaded as GitHub Actions artifacts.

## 2. Objectives

The main objectives are:

- Integrate Selenium WebDriver automation with a CI pipeline.
- Configure GitHub Actions for automated test execution.
- Execute Selenium tests using Java 17 and Maven.
- Support headless Chrome execution in CI.
- Automatically trigger tests on pushes to main.
- Automatically trigger tests for pull requests targeting main.
- Generate and upload test reports.
- Document CI setup, prerequisites, configuration and troubleshooting.

## 3. Technology Stack

| Technology / Tool | Purpose |
|---|---|
| Java 17 | Programming language |
| Selenium WebDriver 4 | Browser automation |
| TestNG | Test execution |
| Maven | Build and dependency management |
| GitHub Actions | Continuous Integration |
| Google Chrome | Browser |
| ExtentReports | Test reporting |
| Log4j2 | Logging |
| Jackson | Data processing |
| Git/GitHub | Source code management |

## 4. Project Structure

YuvaIntern-Task4-Selenium-CI/

    .github/
        workflows/
            selenium-tests.yml

    src/
        main/
            java/
            resources/
                config/
                    default.properties
                    qa.properties
                    ci.properties

        test/
            java/

    pom.xml
    testng.xml
    testng-smoke.xml
    README.md
    .gitignore

## 5. Prerequisites

Before running the project locally, install:

### Java 17

    java -version

The output should show Java 17.

### Maven

    mvn -version

### Git

    git --version

### Google Chrome

Google Chrome is used for Selenium browser testing.

For CI execution, Chrome runs in headless mode on the GitHub-hosted Ubuntu runner.

## 6. Clone the Repository

    git clone https://github.com/Sumi2007/YuvaIntern-Task4-Selenium-CI.git

    cd YuvaIntern-Task4-Selenium-CI

## 7. Install Maven Dependencies

Run:

    mvn clean install -DskipTests

This downloads the project dependencies defined in pom.xml.

## 8. Run Tests Locally

Run the default test suite:

    mvn test

Run the regression suite using the CI configuration:

    mvn test -Denv=ci -DsuiteXmlFile=testng.xml

Run the smoke suite:

    mvn test -Denv=ci -DsuiteXmlFile=testng-smoke.xml

The CI configuration runs Chrome in headless mode.

## 9. Environment Configuration

Environment configuration files are located under:

    src/main/resources/config/

Available configuration files:

    default.properties
    qa.properties
    ci.properties

### CI Configuration

The ci.properties file contains:

    headless=true
    maximize=false
    retry.count=1

### Configuration Details

- headless=true runs Chrome without a visible browser window.
- maximize=false prevents browser maximization in CI.
- retry.count=1 allows one retry for temporary network-related instability.

The CI environment is selected using:

    -Denv=ci

The complete CI command is:

    mvn test -Denv=ci -DsuiteXmlFile=testng.xml

### Environment Variables and Secrets

The current implementation does not require sensitive environment variables or GitHub Secrets.

| Setting | Purpose |
|---|---|
| -Denv=ci | Selects CI configuration |
| -DsuiteXmlFile=testng.xml | Selects regression suite |

## 10. GitHub Actions Workflow

The workflow file is located at:

    .github/workflows/selenium-tests.yml

The workflow configuration is:

    name: Selenium Tests - CI

    on:
      push:
        branches:
          - main
      pull_request:
        branches:
          - main

    jobs:
      selenium-tests:
        runs-on: ubuntu-latest

        steps:
          - name: Checkout source code
            uses: actions/checkout@v4

          - name: Set up Java 17
            uses: actions/setup-java@v4
            with:
              distribution: temurin
              java-version: '17'
              cache: maven

          - name: Run Selenium tests
            run: mvn test -Denv=ci -DsuiteXmlFile=testng.xml

          - name: Upload test reports
            if: always()
            uses: actions/upload-artifact@v4
            with:
              name: selenium-test-reports
              path: |
                test-output/
                target/surefire-reports/
                test-output/ExtentReports/
              if-no-files-found: ignore

### Workflow Steps

| Step | Purpose |
|---|---|
| Checkout source code | Downloads repository source code |
| Set up Java 17 | Configures Java 17 and Maven caching |
| Run Selenium tests | Executes the TestNG regression suite |
| Upload test reports | Uploads generated reports as an artifact |

The report upload step uses if: always() so reports can still be uploaded when tests fail.

## 11. CI Triggers

The workflow runs when code is pushed to the main branch.

It also runs for pull requests targeting the main branch.

## 12. CI Execution Flow

Developer pushes code or creates a pull request

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

Execute tests in headless Chrome

↓

Upload test reports

## 13. Step-by-Step: Running the Tests Through CI

### Step 1: Push Code

Make a code change and push it to the main branch:

    git add .

    git commit -m "Trigger CI run"

    git push origin main

### Step 2: Open GitHub Actions

Open the GitHub repository and select:

Actions → Selenium Tests - CI

### Step 3: Monitor the Workflow

Open the workflow run and select the selenium-tests job.

Review:

- Java setup
- Maven dependency installation
- Selenium test execution
- Test results
- Report upload

### Step 4: Download Reports

After the workflow completes, download:

    selenium-test-reports

from the workflow Artifacts section.

### Step 5: Review Reports

The downloaded artifact contains:

    target/surefire-reports/

The verified regression reports include:

    regression.html
    regression.xml

## 14. Test Reports

The workflow uploads reports as:

    selenium-test-reports

The following locations are collected:

    target/surefire-reports/
    test-output/
    test-output/ExtentReports/

The HTML report can be opened in a browser to review individual test execution results.

## 15. Test Execution Results

The Selenium regression suite was successfully executed through GitHub Actions.

### Actual CI Result

| Result | Count |
|---|---:|
| Total Tests | 10 |
| Passed | 10 |
| Failed | 0 |
| Skipped | 0 |

The verified CI regression execution completed successfully with all 10 tests passing.

The reported regression execution time was approximately 28 seconds.

## 16. Failure Visibility

If Selenium test execution fails, GitHub Actions marks the workflow run as failed.

The report upload step uses:

    if: always()

Therefore, generated reports can still be uploaded when the test execution step fails.

The current implementation does not configure separate email or Slack notifications.

Failure visibility is provided through:

- GitHub Actions workflow status
- CI job logs
- Uploaded test reports

GitHub's built-in workflow notifications can also be enabled through GitHub notification settings.

## 17. Troubleshooting

### Workflow Does Not Start

Check:

- .github/workflows/selenium-tests.yml exists.
- Changes are pushed to main.
- Pull requests target main.
- GitHub Actions is enabled.

### YAML Configuration Error

Verify the run command is correctly indented:

    - name: Run Selenium tests
      run: mvn test -Denv=ci -DsuiteXmlFile=testng.xml

### Browser Visibility Issues

Verify:

    headless=true

### Wrong Environment

Make sure the Maven command contains:

    -Denv=ci

### Local Tests Pass but CI Fails

Check:

- Headless browser behavior
- Explicit waits
- Element locators
- CI logs
- Network-dependent application behavior

### Chrome/WebDriver Problems

Check browser and WebDriver compatibility and verify the Selenium version configured in pom.xml.

### Maven Dependency Problems

Try:

    mvn clean test

### Missing Reports

Check:

    target/surefire-reports/
    test-output/
    test-output/ExtentReports/

### Viewing CI Logs

Go to:

GitHub Repository → Actions → Selenium Tests - CI → Select Workflow Run → Select Job

Review the logs for the failed step.

## 18. CI Benefits

The CI integration provides:

- Automated Selenium test execution.
- Automatic execution after code changes.
- Pull request validation.
- Consistent CI execution environment.
- Headless browser execution.
- Centralized execution logs.
- Downloadable test reports.
- Reduced manual test execution effort.

## 19. Future Improvements

Possible future improvements include:

- Email or Slack notifications for failed workflows.
- Scheduled nightly regression execution.
- Multi-browser testing using a browser matrix.
- Parallel test execution.
- Selenium Grid or Docker-based remote execution.
- Publishing test summaries directly on the workflow run page.

These are future enhancements and are not part of the current implementation.

## 20. Author

**Sumithra C**

YuvaIntern - Software Test Engineer Automation Internship