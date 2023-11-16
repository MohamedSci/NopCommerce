package tests;



import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import data.JsonDataReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndJSON extends TestBase
{

	HomePage homeObject ;
	UserRegistrationPage registerObject ;
	LoginPage loginObject ;
	JsonDataReader jsonReader = new JsonDataReader();

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfullyJSON() throws IOException, ParseException
	{
		jsonReader.JsonReader();
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(jsonReader.firstname,jsonReader.lastname,jsonReader.email,jsonReader.password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}

	@Test(dependsOnMethods= {"RegisteredUserCanLoginJSON"})
	public void RegisteredUserCanLogoutJSON()
	{
		registerObject.userLogout();
	}

	@Test(dependsOnMethods= {"UserCanRegisterSuccssfullyJSON"})
	public void RegisteredUserCanLoginJSON() throws IOException, ParseException {
		jsonReader.JsonReader();
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(jsonReader.email, jsonReader.password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}


}
