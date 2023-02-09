package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy (xpath = "//li[@class='active']")
    WebElement welcomeTestMessage;

    public void verifyPage(){
        Assert.assertTrue("Welcome test message not visible could be on wrong page", welcomeTestMessage.isDisplayed());
    }
}
