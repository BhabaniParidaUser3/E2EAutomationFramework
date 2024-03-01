package RahulShettyAcademy.TestComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import RahulShettyAcademy.pageobjects.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
	public WebDriver initializationDriver() throws IOException
	{
		
		//properties class
		Properties prop =new Properties();
		String filePath=System.getProperty("user.dir") +"\\src\\main\\java\\RahulShettyAcademy\\resources\\GlobalData.properties";
		FileInputStream fis =new FileInputStream(filePath);
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
	        option.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(option);	
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions option = new FirefoxOptions();
	        option.addArguments("--remote-allow-origins=*");
			driver=new FirefoxDriver(option);	
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions option = new EdgeOptions();
	        option.addArguments("--remote-allow-origins=*");
			driver=new EdgeDriver(option);	
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public landingPage launchApplication() throws IOException
	{
	driver=	initializationDriver();
	landingPage landingPage=new landingPage(driver);
	landingPage.goTo();
	return landingPage;
		
	}

}
