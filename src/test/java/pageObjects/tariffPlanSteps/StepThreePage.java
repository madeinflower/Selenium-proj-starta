package pageObjects.tariffPlanSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StepThreePage {
    private WebDriver driver;
    @FindBy(xpath = "//input[@onclick='addOnsFunc()']")
    public WebElement buttonMoreStepThree;
    @FindBy(xpath = "//h3[contains(text(),'На финишной прямой')]")
    public WebElement titleStepFour;

    public StepThreePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillStepThreePrecondition(WebDriver driver) {
        buttonMoreStepThree.click();
        titleStepFour.isDisplayed();
    }
}
