package selenium.Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.Components.BaseTest;
import selenium.pageObject.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    @Test(groups = "Purchase", dataProvider = "getData")
    public void SubmitOrder(HashMap<String, String> data) throws InterruptedException {
        ProductCatalogue productObject = landingPage.login(data.get("email"), data.get("password"));
        productObject.getProductList();
        productObject.getProductByName(data.get("productName"));
        productObject.addToCart(data.get("productName"));
        CartPage cartObject = productObject.goToCart();
        Boolean match = cartObject.verifyCartProduct(data.get("productName"));
        Assert.assertTrue(match, "The cart did not match");
        CheckoutPage checkout = cartObject.goToCheckoutPage();
        String country = "India";
        checkout.selectCountry(country);
        ConfirmationPage confirm = checkout.submitorder();
        String confirmationMessage = confirm.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"SubmitOrder"}, dataProvider = "getData")
    public void orderHistoryTest(HashMap<String, String> data) {
        ProductCatalogue productObject = landingPage.login(data.get("email"), data.get("password"));
        Orders order = productObject.goToOrders();
        boolean match = order.verifyOrders(data.get("productName"));
        Assert.assertTrue(match);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "/src/main/java/selenium/data/Purchase.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}