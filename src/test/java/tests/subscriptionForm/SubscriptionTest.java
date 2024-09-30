package tests.subscriptionForm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.subscriptionFormSteps.SubscriptionPage;

public class SubscriptionTest {
    private WebDriver driver;
    private SubscriptionPage subscriptionPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        subscriptionPage = new SubscriptionPage(driver);
        driver.get("https://qa-faculty.github.io/subscribtion_form/");
    }

    @Test
    public void SubmitFormWithValidData() {
        subscriptionPage.inputName.sendKeys("имяяя");
        subscriptionPage.inputEmail.sendKeys("asddsadas@gmail.com");
        subscriptionPage.subscriptionButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Текст всплывающего окна: " + alertText);

        // Принимаем alert (можно закрыть alert с помощью этого метода)
        //alert.accept();
    }
}
