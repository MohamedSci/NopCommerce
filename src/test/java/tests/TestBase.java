package tests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.cucumber.testng.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Utilies.Helper;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase  extends AbstractTestNGCucumberTests
{
	public static WebDriver driver ;
	public static String downloadPath = System.getProperty("user.dir") + "/Downloads";

	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}
	public static ChromeOptions chromeOption() {
		ChromeOptions chromeOptions = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
		chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		return chromeOptions;
	}

	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) 
	{
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOption()); 
		}

		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(firefoxOption()); 
		}

		else if (browserName.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver(chromeOption()); 
		}

		else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver(); 
		}		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.navigate().to("http://demo.nopcommerce.com/");
	}
	
    @AfterMethod
    public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			Helper helper = new Helper();
			helper.takeElementsScreenshot(driver, result.getName());
		}
	}

	
	@AfterSuite
	public void quiteAll(){
		driver.quit();
	}
}
