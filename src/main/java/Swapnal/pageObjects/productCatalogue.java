package Swapnal.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Swapnal.abstractComponents.abstractComponent;

public class productCatalogue extends abstractComponent{
	
	WebDriver driver;

	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//List<WebElement> cards = driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
	
	//PageFactory
	@FindBy(xpath="//div[@class='card-body']/h5/b")
    List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy=By.xpath("//div[@class='card-body']/h5/b");
	By addToCart=By.xpath("//div[@class='card-body']/button[2]");
	By toastMessage=By.id("toast-container");
	
	
	public List<WebElement> getProducts()
	{
		waitforElementToAppear(productBy);
		return products;
		
		
		
	}
	public WebElement getProductByname(String productName)
	{
		System.out.println(products.get(0).getText());
		System.out.println(productName);
		WebElement prod = getProducts().stream().filter(product -> product.getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		
		return prod;
		
		
	}
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByname(productName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(spinner);
		
		
	}
	
	
	
	
}
