package tests.tariffPlan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.tariffPlanSteps.StepOnePage;
import pageObjects.tariffPlanSteps.StepTwoPage;

public class StepOnePositiveFlows {
    private WebDriver driver;
    private StepOnePage stepOnePage;
    private StepTwoPage stepTwoPage;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://qa-faculty.github.io/tariff-plan/");
        stepOnePage = new StepOnePage(driver);
        stepTwoPage = new StepTwoPage(driver);
    }


    @Test
    public void SubmitFormWithMinimumNameLength() {
        stepOnePage.inputName.sendKeys("Им");
        stepOnePage.inputEmail.sendKeys("asddsadas@gmail.com");
        stepOnePage.inputPhone.sendKeys("+490155467584");
        stepOnePage.buttonMoreStepOne.click();
        stepOnePage.titleStepTwo.isDisplayed();
    }

    @Test
    public void SubmitFormWithMaximumNameLength() {
        stepOnePage.inputName.sendKeys("Тутвведенотридцатьсимволоввввв");
        stepOnePage.inputEmail.sendKeys("asddsadas@gmail.com");
        stepOnePage.inputPhone.sendKeys("+490155467584");
        stepOnePage.buttonMoreStepOne.click();
        stepOnePage.titleStepTwo.isDisplayed();
    }





}
