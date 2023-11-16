package tests;



import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTest extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() 
	{
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration("3mnaa12", "3said21", "3mnaaa9274f17015@gmail.com", "3112134567801");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	
	@Test(dependsOnMethods= {"RegisteredUserCanLogin"})
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}
	
	@Test(dependsOnMethods= {"UserCanRegisterSuccssfully"})
	public void RegisteredUserCanLogin() 
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin("3mnaaa9274f17015@gmail.com", "3112134567801");
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
}
