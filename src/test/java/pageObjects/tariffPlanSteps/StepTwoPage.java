package pageObjects.tariffPlanSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StepTwoPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='plan_box arcade']")
    public WebElement standardTarif;

    @FindBy(xpath = "//input[@onclick='selectPlan()']")
    public WebElement buttonMoreStepTwo;
    @FindBy(xpath = "//h3[contains(text(),'Добавь опции')]")
    public WebElement titleStepThree;

    public StepTwoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillStepTwoPrecondition(WebDriver driver) {
        standardTarif.click();
        buttonMoreStepTwo.click();
        titleStepThree.isDisplayed();
    }

}
