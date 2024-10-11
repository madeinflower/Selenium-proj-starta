package pageObjects.subscriptionFormSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Alert;

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

    // Метод для ввода имени
    public void inputName(String name) {
        inputName.clear();
        inputName.sendKeys(name);
    }

    // Метод для ввода email
    public void inputEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    // Метод для отправки формы
    public void submitForm() {
        subscriptionButton.click();
    }

    // Метод для получения текста из алерта
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public boolean isSubmitButtonEnabled() {
        return subscriptionButton.isEnabled();
    }
}
