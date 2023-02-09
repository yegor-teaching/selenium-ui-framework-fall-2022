package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverUtilities;

public class Hooks {

    @Before
    public void setUp(){
        DriverUtilities.createDriver();
    }

    @After
    public void tearDown(){
        DriverUtilities.getDriver().quit();
    }
}
