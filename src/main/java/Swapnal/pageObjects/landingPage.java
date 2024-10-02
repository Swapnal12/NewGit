package Swapnal.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Swapnal.abstractComponents.abstractComponent;

public class landingPage extends abstractComponent {
	
	WebDriver driver;

	public landingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	
	
	public productCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		productCatalogue productcatalogue=new productCatalogue(driver);
		return productcatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
