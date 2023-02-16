package utilities;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;

public class DriverUtilities {

    static WebDriver driver;

    //We are adding the cucumber Scenario parameter which will hold the data for the scenario which is currently running
    public static void createDriver(Scenario scenario){
        //Set up an if statement to determine what the app.host value is. If it is localhost we will run test on local
        //machine
        if(ConfigReader.getConfigProperty("app.host").equalsIgnoreCase("localhost")) {
            switch (ConfigReader.getConfigProperty("app.browser")) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    //WebDriverManager essentially removes the need to manually manage any drivers. We can use it both "download"
                    //and manage the drivers.
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
            // else if the value of app.host is saucelabs we are setting up a RemoteDriver to run tests on saucelabs
        }else if (ConfigReader.getConfigProperty("app.host").equalsIgnoreCase("saucelabs")){
            //Here we are setting up mutable capabilities which are essentially settings and values that the driver requires
            //The sauceOptions variable is used to store the username and accesskey for your saucelabs account
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("username", ConfigReader.getConfigProperty("sauce.username"));
            sauceOptions.setCapability("accesskey", ConfigReader.getConfigProperty("sauce.accessKey"));

            //The capabilities variable is used to store all the general driver/platform information as well as pass the
            //sauce options to the hub
            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", ConfigReader.getConfigProperty("sauce.browserName"));
            capabilities.setCapability("browserVersion", ConfigReader.getConfigProperty("sauce.browserVersion"));
            capabilities.setCapability("platformName", ConfigReader.getConfigProperty("sauce.platformName"));
            capabilities.setCapability("sauce:options", sauceOptions);

            try {
                //Here we are initializing the WebDriver to a Remote driver an passing the URL to the Selenium grid hub
                //and passing the necessary capabilities
                driver = new RemoteWebDriver(new URL(ConfigReader.getConfigProperty("sauce.urlWest")), capabilities);

                //Here we are casting the driver into JavascriptExecutor and using the .executeScript() method to pass
                //the scenario name to sauce labs
                ((JavascriptExecutor)driver).executeScript("sauce:job-name=" + scenario.getName());
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get(ConfigReader.getConfigProperty("app.baseurl"));
    }

    public static void quitDriver(Scenario scenario){
        //We are using this if statement to see if the run is happening on saucelabs
        if (ConfigReader.getConfigProperty("app.host").equalsIgnoreCase("saucelabs")){

            //((JavascriptExecutor)driver).executeScript("sauce:job-result="+(scenario.isFailed()? "failed" : "passed"));
            //This ternary operator will do the same thing as the below if statement
            //We are creating this if statement to determine wether the scenario failed or passed and are passing the
            //data to saucelabs
            if(scenario.isFailed()){
                ((JavascriptExecutor)driver).executeScript("sauce:job-result=failed");
            }else{
                ((JavascriptExecutor)driver).executeScript("sauce:job-result=passed");
            }
        }

        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
