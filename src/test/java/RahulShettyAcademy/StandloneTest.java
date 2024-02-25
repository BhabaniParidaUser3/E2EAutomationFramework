package RahulShettyAcademy;

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

import RahulShettyAcademy.pageobjects.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("Bhabani.sk.parida@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Bhabani@123");
		driver.findElement(By.cssSelector("#login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		String productname="ZARA COAT 3";
	
		
		/*Traditional for loop process*/
	/*	List<WebElement> we=driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
		for(WebElement el:we)
		{
			System.out.println(el.getText());
		}
		int j=0;
		for(int i=0;i<we.size();i++)
		{
			String element1=we.get(i).getText();
			if(element1.equals("ADIDAS ORIGINAL"))
			{
				j++;
				driver.findElements(By.xpath("//div[@class='card-body']//button//i[@class='fa fa-shopping-cart']")).get(i).click();
				if(j==we.size())
				{
					break;
				}
			}
			
			
		}*/
		
		/*By using Java stream concept*/
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mb-3')]")));
		List<WebElement> products=driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));
		WebElement prod=products.stream().filter(product->product.findElement(By.xpath(".//div[@class='card-body']//b")).getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(By.xpath(".//div[@class='card-body']/button[2]")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));	
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a =new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
