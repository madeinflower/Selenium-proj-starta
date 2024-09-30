package pageObjects.tariffPlanSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StepOnePage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='name']")
    public WebElement inputName;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@id='phone']")
    public WebElement inputPhone;

    @FindBy(xpath = "//input[@onclick='validatePersonalInfo(event)']")
    public WebElement buttonMoreStepOne;
    @FindBy(xpath = "//h3[contains(text(),'Выбери план')]")
    public WebElement titleStepTwo;

    public StepOnePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillStepOnePrecondition(WebDriver driver) {
        inputName.sendKeys("имяяя");
        inputEmail.sendKeys("asddsadas@gmail.com");
        inputPhone.sendKeys("+490155467584");
        buttonMoreStepOne.click();
        titleStepTwo.isDisplayed();
    }
}
