package Swapnal.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Swapnal.pageObjects.*;
import Swapnal.testComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;

public class SubmitOrder extends BaseTest{
	
	String countryName = "ind";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		

		landingPage landingpage=new landingPage(driver);
		productCatalogue productcatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productcatalogue.getProducts();
		productcatalogue.getProductByname(input.get("product"));
		productcatalogue.addProductToCart(input.get("product"));

		cartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplayed(input.get("product"));
		Assert.assertTrue(match);

		checkOutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry(countryName);
		confirmationPage confirmationpage = checkoutpage.clickPlaceOrder();
		String receivedMessage = confirmationpage.getMessage();

		Assert.assertTrue(receivedMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();

	}
	@Test(dataProvider="getData",dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest(HashMap<String,String> input) throws IOException
	{
		
		landingPage landingpage=new landingPage(driver);
		productCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		orderPage orderpage=productcatalogue.goToOrdersPage();
		orderpage.verifyOrdersDisplayed(input.get("product"));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "carythomson@gmail.com");
		map.put("password", "Cary@1234");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>(
		);
		map1.put("email", "kayla.andres@gmail.com");
		map1.put("password", "Kayla@123");
		map1.put("product", "ADIDAS ORIGINAL");*/
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Swapnal//selenium_framework_design//data//purchaseOrder.json");
		//System.getProperty("user.dir")+"//src//test//java//Swapnal//selenium_framework_design//data//purchaseOrder.json"
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}
	
	

}
