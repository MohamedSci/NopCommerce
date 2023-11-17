package tests;




import org.testng.Assert;
import org.testng.annotations.Test;

import data.LoadProperties;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDDTAndPropertiesFile extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	String firtname = LoadProperties.userData.getProperty("firstname");
	String lastname = LoadProperties.userData.getProperty("lastname"); 
	String email = LoadProperties.userData.getProperty("email"); 
	String Password = LoadProperties.userData.getProperty("password"); 

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfullyProperties()
	{
	
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration(firtname,lastname,email,Password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.clickRegistrationContinueButton();
	}
	
	@Test(dependsOnMethods= {"RegisteredUserCanLoginProperties"})
	public void RegisteredUserCanLogoutProperties()
	{
		registerObject.userLogout();
	}
	
	@Test(dependsOnMethods= {"UserCanRegisterSuccssfullyProperties"})
	public void RegisteredUserCanLoginProperties()
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin(email,Password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
}
