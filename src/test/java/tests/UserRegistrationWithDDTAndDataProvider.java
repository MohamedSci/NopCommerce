package tests;



import org.apache.log4j.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

/* There is only one test which is used for REGISTRATION, lOG IN , LOGOUT
 * as it's so hard to make concurrency between Registration and Sign in process with the same data provider */


public class UserRegistrationWithDDTAndDataProvider extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 


	@DataProvider(name="testData")
	public static Object[][] userData()
	{
		return new Object[][] {
			{"Saz516" , "for1t660","test51660Saz99990@gmail.com","121345656"},
			{"Ahmed1665","Sabe1h6660","test51606Sab0e9999@gmail.com","12341567856"}
		};
	}

	@Test(dataProvider="testData")
	public void UserCanRegisterSuccssfullyDataProvider(String fname, String lname , String email , String password )
	{
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration(fname,lname,email,password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.clickRegistrationContinueButton();
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email,password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
		registerObject.userLogout();
	}

}
