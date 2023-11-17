package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends PageBase 
{
	public UserRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="gender-male")
	WebElement genderRdoBtn ; 
	
	@FindBy(id="FirstName")
	WebElement fnTxtBox; 
	
	@FindBy(id="LastName")
	WebElement lnTxtBox ; 
	
	@FindBy(id="Email")
	WebElement emailTxtBox ; 
	
	@FindBy(id="Password")
	WebElement passwordTxtBox ; 
	
	@FindBy(id="ConfirmPassword")
	WebElement confirmPasswordTxtBox ; 
	
	@FindBy(id="register-button")
	WebElement registerBtn ; 
	@FindBy(xpath = "//a[@class=\"button-1 register-continue-button\"]")
	WebElement registerContinueButton;

	@FindBy(css="div.result")
	public WebElement successMessage ; 
	
	@FindBy(css="a.ico-logout")
	public WebElement logoutLink; 
	
	@FindBy(linkText="My account")
	WebElement myAccountLink; 
	
	public void userRegistration(String firstName , String lastName , String email , String password) 
	{
		clickButton(genderRdoBtn);
		setTextElementText(fnTxtBox, firstName);
		setTextElementText(lnTxtBox, lastName);
		setTextElementText(emailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		setTextElementText(confirmPasswordTxtBox, password);
		clickButton(registerBtn);
	}
	
	public void userLogout() 
	{
		clickButton(logoutLink);
	}
	public void clickRegistrationContinueButton(){
		clickButton(registerContinueButton);
	}
	public void openMyAccountPage() 
	{
		clickButton(myAccountLink);
	}
	
}
