package selenium.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='ng-trigger-flyInOut']")
    WebElement errorMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalogue login(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        ProductCatalogue productObject = new ProductCatalogue(driver);
        return productObject;
    }

    public String getErrorMessage() {
        waitForElementAppearsWebElement(errorMessage);
        return errorMessage.getText();
    }

}
