package tests;



import java.io.IOException;

import data.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndJSON extends TestBase
{
	HomePage homeObject ;
	UserRegistrationPage registerObject ;
	LoginPage loginObject ;

	@DataProvider(name="JSONData")
	public Object[][] userRegisterData() throws IOException
	{
		// get data from Excel Reader class
		JsonDataReader JDR = new JsonDataReader();
		return JDR.getData();
	}

	@Test(priority=1,alwaysRun=true,dataProvider="JSONData")
	public void UserCanRegisterSuccssfully(String firstname , String lastname , String email , String password)
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstname,lastname,email,password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}


	@Test(dependsOnMethods= {"RegisteredUserCanLogin"})
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}

	@Test(dependsOnMethods= {"UserCanRegisterSuccssfully"},dataProvider="JSONData")
	public void RegisteredUserCanLogin(String email , String password)
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

}
