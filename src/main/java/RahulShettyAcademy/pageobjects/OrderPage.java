package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstarctComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> ProductNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public boolean verifyOrderDisplay(String productName)
	{
		boolean match=ProductNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
