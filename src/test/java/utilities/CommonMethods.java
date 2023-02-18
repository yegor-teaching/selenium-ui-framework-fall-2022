package utilities;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class CommonMethods {

    public static byte[] takeScreenshot(){
        //Here we are creating an instance of TakeScreenshot interface by casting driver to it
        //Take screenshot is similar to JSExecutor in the way its built so we can create an instance of it
        //by casting driver to it
        TakesScreenshot takesScreenshot = (TakesScreenshot) DriverUtilities.getDriver();
        //Here we are saving a screenshot in a byte array for later use
        byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);

        return screenshot;
    }

    public static void takeScreenshot(Scenario scenario){
        //We are surrounding the whole block in a try catch block in order to catch and print an error if the screenshot
        //fails
        try {
            //Here we are ensuring that the scenario has failed
            if (scenario.isFailed()) {
                //We are creating an Object of File and using the TakeScreenshot interface to take a screenshot and
                //return it as an OutputType of File
                File screenshot = ((TakesScreenshot) DriverUtilities.getDriver()).getScreenshotAs(OutputType.FILE);
                //Here we have a try catch block for checked exception
                try {
                    //We are using FileUtils.copyFile() in order to create a copy of the screenshot in the
                    // target/automation/screenshots directory
                    //Then we are using String concatenation in order to name the File with the name of the scenario +
                    //.png to ensure that it is of .png type
                    FileUtils.copyFile(screenshot, new File("target/automation/screenshots/" + scenario.getName() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("The error happened while taking screenshot and cleaning up after the test");
            e.getMessage();
        }
    }
}
