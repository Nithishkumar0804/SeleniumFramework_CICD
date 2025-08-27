package selenium.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import selenium.Components.BaseTest;
import selenium.pageObject.*;

import java.io.IOException;

public class stepDefinitionImp extends BaseTest {
    LandingPage landingPage;
    ProductCatalogue productObject;
    CartPage cartObject;
    CheckoutPage checkout;
    ConfirmationPage confirm;
    private String confirmationMessage;

    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("Logged in with {string} and password {string}")
    public void Logged_in_with_name_and_password(String username, String password) {
        productObject = landingPage.login(username, password);
    }

    @Given("I add product {string} to Cart")
    public void I_add_product_productName_to_Cart(String productName) throws InterruptedException {
        productObject.getProductList();
        productObject.getProductByName(productName);
        productObject.addToCart(productName);
    }

    @And("Checkout {string} and submit the order")
    public void Checkout_product_and_submit_the_order(String productName) {
        cartObject = productObject.goToCart();
        Boolean match = cartObject.verifyCartProduct(productName);
        Assert.assertTrue(match, "The cart did not match");
        checkout = cartObject.goToCheckoutPage();
        String country = "India";
        checkout.selectCountry(country);
        confirm = checkout.submitorder();
    }

    @Then("{string} message is displayed in confirmation page")
    public void message_is_displayed_in_confirmation_page(String cnfmsg) {
        String confirmationMessage = confirm.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase(cnfmsg));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void Incorrect_email_or_password_message_is_displayed(String errormsg) {
        Assert.assertEquals(landingPage.getErrorMessage(), errormsg);
        driver.close();
    }

}

