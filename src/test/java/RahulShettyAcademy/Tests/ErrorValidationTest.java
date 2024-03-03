package RahulShettyAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CheckOutPage;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.cartPage;
import RahulShettyAcademy.pageobjects.productCataloguePage;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LogInErrorValidation() throws IOException, InterruptedException {

		String productname = "ZARA COAT 3";
		landingPage.loginApplication("Bhabani.kk.parida@gmail.com", "Bhabani@1234");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		
		String productname="ZARA COAT 33";
		productCataloguePage productCatalogue=landingPage.loginApplication("Bhabani.sk.parida@gmail.com", "Bhabani@123");
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductTocart(productname);
		cartPage cartpage=productCatalogue.goTocartPage();
		boolean match=cartpage.verifyProductDisplay(productname);
		Assert.assertFalse(match);
		
	}

}
