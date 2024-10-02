package Swapnal.tests;

import org.testng.AssertJUnit;

import Swapnal.pageObjects.*;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ADIDAS ORIGINAL";
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("carythomson@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Cary@1234");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card-body']/h5/b")));

		List<WebElement> cards = driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));

		WebElement prod = cards.stream().filter(card -> card.getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);

		System.out.println(prod.getText());

		prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();

		List<WebElement> productsCart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = productsCart.stream()
				.anyMatch(productCart -> productCart.getText().equalsIgnoreCase(productName));
		// Assert.assertTrue(match);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='totalRow']/button")));
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//li[@class='totalRow']/button"));

		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");

		List<WebElement> countries = driver
				.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));

		// countries.stream().filter(country->country.getText().equalsIgnoreCase("India"));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']//span"))));

		countries.stream()

				.filter(country -> country.getText().equalsIgnoreCase("India"))

				.findFirst()

				.ifPresent(country -> {

					country.click();

					// Wait for stability after selecting the country

					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")));

				});

		/*
		 * for (int i = 0; i < countries.size(); i++) {
		 * 
		 * if (countries.get(i).getText().equalsIgnoreCase("India")) {
		 * countries.get(i).click();
		 * 
		 * } }
		 */

		// driver.findElement(By.xpath("//a[@class='btnn action__submit
		// ng-star-inserted']")).click();

		WebElement placeOrder = driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"));

		actions.moveToElement(placeOrder).click().build().perform();

		String receivedMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		AssertJUnit.assertTrue(receivedMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
