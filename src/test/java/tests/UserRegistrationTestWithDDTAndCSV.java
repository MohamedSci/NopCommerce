package tests;




import java.io.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndCSV extends TestBase {
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	CSVReader reader;
	String CSV_file = System.getProperty("user.dir") + "/src/test/java/data/UserData.csv";

	String[] csvCell;
	@Test(priority = 1, alwaysRun = true)
	public void UserCanRegisterSuccssfullyJSON() throws IOException {
		// get path of CSV file
		reader = new CSVReader(new FileReader(CSV_file));

		// while loop will be executed till the lastvalue in CSV file .
		while ((csvCell = reader.readNext()) != null) {
			String firstname = csvCell[0];
			String lastName = csvCell[1];
			String email = csvCell[2];
			String password = csvCell[3];

			homeObject = new HomePage(driver);
			homeObject.openRegistrationPage();
			registerObject = new UserRegistrationPage(driver);
			registerObject.userRegistration(firstname, lastName, email, password);
			Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
			registerObject.userLogout();
			homeObject.openLoginPage();
			loginObject = new LoginPage(driver);
			loginObject.UserLogin(email, password);
			Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
			registerObject.userLogout();
		}
	}


}



