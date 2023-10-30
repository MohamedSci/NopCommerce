package Utilies;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	
	public void takeElementsScreenshot(WebDriver driver, String SsName) {
		Path filePath = Paths.get("./ScreenShot",SsName +".png");
		try {
			Files.createDirectories(filePath.getParent());
			FileOutputStream out = new FileOutputStream(filePath.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		}catch(Exception e) {
			System.out.println("Exception takeElementsScreenshot "+e.getMessage());
		}
	}
	
	// This dependency [ru.yandex.qatools.ashot] is added in Pom.xml 
		public void captureFullPageScreenshot(WebDriver driver, String fileName){
			try {
				Screenshot screenshot=new AShot()
						.shootingStrategy(ShootingStrategies.viewportPasting(1000))
						.takeScreenshot(driver);
				ImageIO.write(screenshot.getImage(),"PNG",new File("./ScreenShot/"+fileName+"WEbPage" + ".png"));
			}catch(Exception e) {
				System.out.println("captureFullPageScreenshot Exception "+ e.getMessage());
			}
			

		}
	

}
