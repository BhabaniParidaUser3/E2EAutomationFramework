package RahulShettyAcademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
	public void submitOrder(String email,String password,String productName) throws IOException, InterruptedException
	{
		
		
		productCataloguePage productCatalogue=landingPage.loginApplication(email, password);
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
		cartPage cartpage=productCatalogue.goTocartPage();
		boolean match=cartpage.verifyProductDisplay(productName);
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
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"Bhabani.sk.parida@gmail.com","Bhabani@123","ZARA COAT 3"},{"kar.prangya123@gmail.com","Prangya@123","IPHONE 13 PRO"}};
	}

}
