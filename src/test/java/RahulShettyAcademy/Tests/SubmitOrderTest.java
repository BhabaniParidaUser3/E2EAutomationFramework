package RahulShettyAcademy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CheckOutPage;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.OrderPage;
import RahulShettyAcademy.pageobjects.cartPage;
import RahulShettyAcademy.pageobjects.landingPage;
import RahulShettyAcademy.pageobjects.productCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	String productname="ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"purchase"})
/*public void submitOrder(String email,String password,String productName) throws IOException, InterruptedException*/
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException

	{
		
		
		productCataloguePage productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductTocart(input.get("product"));
		cartPage cartpage=productCatalogue.goTocartPage();
		boolean match=cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartpage.goToCheckOut();
		checkoutpage.selectCountry("India");
		ConfirmationPage ConfirmationPage=checkoutpage.submitOrder();
		String confirmMessage=ConfirmationPage.getConfirmationPage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistory()
	{
		productCataloguePage productCatalogue=landingPage.loginApplication("Bhabani.sk.parida@gmail.com", "Bhabani@123");
		OrderPage ordersPage=productCatalogue.goToorderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productname));
		
	}
	
	public String getScreenshot(String testcasename) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
	}
	
/*	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"Bhabani.sk.parida@gmail.com","Bhabani@123","ZARA COAT 3"},{"kar.prangya123@gmail.com","Prangya@123","IPHONE 13 PRO"}};
	}
*/
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	/*	
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "Bhabani.sk.parida@gmail.com");
		map.put("password", "Bhabani@123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "kar.prangya123@gmail.com");
		map1.put("password", "Prangya@123");
		map1.put("product", "IPHONE 13 PRO");*/
		
		List <HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//RahulShettyAcademy//data//PurchaseOrder.json");
		//List <HashMap<String,String>>data=getJsonDataToMap("G:\\Automation\\SeleniumFrameworkDesign\\src\\test\\java\\RahulShettyAcademy\\data\\PurchaseOrder.json");

		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
}
