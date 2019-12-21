## UI automation challenge
This is UI automation framework bases on selenium following Page Object Model structure
#### Test execution steps:
Test scripts can be executed with following command:
```$xslt
mvn clean test -Denv=int
``` 
where in -Denv flag represent the environment on which the execution of tests is required.

#### Reports: 
HTML reports are generated under ExtentReports directory and contain screenshots for failed test scripts 
Report:
![Alt text](ReportScreenShot.png?raw=true "Report")
Report With Screenshot: 
![Alt text](ReportWithFailedTest.png?raw=true "Report With Screenshot")
#### Scripts:
All script classes should extend `BasicTest.class` 
#### Page Objects:
All page object classes should extend `BasicPageObject.class`
#### Property files:
All environment specific configs shold be added into property files under resource directory in `<environment_config.properties>` format.
#### Notes:
* For the steps to be printed in logs and in the HTML report only if they are annotated using `@Step` annotation.
* By default the tests are configured to run on 3 threads in parallel. it can be increased from `suite.xml` file:
```<suite name="Suite1" verbose="1"  parallel="methods" thread-count="3">```