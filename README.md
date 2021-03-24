# README #

* This is Java test automation framework
* This project uses [Spring](https://spring.io) with [TestNG](https://testng.org).

### Modules
* **library** - various test utilities shared between all other modules
* **frontend** - UI-based tests using Selenium.

## Installation & Usage
**After any `library` module changes be sure to `mvn install` it in order to get those changes visible to other modules**
### Requirements 
Dependencies versions can be found in project Maven POM file. Main ones are:
- [Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) or higher
- (Recommended) [Intellij Lombok plugin](https://plugins.jetbrains.com/plugin/6317-lombok)
- [Allure](https://docs.qameta.io/allure/)

### Usage
* To run tests normally use `mvn test` or `mvn test -pl <module>` or Intellij toolbar.
* To debug in IntelliJ, use TestNG over Maven test runner.

##### Reports ####
   * Reports are generated on a single test run.
   * To view UI test results navigate to frontend module and use `allure serve /target/allure-results/allure` in the command line.

### Design Patterns used in this Framework ###

* BDD
* Test Separation from Framework
* Component Based Architecture
* WebDriver Factory
* Scalability
* Page Object
