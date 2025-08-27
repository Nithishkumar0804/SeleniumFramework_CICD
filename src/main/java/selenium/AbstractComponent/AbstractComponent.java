package selenium.AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pageObject.CartPage;
import selenium.pageObject.Orders;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerlink*='/dashboard/cart']:nth-child(1)")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersHeader;

    public void waitForElementAppears(By ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ele));
    }

    public void waitForElementAppearsWebElement(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementDisapper(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCart() {
        cartHeader.click();
        CartPage cartObject = new CartPage(driver);
        return cartObject;
    }

    public Orders goToOrders() {
        ordersHeader.click();
        Orders order = new Orders(driver);
        return order;
    }
}
