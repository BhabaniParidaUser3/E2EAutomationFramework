package RahulShettyAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.pageobjects.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public landingPage landingPage;
	
	public WebDriver initializationDriver() throws IOException
	{
		
		//properties class
		Properties prop =new Properties();
		String filePath=System.getProperty("user.dir") +"\\src\\main\\java\\RahulShettyAcademy\\resources\\GlobalData.properties";
		FileInputStream fis =new FileInputStream(filePath);
		prop.load(fis);
		//String browserName=prop.getProperty("browser");
		
		//this below line added If user want to run test in multiple browser from terminal without touching script again & again.
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			WebDriverManager.chromedriver().clearDriverCache().setup();
			ChromeOptions option = new ChromeOptions();
	        option.addArguments("--remote-allow-origins=*");
	        if(browserName.contains("headless"))
	        {
	        option.addArguments("headless");
	        }
	        driver=new ChromeDriver(option);
	        driver.manage().window().setSize(new Dimension(1400,900));
	        
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			FirefoxOptions option = new FirefoxOptions();
			 if(browserName.contains("headless"))
		        {
		        option.addArguments("headless");
		        }
			driver=new FirefoxDriver(option);	
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().clearDriverCache().setup();
			EdgeOptions option = new EdgeOptions();
	        option.addArguments("--remote-allow-origins=*");
	        if(browserName.contains("headless"))
	        {
	        option.addArguments("headless");
	        }
			driver=new EdgeDriver(option);	
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//reading json to string
		String jsonContent=FileUtils.readFileToString(new File(filePath),
				(StandardCharsets.UTF_8));
		
		//convert string to hashmap using jackson databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
			
	}
	
	@BeforeMethod(alwaysRun=true)
	public landingPage launchApplication() throws IOException
	{
	driver=	initializationDriver();
	landingPage=new landingPage(driver);
	landingPage.goTo();
	return landingPage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}

}
