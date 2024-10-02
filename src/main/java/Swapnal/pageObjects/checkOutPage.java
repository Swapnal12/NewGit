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

public class checkOutPage extends abstractComponent {

	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectDropdown;

	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> countries;

	By proceedButton = By.xpath("//a[@class='btnn action__submit ng-star-inserted']");
	By selectCountry=By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']//span");
	
	public void selectCountry(String countryName) {
		selectDropdown.sendKeys(countryName);
		waitforElementToAppear(selectCountry);
		countries.stream().filter(country -> country.getText().equalsIgnoreCase("India")).findFirst()
				.ifPresent(country -> {
					country.click();

					waitforElementToBeClickable(proceedButton);

				});
	}
	
	public confirmationPage clickPlaceOrder()
	{
		WebElement placeOrder = driver.findElement(proceedButton);
		Actions actions = new Actions(driver);
		actions.moveToElement(placeOrder).click().build().perform();
		
		return new confirmationPage(driver);
	}

	

}
