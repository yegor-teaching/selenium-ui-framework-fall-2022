package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.DriverUtilities;

public class Hooks {

    @Before
    //We are adding the cucumber Scenario parameter which will hold the data for the scenario which is currently running
    public void setUp(Scenario scenario){
        //Here we are initializing the properties object inside of the ConfigReader class we created
        ConfigReader.initializeProperties();
        DriverUtilities.createDriver(scenario);
    }

    @After
    public void tearDown(Scenario scenario){
        DriverUtilities.quitDriver(scenario);
    }
}
