### Steps to implementing scenarios
1. Create a scenario in either an existing 
feature file or in a new feature file if applicable
2. Create **EMPTY** step definition from this scenario. Note
you may be able to re use previous step definitions
3. Implement methods for the step definitions in applicable
page classes (following page object model)
4. After methods have been created use them in the correct
step definition methods
   5. Note: If you have any data that may be reused
   by several different tests please add it to
   config.properties


### Steps to running tests on a different browser
**Note:** the default browser is set to run on chrome
1. Choose which browser you want to use and ensure it is
set up in the switch case inside of DriverUtils class
2. If the driver is set up in the switch case you can change
the app.browser value in the config.properties value to the 
browser value you would like to execute the test on

### Steps to running tests in sauce labs
1. Go into config.properties file and fill out sauce.username 
and sauce.accessKey with your info
2. Set app.host to *saucelabs* and run any feature/runner how
you normally would
3. Open your saucelabs dashboard to see the results