package tests.tariffPlan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.tariffPlanSteps.*;


public class AllStepsPositiveFlow {

    private WebDriver driver;
    private StepOnePage stepOnePage;
    private StepTwoPage stepTwoPage;
    private StepThreePage stepThreePage;
    private StepFourPage stepFourPage;
    private StepFivePage stepFivePage;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        stepOnePage = new StepOnePage(driver);
        stepTwoPage = new StepTwoPage(driver);
        stepThreePage = new StepThreePage(driver);
        stepFourPage = new StepFourPage(driver);
        stepFivePage = new StepFivePage(driver);
        driver.get("https://qa-faculty.github.io/tariff-plan/");
    }

    // Tests
    @Test
    public void SubmitFormWithValidData() {
        stepOnePage.inputName.sendKeys("имяяя");
        stepOnePage.inputEmail.sendKeys("asddsadas@gmail.com");
        stepOnePage.inputPhone.sendKeys("+490155467584");
        stepOnePage.buttonMoreStepOne.click();
        stepOnePage.titleStepTwo.isDisplayed();
    }

    @Test
    public void ChooseStandardPlanValidTest() {
        stepOnePage.fillStepOnePrecondition(driver);
        stepTwoPage.standardTarif.click();
        stepTwoPage.buttonMoreStepTwo.click();
        stepTwoPage.titleStepThree.isDisplayed();
    }

    @Test
    public void AddOptionValidTest() {
        stepOnePage.fillStepOnePrecondition(driver);
        stepTwoPage.fillStepTwoPrecondition(driver);
        stepThreePage.buttonMoreStepThree.click();
        stepThreePage.titleStepFour.isDisplayed();
    }

    @Test
    public void ConfirmationValidTest() {
        stepOnePage.fillStepOnePrecondition(driver);
        stepTwoPage.fillStepTwoPrecondition(driver);
        stepThreePage.fillStepThreePrecondition(driver);
        stepFourPage.confirmButtonStepFour.click();
        stepFivePage.titleStepFive.isDisplayed();
    }

}
