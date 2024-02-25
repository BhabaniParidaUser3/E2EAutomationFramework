package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstarctComponents.AbstractComponent;

public class landingPage extends AbstractComponent {
	WebDriver driver;
	public landingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="userEmail")
	WebElement userEmailEle;

	@FindBy(id="userPassword")
	WebElement userPasswordEle;

	@FindBy(id="login")
	WebElement submit;
	
	public productCataloguePage loginApplication(String email,String password)
	{
		userEmailEle.sendKeys(email);
		userPasswordEle.sendKeys(password);
		submit.click();
		productCataloguePage productCatalogue=new productCataloguePage(driver);
		return productCatalogue;

	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
