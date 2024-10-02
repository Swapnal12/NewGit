package Swapnal.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Swapnal.abstractComponents.abstractComponent;

public class confirmationPage extends abstractComponent{
	WebDriver driver;

	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement message;
	
	public String getMessage()
	{
		return message.getText();
		
	}
	
	
	


}
