package pageObjects.subscriptionFormSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubscriptionPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='name']")
    public WebElement inputName;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement inputEmail;

    @FindBy(xpath = "//button[contains(text(),'Подписаться')]")
    public WebElement subscriptionButton;

    public SubscriptionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
