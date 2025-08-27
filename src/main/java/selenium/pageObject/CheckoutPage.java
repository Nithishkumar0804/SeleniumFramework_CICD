package selenium.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryInput;

    @FindBy(css = ".ta-item:nth-child(3)")
    WebElement selectCountryElement;

    @FindBy(css = ".action__submit")
    WebElement submitOrderElement;

    By results = By.cssSelector(".ta-item");

    public void selectCountry(String country) {
        Actions a = new Actions(driver);
        countryInput.sendKeys(country);
        waitForElementAppears(results);
        selectCountryElement.click();
    }

    public ConfirmationPage submitorder() {
        submitOrderElement.click();
        return new ConfirmationPage(driver);
    }
}
