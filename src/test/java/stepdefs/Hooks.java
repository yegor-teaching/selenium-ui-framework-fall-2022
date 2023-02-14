package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.ConfigReader;
import utilities.DriverUtilities;

public class Hooks {

    @Before
    public void setUp(){
        //Here we are initializing the properties object inside of the ConfigReader class we created
        ConfigReader.initializeProperties();

        DriverUtilities.createDriver();
    }

    @After
    public void tearDown(){
        DriverUtilities.getDriver().quit();
    }
}
