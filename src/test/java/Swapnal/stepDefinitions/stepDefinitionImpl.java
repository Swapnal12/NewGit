package Swapnal.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Swapnal.pageObjects.cartPage;
import Swapnal.pageObjects.checkOutPage;
import Swapnal.pageObjects.confirmationPage;
import Swapnal.pageObjects.landingPage;
import Swapnal.pageObjects.productCatalogue;
import Swapnal.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {
	
	public landingPage landingpage;
	public productCatalogue productcatalogue;
	public cartPage cartpage;
	public confirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
	    landingpage=launchApplication();
	}

	@Given("^Login with Username (.+) and Password (.+)$")
	public void login_with_username_and_password(String userName, String password) {
	productcatalogue = landingpage.loginApplication(userName,password);
	    
	}

	@When("^I add product(.+) to the Cart$")
	public void i_add_product_to_the_cart(String productName) {
		List<WebElement> products = productcatalogue.getProducts();
		productcatalogue.getProductByname(productName);
		productcatalogue.addProductToCart(productName);
	    
	}

	@When("^Checkout (.+) and submit order$")
	public void checkout_and_submit_order(String productName) {
		cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplayed(productName);
		Assert.assertTrue(match);
		checkOutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		confirmationpage = checkoutpage.clickPlaceOrder();
	    
	}

	@Then("{string} is displayed on confirmationPage")
	public void is_displayed_on_confirmation_page(String string) {
		
		String receivedMessage = confirmationpage.getMessage();
		Assert.assertTrue(receivedMessage.equalsIgnoreCase(string));
		
	    
	}


}
