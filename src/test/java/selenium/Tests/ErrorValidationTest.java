package selenium.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.Components.BaseTest;
import selenium.Components.Retry;
import selenium.pageObject.CartPage;
import selenium.pageObject.ProductCatalogue;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {
    @Test(groups = "ErrorHandling", retryAnalyzer = Retry.class)
    public void loginErrorcheck() throws IOException, InterruptedException {
        String name = "rnithishkumar080417@gmail.com";
        String password = "R";
        landingPage.login(name, password);
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

    @Test(groups = "ErrorHandling")
    public void catalogueError() throws IOException, InterruptedException {

        String name = "rnithishkumar080417@gmail.com";
        String password = "Rnithish21##";
        String productName = "ADIDAS ORIGINAL";
        ProductCatalogue productObject = landingPage.login(name, password);
        productObject.getProductList();
        productObject.getProductByName(productName);
        productObject.addToCart(productName);
        CartPage cartObject = productObject.goToCart();
        Boolean match = cartObject.verifyCartProduct(productName);
        //Assert.assertEquals("The cart did not match", "The cart did not match");
        Assert.assertTrue(match, "The cart did not match");
    }
}
