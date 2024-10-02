package Swapnal.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Swapnal.pageObjects.cartPage;
import Swapnal.pageObjects.orderPage;

public class abstractComponent {
	
	WebDriver driver;
	//Page Fatory
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement cart;
	
	@FindBy(xpath="//button[contains(@routerlink,'myorders')]")
	WebElement orderHeader;

	
	public abstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitforElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	public void waitforElementToDisappear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public cartPage goToCartPage()
	{
		cart.click();
		cartPage cartpage = new cartPage(driver);
		return cartpage;
	}
	
	public orderPage goToOrdersPage()
	{
		orderHeader.click();
		orderPage orderpage = new orderPage(driver);
		return orderpage;
	}
	
	public void waitforElementToBeClickable(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	
    
}
