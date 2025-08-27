package selenium.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Orders {
    WebDriver driver;

    public Orders(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[2]")
    List<WebElement> ordersList;

    public boolean verifyOrders(String productName) {
        Boolean match = ordersList.stream().anyMatch(ele -> ele.getText().equalsIgnoreCase(productName));
        return match;
    }
}
