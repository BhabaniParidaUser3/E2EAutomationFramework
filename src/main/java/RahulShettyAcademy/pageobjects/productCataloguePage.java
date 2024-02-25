package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstarctComponents.AbstractComponent;

public class productCataloguePage extends AbstractComponent {
	WebDriver driver;
	public productCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsBy=By.cssSelector(".mb-3");
	By addTocartBy=By.xpath(".//div[@class='card-body']/button[2]");
	By toast=By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productname)
	{
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.xpath(".//div[@class='card-body']//b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	
	public  void addProductTocart(String productname) throws InterruptedException
	{
		WebElement prod=getProductByName(productname);
		prod.findElement(addTocartBy).click();
		waitForElementToAppear(toast);
		waitForElementTodisappear(spinner);

	}

}
