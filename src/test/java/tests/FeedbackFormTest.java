package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FeedbackFormTest {

    private static final String successText = "Ваше сообщение успешно отправлено!";
    private static final String errorNameText = "Ошибка в поле Имя.";
    private static final String errorEmailText = "Ошибка в поле Email.";
    private static final String errorPhoneText = "Ошибка в поле Телефон.";
    private static final String emptyFormErrorText = "Ошибка в поле Имя.";

    @Test
    public void sendFeedbackTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-faculty.github.io/feedback_form/");
        driver.findElement(By.xpath("//input[@placeholder='Ваше имя']")).sendKeys("имяяя");
        driver.findElement(By.xpath("//input[@placeholder='Введите email']")).sendKeys("asddsadas@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Введите телефон']")).sendKeys("898921512511");
        driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Сообщение длинное ...");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String messageText = driver.findElement(By.xpath("//div[@id='messageBanner']")).getText();
        assertEquals(successText, messageText);
    }

    @Test
    public void sendFeedbackNegativeNameTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-faculty.github.io/feedback_form/");
        driver.findElement(By.xpath("//input[@placeholder='Ваше имя']")).sendKeys("ы"); // Имя слишком короткое
        driver.findElement(By.xpath("//input[@placeholder='Введите email']")).sendKeys("asddsadas@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Введите телефон']")).sendKeys("898921512511");
        driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Сообщение длинное ...");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals(errorNameText, alertText);
    }

    @Test
    public void sendFeedbackNegativeEmailTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-faculty.github.io/feedback_form/");
        driver.findElement(By.xpath("//input[@placeholder='Ваше имя']")).sendKeys("Татьяна");
        driver.findElement(By.xpath("//input[@placeholder='Введите email']")).sendKeys("невалидный-email"); // Некорректный email
        driver.findElement(By.xpath("//input[@placeholder='Введите телефон']")).sendKeys("898921512511");
        driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Сообщение длинное ...");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals(errorEmailText, alertText);
    }

    @Test
    public void sendFeedbackNegativePhoneTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-faculty.github.io/feedback_form/");
        driver.findElement(By.xpath("//input[@placeholder='Ваше имя']")).sendKeys("Татьяна");
        driver.findElement(By.xpath("//input[@placeholder='Введите email']")).sendKeys("test@example.com");
        driver.findElement(By.xpath("//input[@placeholder='Введите телефон']")).sendKeys("abcde"); // Некорректный телефон
        driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Сообщение длинное ...");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals(errorPhoneText, alertText);
    }

    @Test
    public void sendFeedbackEmptyFormTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-faculty.github.io/feedback_form/");
        // Пустая форма, ничего не заполняем
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals(emptyFormErrorText, alertText);
    }
}
