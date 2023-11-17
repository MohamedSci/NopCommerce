package Steps;

import io.cucumber.java.en.*;
import org.testng.*;
import pages.*;
import tests.*;


public class Steps extends TestBase {

    HomePage homeObject ;
    UserRegistrationPage registerObject ;
    LoginPage loginObject ;
    @Given("^The User opens the registration page$")
    public void theUserOpensTheRegistrationPage() {
        homeObject = new HomePage(driver);
        homeObject.openRegistrationPage();
    }

    @When("^he inputs his \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" and Submit$")
    public void heInputsHisFirstnameAndLastnameAndEmailAndPasswordAndSubmit(
            String firstname, String lastname ,String email, String password ) {
        registerObject = new UserRegistrationPage(driver);
        registerObject.userRegistration(firstname,lastname,email,password);
    }


    @Then("^the Registration is done successfully$")
    public void theRegistrationIsDoneSuccessfully() {
        Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
    }
    @Then("The User CLicked Continue button on the success dialog")
    public void theUserCLickedContinueButtonOnTheSuccessDialog() {

        registerObject.clickRegistrationContinueButton();
    }
//    @Then("The User Log out")
//    public void theUserLogOut() {
//        registerObject.userLogout();
//    }
    @Given("^The User opens the Log in page$")
    public void theUserOpensTheLogInPage() {
        homeObject.openLoginPage();
    }

    @When("^he inputs the new registered \"([^\"]*)\" and \"([^\"]*)\" and Submit$")
    public void heInputsTheNewRegisteredEmailAndPasswordAndSubmit(String email, String password) {
        loginObject = new LoginPage(driver);
        loginObject.UserLogin(email, password);
    }


    @Then("^The LogIn process is done successfully$")
    public void theLogInProcessIsDoneSuccessfully() {
        Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
    }
    @Then("^At the End The User Log out$")
    public void atTheEndTheUserLogOut() {
        registerObject.userLogout();
    }



}
