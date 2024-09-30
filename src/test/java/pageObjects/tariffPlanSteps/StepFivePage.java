package pageObjects.tariffPlanSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StepFivePage {

    private WebDriver driver;
    @FindBy(xpath = "//h3[contains(text(),'Спасибо!')]")
    public WebElement titleStepFive;

    public StepFivePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}