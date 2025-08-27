package selenium.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By product = By.cssSelector(".mb-3");
    By addToCartElemet = By.cssSelector(".mb-3 button:last-of-type");
    By toastMeassage = By.cssSelector("#toast-container");

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    public List<WebElement> getProductList() {
        waitForElementAppears(product);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
                .orElse(null);
        return prod;
    }

    public void addToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCartElemet).click();
        waitForElementAppears(toastMeassage);
        Thread.sleep(1000);
        //waitForElemetDisapper(spinner);
    }

}
