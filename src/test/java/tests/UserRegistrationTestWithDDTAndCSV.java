package tests;

import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndCSV extends TestBase {
	String firstname, lastName, email, password;
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	CSVReader reader;
	String[] csvCell;
	// CSVReader reader ;
	// get path of CSV file
	String CSV_file = System.getProperty("user.dir") + "/src/test/java/data/UserData.csv";

	public void FileReaderFun() {
		try {
			CSVReader _reader = new CSVReader(new FileReader(CSV_file));
			if (_reader != null) {
				reader = _reader;
			}
		} catch (Exception e) {
			System.out.println("Exception $e");
		}
	}

	// while loop will be executed till the lastvalue in CSV file .

	@Test(priority = 1, alwaysRun = true)
	public void UserCanRegisterSuccssfully() throws IOException {
		while ((csvCell = reader.readNext()) != null) {
			FileReaderFun();
			firstname = csvCell[0];
			lastName = csvCell[1];
			email = csvCell[2];
			password = csvCell[3];
			homeObject = new HomePage(driver);
			homeObject.openRegistrationPage();
			registerObject = new UserRegistrationPage(driver);
			registerObject.userRegistration(firstname, lastName, email, password);
			Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
		}

	@Test(dependsOnMethods = {"RegisteredUserCanLogin"})
	public void RegisteredUserCanLogout() {
		FileReaderFun();
		try {
			while ((csvCell = reader.readNext()) != null) {
				firstname = csvCell[0];
				lastName = csvCell[1];
				email = csvCell[2];
				password = csvCell[3];
				registerObject.userLogout();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "UserCanRegisterSuccssfully" })
	public void RegisteredUserCanLogin() {
		FileReaderFun();
		try {
			while ((csvCell = reader.readNext()) != null) {
				firstname = csvCell[0];
				lastName = csvCell[1];
				email = csvCell[2];
				password = csvCell[3];
				homeObject.openLoginPage();
				loginObject = new LoginPage(driver);
				loginObject.UserLogin(email, password);
				Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
