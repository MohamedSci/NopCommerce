package tests;



import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDDTAndDataProvider extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 


	@DataProvider(name="testData")
	public static Object[][] userData()
	{
		return new Object[][] {
			{"Saz516" , "for1t660","test51660Saz00666@gmail.com","121345656"},
			{"Ahmed1665","Sabe1h6660","test51606Sabeh00666@gmail.com","12341567856"}
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
	}
	@Test(dependsOnMethods= {"RegisteredUserCanLoginDataProvider"})
	public void RegisteredUserCanLogoutDataProvider()
	{
		registerObject.userLogout();
	}

	@Test(dependsOnMethods= {"UserCanRegisterSuccssfullyDataProvider"},dataProvider="testData")
	public void RegisteredUserCanLoginDataProvider(String email , String password)
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email,password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
}
