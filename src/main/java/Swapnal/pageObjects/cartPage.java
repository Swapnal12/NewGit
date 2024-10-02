package Swapnal.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Swapnal.abstractComponents.abstractComponent;

public class cartPage extends abstractComponent{
	
	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//List<WebElement> cards = driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
	
	//PageFactory
	@FindBy(xpath="//div[@class='cartSection']/h3")
    List<WebElement> cartProducts;
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutButton;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By checkOut=By.xpath("//li[@class='totalRow']/button");
	
	
	public Boolean verifyProductDisplayed(String productName)
	{
		Boolean match = cartProducts.stream()
				.anyMatch(productCart -> productCart.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public checkOutPage goToCheckout()
	{
		waitforElementToBeClickable(checkOut);
		Actions actions = new Actions(driver);
		actions.moveToElement(checkOutButton).click().build().perform();
		
		
		
		checkOutPage checkoutpage = new checkOutPage(driver);
		return checkoutpage;
	}
	
	
	
	
	
	
	
	
}
