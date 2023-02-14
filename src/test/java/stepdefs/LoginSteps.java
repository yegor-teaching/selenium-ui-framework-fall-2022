package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Given("user enters valid {string} and {string}")
    public void user_enters_valid_username_and_password(String username, String password) {
        loginPage.enterValidLoginInfo(username, password);
    }
    @When("user clicks on Sign In button")
    public void user_clicks_on_sign_in_button() {
        loginPage.clickSignInBtn();
    }
    @Then("verify user is successfully logged in to the account")
    public void verify_user_is_successfully_logged_in_to_the_account() {
        homePage.verifyPage();
    }
}
