package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.DriverUtilities;

public class BasePage {
    WebDriver driver = DriverUtilities.getDriver();
    Faker faker = new Faker();

    public BasePage(){
        //The page factory class is essentially what makes Page Object Model easy and worthwhile to implement
        //It will essentially dynamically initialize the WebElements on the page
        //The .initElements() method is used to initialize the elements and we need to pass the driver and the object
        //page that we want to initialize the elements on
        PageFactory.initElements(driver, this);
    }
}
