package Swapnal.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class orderPage {
	
	WebDriver driver;

	public orderPage(WebDriver driver) {
		super();
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	

	//PageFactory
	@FindBy(xpath="//tr/td[2]")
    List<WebElement> productNames;
	

	
	public Boolean verifyOrdersDisplayed(String productName)
	{
		Boolean match = productNames.stream()
				.anyMatch(productCart -> productCart.getText().equalsIgnoreCase(productName));
		return match;
		
	}

}
