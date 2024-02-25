package RahulShettyAcademy.Tests;

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

import RahulShettyAcademy.pageobjects.CheckOutPage;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.cartPage;
import RahulShettyAcademy.pageobjects.landingPage;
import RahulShettyAcademy.pageobjects.productCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		landingPage lp=new landingPage(driver);
		lp.goTo();
		productCataloguePage productCatalogue=lp.loginApplication("Bhabani.sk.parida@gmail.com", "Bhabani@123");
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductTocart(productname);
		cartPage cartpage=productCatalogue.goTocartPage();
		boolean match=cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartpage.goToCheckOut();
		checkoutpage.selectCountry("India");
		ConfirmationPage ConfirmationPage=checkoutpage.submitOrder();
		String confirmMessage=ConfirmationPage.getConfirmationPage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}
