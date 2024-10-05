package tests.subscriptionForm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.subscriptionFormSteps.SubscriptionPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionTests {
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

    // Negative tests:
    @Test
    public void SubmitFormWithEmptyFields() {
        subscriptionPage.subscriptionButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Текст всплывающего окна: " + alertText);
        assertEquals("Пожалуйста, введите корректное имя", alertText);
    }

    @Test
    public void SubmitFormWithTooShortName() {
        subscriptionPage.inputName.sendKeys("A");
        subscriptionPage.inputEmail.sendKeys("test@test.com");
        subscriptionPage.subscriptionButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Текст всплывающего окна: " + alertText);
        assertEquals("Пожалуйста, введите корректное имя", alertText);
    }
    @Test
    public void SubmitFormWithTooLongName() {
        subscriptionPage.inputName.sendKeys("ИмяОченьОченьДлинноеКотороеБольше20Символов");
        subscriptionPage.inputEmail.sendKeys("test@test.com");
        subscriptionPage.subscriptionButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Текст всплывающего окна: " + alertText);
        assertEquals("Спасибо, ИмяОченьОченьДлинноеКотороеБольше20Символов, за подписку на рассылку!", alertText);
    }

    @Test
    public void SubmitFormWithTooShortEmail() {
        subscriptionPage.inputName.sendKeys("Имя");
        subscriptionPage.inputEmail.sendKeys("a@a.");
        subscriptionPage.subscriptionButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Текст всплывающего окна: " + alertText);
        assertEquals("Пожалуйста, введите корректный email", alertText);
    }

    @Test
    public void SubmitFormWithTooLongEmail() {
        subscriptionPage.inputName.sendKeys("Имя");
        subscriptionPage.inputEmail.sendKeys("emailоченьдлинныйадрес@оченьдлинныйдомен.com");
        subscriptionPage.subscriptionButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Текст всплывающего окна: " + alertText);
        assertEquals("Пожалуйста, введите корректный email", alertText);
    }

    @Test
    public void SubmitFormWithInvalidEmailFormat() {
        subscriptionPage.inputName.sendKeys("Имя");
        subscriptionPage.inputEmail.sendKeys("test@com");
        subscriptionPage.subscriptionButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Текст всплывающего окна: " + alertText);
        assertEquals("Пожалуйста, введите корректный email", alertText);
    }



}
