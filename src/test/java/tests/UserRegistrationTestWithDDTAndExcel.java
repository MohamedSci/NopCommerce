package tests;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

/* There is only one test which is used for REGISTRATION, lOG IN , LOGOUT
* as it's so hard to make concurrency between Registration and Sign in process with the same data provider */


public class UserRegistrationTestWithDDTAndExcel extends TestBase
{
	HomePage homeObject ;
	UserRegistrationPage registerObject ;
	LoginPage loginObject ;

	@DataProvider(name="ExcelData")
	public Object[][] userRegisterData() throws IOException
	{
		// get data from Excel Reader class
		ExcelReader ER = new ExcelReader();
		return ER.getExcelData();
	}

	@Test(priority=1,alwaysRun=true,dataProvider="ExcelData")
	public void UserCanRegisterSuccssfullyExcel(String firstname , String lastname , String email , String password)
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstname,lastname,email,password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		// The Log Out Link is not displayed in this DDT issue at this Step
		registerObject.clickRegistrationContinueButton();
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email,password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));

		registerObject.userLogout();
	}

}